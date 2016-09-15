package com.google.android.snet;

import com.google.android.gms.common.api.Api.SimpleClientBuilder;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MxRecordDnsMessageTool {
    private static final byte AA_MASK = (byte) 4;
    private static final int COMPRESSION_MASK = 16383;
    private static byte[] DNS_MESSAGE_HEADER = null;
    private static final byte INDICATOR_COMPRESSION_MASK = (byte) -64;
    public static final byte IN_CLASS = (byte) 1;
    private static final int MESSAGE_SIZE_MASK = 65535;
    public static final byte MX_QTYPE = (byte) 15;
    private static final byte NIBBLE_MASK = (byte) 15;
    private static final byte OPCODE_MASK = (byte) 120;
    private static final int OPCODE_SHIFT = 3;
    private static final byte QR_MASK = Byte.MIN_VALUE;
    private static final byte RA_MASK = Byte.MIN_VALUE;
    private static final byte RCODE_MASK = (byte) 15;
    private static final byte RD_MASK = (byte) 1;
    private static final int SHORT_MASK = 65535;
    private static final byte TC_MASK = (byte) 2;
    private static final byte Z_MASK = (byte) 112;
    private static final int Z_SHIFT = 4;
    private static final CharsetDecoder sCharsetDecoder;

    public static abstract class RecordData {
    }

    public static class AnswerRecordData extends RecordData {
        public String mailExchangeHostName;
        public int preference;
    }

    public static class HeaderSection {
        public int aa;
        public int anCount;
        public int arCount;
        public int id;
        public int nsCount;
        public int opCode;
        public int qdCount;
        public int qr;
        public int rCode;
        public int ra;
        public int rd;
        public int tc;
        public int z;
    }

    public static class ParsedResponse {
        public List<ResourceRecord> answerSections;
        public HeaderSection headerSection;
        public List<QuestionSection> questionSections;
    }

    public static class QuestionSection {
        public int qClass;
        public String qName;
        public int qType;
    }

    public static class ResourceRecord {
        public int answerClass;
        public String name;
        public RecordData rData;
        public int rdLength;
        public int ttl;
        public int type;
    }

    MxRecordDnsMessageTool() {
    }

    static {
        DNS_MESSAGE_HEADER = new byte[]{(byte) -66, (byte) -17, RD_MASK, (byte) 0, (byte) 0, RD_MASK, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        sCharsetDecoder = Charset.forName("US-ASCII").newDecoder();
    }

    ParsedResponse parseResponse(byte[] response) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(response).asReadOnlyBuffer();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        Map<Integer, String> nameCache = new HashMap();
        try {
            int i;
            ParsedResponse parsedResponse = new ParsedResponse();
            HeaderSection headerSection = new HeaderSection();
            headerSection.id = getNextUnsignedShortAsInt(byteBuffer);
            byte options1 = byteBuffer.get();
            headerSection.qr = options1 & -128;
            headerSection.opCode = ((options1 & LogSource.FLYDROID) >> OPCODE_SHIFT) & 15;
            headerSection.aa = options1 & Z_SHIFT;
            headerSection.tc = options1 & 2;
            headerSection.rd = options1 & 1;
            byte options2 = byteBuffer.get();
            headerSection.ra = options2 & -128;
            headerSection.z = (options2 & LogSource.ENDER) << Z_SHIFT;
            headerSection.rCode = (options2 & 15) & 15;
            headerSection.qdCount = getNextUnsignedShortAsInt(byteBuffer);
            headerSection.anCount = getNextUnsignedShortAsInt(byteBuffer);
            headerSection.nsCount = getNextUnsignedShortAsInt(byteBuffer);
            headerSection.arCount = getNextUnsignedShortAsInt(byteBuffer);
            List<QuestionSection> questionSections = new ArrayList();
            for (i = 0; i < headerSection.qdCount; i++) {
                QuestionSection questionSection = new QuestionSection();
                questionSection.qName = parseNameLabels(byteBuffer, nameCache);
                if (questionSection.qName == null) {
                    return null;
                }
                questionSection.qType = getNextUnsignedShortAsInt(byteBuffer);
                questionSection.qClass = getNextUnsignedShortAsInt(byteBuffer);
                questionSections.add(questionSection);
            }
            List<ResourceRecord> answerSections = new ArrayList();
            for (i = 0; i < headerSection.anCount; i++) {
                ResourceRecord answerSection = new ResourceRecord();
                answerSection.name = parseNameLabels(byteBuffer, nameCache);
                if (answerSection.name == null) {
                    return null;
                }
                answerSection.type = getNextUnsignedShortAsInt(byteBuffer);
                answerSection.answerClass = getNextUnsignedShortAsInt(byteBuffer);
                answerSection.ttl = byteBuffer.getInt();
                answerSection.rdLength = getNextUnsignedShortAsInt(byteBuffer);
                AnswerRecordData answerRecordData = new AnswerRecordData();
                answerRecordData.preference = getNextUnsignedShortAsInt(byteBuffer);
                answerRecordData.mailExchangeHostName = parseNameLabels(byteBuffer, nameCache, answerSection.rdLength - 2);
                if (answerRecordData.mailExchangeHostName == null) {
                    return null;
                }
                answerSection.rData = answerRecordData;
                answerSections.add(answerSection);
            }
            parsedResponse.headerSection = headerSection;
            parsedResponse.questionSections = questionSections;
            parsedResponse.answerSections = answerSections;
            return parsedResponse;
        } catch (BufferUnderflowException e) {
            return null;
        } catch (IndexOutOfBoundsException e2) {
            return null;
        } catch (IllegalArgumentException e3) {
            return null;
        }
    }

    private static int getNextUnsignedShortAsInt(ByteBuffer byteBuffer) {
        return byteBuffer.getShort() & SHORT_MASK;
    }

    private String parseNameLabels(ByteBuffer byteBuffer, Map<Integer, String> nameCache) {
        return parseNameLabels(byteBuffer, nameCache, SimpleClientBuilder.API_PRIORITY_OTHER);
    }

    private String parseNameLabels(ByteBuffer byteBuffer, Map<Integer, String> nameCache, int maxLength) {
        return parseNameLabels(byteBuffer, nameCache, maxLength, new HashSet());
    }

    private String parseNameLabels(ByteBuffer responseByteBuffer, Map<Integer, String> nameCache, int maxLength, Set<Integer> previousCompressionOffsets) {
        StringBuilder strBuilder = new StringBuilder();
        int originalOffset = responseByteBuffer.position();
        while (maxLength > 0) {
            responseByteBuffer.mark();
            byte indicatorByte = responseByteBuffer.get();
            if (indicatorByte == null) {
                break;
            }
            byte maskedIndicatorByte = (byte) (indicatorByte & -64);
            if (maskedIndicatorByte == null) {
                byte numOctetsToRead = indicatorByte;
                if (strBuilder.length() != 0) {
                    strBuilder.append(".");
                }
                ByteBuffer nameByteBuffer = ByteBuffer.allocate(numOctetsToRead);
                responseByteBuffer.get(nameByteBuffer.array());
                try {
                    strBuilder.append(sCharsetDecoder.decode(nameByteBuffer).toString());
                    maxLength -= numOctetsToRead + 1;
                } catch (CharacterCodingException e) {
                    return null;
                }
            } else if (maskedIndicatorByte != -64) {
                return null;
            } else {
                responseByteBuffer.reset();
                int compressionOffset = responseByteBuffer.getShort() & COMPRESSION_MASK;
                if (previousCompressionOffsets.contains(Integer.valueOf(compressionOffset))) {
                    return null;
                }
                String nameEnd = (String) nameCache.get(Integer.valueOf(compressionOffset));
                if (nameEnd == null) {
                    previousCompressionOffsets.add(Integer.valueOf(compressionOffset));
                    ByteBuffer duplicateByteBuffer = responseByteBuffer.duplicate();
                    duplicateByteBuffer.position(compressionOffset);
                    nameEnd = parseNameLabels(duplicateByteBuffer, nameCache, SimpleClientBuilder.API_PRIORITY_OTHER, previousCompressionOffsets);
                    if (nameEnd == null) {
                        return null;
                    }
                    nameCache.put(Integer.valueOf(compressionOffset), nameEnd);
                }
                if (strBuilder.length() != 0) {
                    strBuilder.append(".");
                }
                strBuilder.append(nameEnd);
            }
        }
        if (maxLength < 0) {
            return null;
        }
        String name = strBuilder.toString();
        nameCache.put(Integer.valueOf(originalOffset), name);
        return name;
    }

    byte[] createSingleQueryBytes(String domain) {
        byte[] bArr = null;
        List<Byte> questionSection = createQuestionSectionBytes(domain);
        if (questionSection != null) {
            int messageSize = DNS_MESSAGE_HEADER.length + questionSection.size();
            if (messageSize <= SHORT_MASK) {
                int offset;
                bArr = new byte[messageSize];
                byte[] arr$ = DNS_MESSAGE_HEADER;
                int len$ = arr$.length;
                int i$ = 0;
                int offset2 = 0;
                while (i$ < len$) {
                    offset = offset2 + 1;
                    bArr[offset2] = arr$[i$];
                    i$++;
                    offset2 = offset;
                }
                offset = offset2;
                for (Byte nextQuestionByte : questionSection) {
                    offset2 = offset + 1;
                    bArr[offset] = nextQuestionByte.byteValue();
                    offset = offset2;
                }
            }
        }
        return bArr;
    }

    private List<Byte> createQuestionSectionBytes(String domain) {
        String[] parts = domain.split("[.]");
        List<Byte> questionSectionBytes = new ArrayList();
        for (String part : parts) {
            if (part.length() > 63) {
                return null;
            }
            questionSectionBytes.add(Byte.valueOf((byte) part.length()));
            for (int i = 0; i < part.length(); i++) {
                questionSectionBytes.add(Byte.valueOf((byte) part.charAt(i)));
            }
        }
        questionSectionBytes.add(Byte.valueOf((byte) 0));
        questionSectionBytes.add(Byte.valueOf((byte) 0));
        questionSectionBytes.add(Byte.valueOf(RCODE_MASK));
        questionSectionBytes.add(Byte.valueOf((byte) 0));
        questionSectionBytes.add(Byte.valueOf(RD_MASK));
        return questionSectionBytes;
    }
}
