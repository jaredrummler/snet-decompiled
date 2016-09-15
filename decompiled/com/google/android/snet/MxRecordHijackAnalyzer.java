package com.google.android.snet;

import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.android.snet.MxRecordDnsMessageTool.ParsedResponse;
import com.google.android.snet.MxRecordDnsMessageTool.ResourceRecord;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MxRecordHijackAnalyzer {
    private static final int DNS_PORT = 53;
    private static final int DNS_QUERY_TIMEOUT_MILLIS = 10000;
    private static final int DNS_SERVER_CONNECT_TIMEOUT_MILLIS = 10000;
    private static final List<String> EMAIL_DOMAIN_NAMES;

    public static class MxInfo {
        public String dnsServer;
        public String domainName;
        public List<MxRecord> mxRecordsList;
        public byte[] rawDnsQueryResponse;
    }

    public static class MxRecord {
        public List<String> ipAddressesList;
        public String mailServerName;
    }

    MxRecordHijackAnalyzer() {
    }

    static {
        EMAIL_DOMAIN_NAMES = Arrays.asList(new String[]{"gmail.com"});
    }

    List<MxInfo> getMxRecords() {
        List<MxInfo> mxRecords = new ArrayList();
        List<String> dnsServersList = NetworkAnalyzer.dnsServers();
        if (dnsServersList.isEmpty()) {
            return null;
        }
        for (String domainName : EMAIL_DOMAIN_NAMES) {
            for (String dnsServer : dnsServersList) {
                MxInfo currentRecords = new MxInfo();
                currentRecords.domainName = domainName;
                currentRecords.dnsServer = dnsServer;
                byte[] dnsResponse = queryForMxTypes(domainName, dnsServer);
                if (dnsResponse != null) {
                    List<String> mailServersList = parseForMailServers(dnsResponse);
                    if (mailServersList == null || mailServersList.isEmpty()) {
                        currentRecords.rawDnsQueryResponse = dnsResponse;
                        mxRecords.add(currentRecords);
                    } else {
                        List<MxRecord> mxRecordList = new ArrayList();
                        for (String mailServer : mailServersList) {
                            MxRecord currentRecord = new MxRecord();
                            currentRecord.mailServerName = mailServer;
                            try {
                                InetAddress[] mailServerInetAddress = InetAddress.getAllByName(mailServer);
                                List<String> mailServerIpAddresses = new ArrayList();
                                for (InetAddress ipAddress : mailServerInetAddress) {
                                    mailServerIpAddresses.add(ipAddress.getHostAddress());
                                }
                                currentRecord.ipAddressesList = mailServerIpAddresses;
                            } catch (UnknownHostException e) {
                            }
                            mxRecordList.add(currentRecord);
                        }
                        currentRecords.mxRecordsList = mxRecordList;
                        mxRecords.add(currentRecords);
                    }
                }
            }
        }
        return mxRecords;
    }

    private byte[] queryForMxTypes(String commonMailName, String dnsServer) {
        Throwable th;
        byte[] questionSectionBytes = new MxRecordDnsMessageTool().createSingleQueryBytes(commonMailName);
        if (questionSectionBytes == null) {
            return null;
        }
        ByteBuffer tcpDnsQueryByteBuffer = ByteBuffer.allocate(questionSectionBytes.length + 2).order(ByteOrder.BIG_ENDIAN);
        tcpDnsQueryByteBuffer.putShort((short) questionSectionBytes.length);
        tcpDnsQueryByteBuffer.put(questionSectionBytes);
        Socket socket = null;
        try {
            SocketAddress address = new InetSocketAddress(dnsServer, DNS_PORT);
            Socket socket2 = new Socket();
            try {
                socket2.connect(address, DNS_SERVER_CONNECT_TIMEOUT_MILLIS);
                OutputStream outputStream = socket2.getOutputStream();
                InputStream inputStream = socket2.getInputStream();
                outputStream.write(tcpDnsQueryByteBuffer.array());
                byte[] temp = new byte[PeopleColumnBitmask.LAST_MODIFIED_TIME];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                socket2.setSoTimeout(DNS_SERVER_CONNECT_TIMEOUT_MILLIS);
                while (true) {
                    int len = inputStream.read(temp, 0, temp.length);
                    if (len == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(temp, 0, len);
                }
                byte[] tcpDnsResponse = byteArrayOutputStream.toByteArray();
                ByteBuffer byteBuffer = ByteBuffer.wrap(tcpDnsResponse);
                byteBuffer.order(ByteOrder.BIG_ENDIAN);
                int dnsResponseMessageLength = byteBuffer.getShort();
                if (tcpDnsResponse.length == dnsResponseMessageLength + 2) {
                    byte[] dnsResponse = new byte[dnsResponseMessageLength];
                    byteBuffer.get(dnsResponse);
                    if (socket2 == null || socket2.isClosed()) {
                        return dnsResponse;
                    }
                    try {
                        socket2.close();
                        return dnsResponse;
                    } catch (IOException e) {
                        return dnsResponse;
                    }
                } else if (socket2 == null || socket2.isClosed()) {
                    return null;
                } else {
                    try {
                        socket2.close();
                        return null;
                    } catch (IOException e2) {
                        return null;
                    }
                }
            } catch (IOException e3) {
                socket = socket2;
                return socket == null ? null : null;
            } catch (BufferUnderflowException e4) {
                socket = socket2;
                return socket == null ? null : null;
            } catch (Throwable th2) {
                th = th2;
                socket = socket2;
                try {
                    socket.close();
                } catch (IOException e5) {
                }
                throw th;
            }
        } catch (IOException e6) {
            if (socket == null && !socket.isClosed()) {
                try {
                    socket.close();
                    return null;
                } catch (IOException e7) {
                    return null;
                }
            }
        } catch (BufferUnderflowException e8) {
            if (socket == null && !socket.isClosed()) {
                try {
                    socket.close();
                    return null;
                } catch (IOException e9) {
                    return null;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (!(socket == null || socket.isClosed())) {
                socket.close();
            }
            throw th;
        }
    }

    private List<String> parseForMailServers(byte[] dnsResponse) {
        List<String> mailServersList = new ArrayList();
        ParsedResponse parsedResponse = new MxRecordDnsMessageTool().parseResponse(dnsResponse);
        if (parsedResponse == null) {
            return null;
        }
        List<ResourceRecord> answerSections = parsedResponse.answerSections;
        if (answerSections == null) {
            return null;
        }
        for (ResourceRecord answerSection : answerSections) {
            if (answerSection.type == 15 && answerSection.answerClass == 1) {
                mailServersList.add(answerSection.rData.mailExchangeHostName);
            }
        }
        return mailServersList;
    }
}
