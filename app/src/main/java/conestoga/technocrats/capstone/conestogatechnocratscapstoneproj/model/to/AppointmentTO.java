package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TBL_APPOINTMENTS")
public class AppointmentTO
{
    public enum EAppointmentStatus
    {
        Accepted("Accepted"),Rejected("Rejected");
        private String value;
        EAppointmentStatus(String value)
        {
            this.value=value;
        }

        public String getValue() {
            return value;
        }
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "appointmentId")
    private long appointmentId;
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "peerId")
    private long peerId;
    @ColumnInfo(name = "peerTitle")
    private String peerTitle;
    @ColumnInfo(name = "appointmentIcon")
    private String appointmentIcon;
    @ColumnInfo(name = "registerDate")
    private long registerDate;
    @ColumnInfo(name = "appointmentDate")
    private long appointmentDate;
    @ColumnInfo(name = "status")
    private String status;

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPeerId() {
        return peerId;
    }

    public void setPeerId(long peerId) {
        this.peerId = peerId;
    }

    public String getPeerTitle() {
        return peerTitle;
    }

    public void setPeerTitle(String peerTitle) {
        this.peerTitle = peerTitle;
    }

    public String getAppointmentIcon() {
        return appointmentIcon;
    }

    public void setAppointmentIcon(String appointmentIcon) {
        this.appointmentIcon = appointmentIcon;
    }

    public long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    public long getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(long appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
