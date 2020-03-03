package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

public class MessageTO
{
    private long id;
    private long senderId;
    private long receiverId;
    private long senderTitle;
    private long receiverTitle;
    private String messageIcon;
    private String message;
    private long registerDate;
    private long updateDate;
    private long seenDate;
    private long deleteDate;

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
