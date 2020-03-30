package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TBL_USERS")
public class UserTO
{
    public static final class KEY{
        public static final String ID_KEY="id";
            public static final String NAME_KEY="name";
        public static final String FAMILY_KEY="family";
        public static final String EMAIL_KEY="email";
        public static final String PHONE_KEY="phone";
        public static final String PHOTO_KEY="photo";
        public static final String PASSWD_KEY="password";
        public static final String TOKEN_KEY="token";
    }
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "family")
    private String family;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "photo")
    private String photo;
    @ColumnInfo(name = "password")
    private String passwd;
    @ColumnInfo(name = "token")
    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
