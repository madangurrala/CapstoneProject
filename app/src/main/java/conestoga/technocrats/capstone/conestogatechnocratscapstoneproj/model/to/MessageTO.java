package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;


@Entity(tableName = "TBL_MESSAGES")
public class MessageTO
{
    public class KEY
    {
        public static final String MESSAGE_ID_KEY="messageId";
        public static final String SENDER_ID_KEY="senderId";
        public static final String RECEIVER_ID_KEY="receiverId";
        public static final String MESSAGE_KEY="message";
        public static final String REGISTER_DATE_KEY="registerDate";
        public static final String UPDATE_DATE_KEY="updateDate";
        public static final String SEEN_DATE_KEY="seenDate";
        public static final String DELETE_DATE_KEY="deleteDate";
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "messageId")
    private long messageId;
    @ColumnInfo(name = "senderId")
    private int senderId;
    @ColumnInfo(name = "receiverId")
    private int receiverId;
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

    public MessageTO() {
    }

    @Exclude
    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
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
