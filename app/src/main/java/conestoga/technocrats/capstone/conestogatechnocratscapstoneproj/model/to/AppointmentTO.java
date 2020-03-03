package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

public class AppointmentTO
{
    private long id;
    private long peerId;
    private String peerTitle;
    private String appointmentIcon;
    private long registerDate;
    private long appointmentDate;

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
}
