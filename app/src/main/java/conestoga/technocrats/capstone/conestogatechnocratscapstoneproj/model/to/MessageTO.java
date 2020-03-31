package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "TBL_MESSAGES")
public class MessageTO
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messageId")
    private long messageId;
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "senderId")
    private long senderId;
    @ColumnInfo(name = "receiverId")
    private long receiverId;
    @ColumnInfo(name = "senderTitle")
    private long senderTitle;
    @ColumnInfo(name = "receiverTitle")
    private long receiverTitle;
    @ColumnInfo(name = "messageIcon")
    private String messageIcon;
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "registerDate")
    private long registerDate;
    @ColumnInfo(name = "updateDate")
    private long updateDate;
    @ColumnInfo(name = "seenDate")
    private long seenDate;
    @ColumnInfo(name = "deleteDate")
    private long deleteDate;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public long getSenderTitle() {
        return senderTitle;
    }

    public void setSenderTitle(long senderTitle) {
        this.senderTitle = senderTitle;
    }

    public long getReceiverTitle() {
        return receiverTitle;
    }

    public void setReceiverTitle(long receiverTitle) {
        this.receiverTitle = receiverTitle;
    }

    public String getMessageIcon() {
        return messageIcon;
    }

    public void setMessageIcon(String messageIcon) {
        this.messageIcon = messageIcon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public long getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(long seenDate) {
        this.seenDate = seenDate;
    }

    public long getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(long deleteDate) {
        this.deleteDate = deleteDate;
    }
}
