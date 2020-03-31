package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TBL_PROPERTIES")
public class PropertyTO implements Parcelable
{
    public PropertyTO(){}
    public PropertyTO(Parcel in) {
        propertyId = in.readLong();
        id = in.readLong();
        title = in.readString();
        smallImagePath = in.readString();
        bigImagePath = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
        status = in.readString();
        userId = in.readLong();
        user = in.readString();
        registerDate = in.readLong();
        latitude = in.readDouble();
        longitude = in.readDouble();
        address = in.readString();
        size = in.readString();
        price = in.readString();
        rate = in.readFloat();
        viewCount = in.readInt();
    }

    public static final Creator<PropertyTO> CREATOR = new Creator<PropertyTO>() {
        @Override
        public PropertyTO createFromParcel(Parcel in) {
            return new PropertyTO(in);
        }

        @Override
        public PropertyTO[] newArray(int size) {
            return new PropertyTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(propertyId);
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(smallImagePath);
        dest.writeString(bigImagePath);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
        dest.writeString(status);
        dest.writeLong(userId);
        dest.writeString(user);
        dest.writeLong(registerDate);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(address);
        dest.writeString(size);
        dest.writeString(price);
        dest.writeFloat(rate);
        dest.writeInt(viewCount);
    }

    public static final class KEY
    {
            public static final String PROPERTY_ID_KEY = "propertyId";
            public static final String ID_KEY = "id";
            public static final String TITLE_KEY = "title";
            public static final String SMALL_IMAGE_PATH_KEY = "smallImagePath";
            public static final String BIG_IMAGE_PATH_KEY = "bigImagePath";
            public static final String SHORT_DESCRIPTION_KEY = "shortDescription";
            public static final String LONG_DESCRIPTION_KEY = "longDescription";
            public static final String STATUS_KEY = "status";
            public static final String USER_ID_KEY = "userId";
            public static final String USER_KEY = "user";
            public static final String REGISTER_DATE_KEY = "registerDate";
            public static final String LATITUDE_KEY = "latitude";
            public static final String LONGITUDE_KEY = "longitude";
            public static final String ADDRESS_KEY = "address";
            public static final String SIZE_KEY = "size";
            public static final String PRICE_KEY = "price";
            public static final String RATE_KEY = "rate";
            public static final String VIEW_COUNT_KEY = "viewCount";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "propertyId")
    private long propertyId;
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "smallImagePath")
    private String smallImagePath;
    @ColumnInfo(name = "bigImagePath")
    private String bigImagePath;
    @ColumnInfo(name = "shortDescription")
    private String shortDescription;
    @ColumnInfo(name = "longDescription")
    private String longDescription;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "userId")
    private long userId;
    @ColumnInfo(name = "user")
    private String user;
    @ColumnInfo(name = "registerDate")
    private long registerDate;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "size")
    private String size;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "rate")
    private float rate;
    @ColumnInfo(name = "viewCount")
    private int viewCount;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallImagePath() {
        return smallImagePath;
    }

    public void setSmallImagePath(String smallImagePath) {
        this.smallImagePath = smallImagePath;
    }

    public String getBigImagePath() {
        return bigImagePath;
    }

    public void setBigImagePath(String bigImagePath) {
        this.bigImagePath = bigImagePath;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
