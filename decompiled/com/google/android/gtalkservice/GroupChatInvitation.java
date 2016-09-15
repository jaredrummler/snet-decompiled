package com.google.android.gtalkservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GroupChatInvitation implements Parcelable {
    public static final Creator<GroupChatInvitation> CREATOR;
    private long mGroupContactId;
    private String mInviter;
    private String mPassword;
    private String mReason;
    private String mRoomAddress;

    public GroupChatInvitation(String roomAddr, String inviter, String reason, String password, long groupContactId) {
        this.mRoomAddress = roomAddr;
        this.mInviter = inviter;
        this.mReason = reason;
        this.mPassword = password;
        this.mGroupContactId = groupContactId;
    }

    public GroupChatInvitation(Parcel source) {
        this.mRoomAddress = source.readString();
        this.mInviter = source.readString();
        this.mReason = source.readString();
        this.mPassword = source.readString();
        this.mGroupContactId = source.readLong();
    }

    public String getRoomAddress() {
        return this.mRoomAddress;
    }

    public String getInviter() {
        return this.mInviter;
    }

    public String getReason() {
        return this.mReason;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public long getGroupContactId() {
        return this.mGroupContactId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mRoomAddress);
        dest.writeString(this.mInviter);
        dest.writeString(this.mReason);
        dest.writeString(this.mPassword);
        dest.writeLong(this.mGroupContactId);
    }

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Creator<GroupChatInvitation>() {
            public GroupChatInvitation createFromParcel(Parcel source) {
                return new GroupChatInvitation(source);
            }

            public GroupChatInvitation[] newArray(int size) {
                return new GroupChatInvitation[size];
            }
        };
    }
}
