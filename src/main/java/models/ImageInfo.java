package models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageInfo implements Parcelable {
    private Integer _id;
    private String _name;
    private Double _size;

    public Integer getId() { return this._id; }
    public String getName() { return this._name; }
    public Double getSize() { return this._size; }

    public void setId(Integer id)
    {
        if(!this._id.equals(id)) this._id = id;
    }

    public void setName(String name)
    {
        if(!this._name.equals(name)) this._name = name;
    }

    public void setSize(Double size)
    {
        if(!this._size.equals(size)) this._size = size;
    }

    public ImageInfo()
    {
        this._id = 0;
        this._size = 0.d;
        this._name = "";
    }

    public ImageInfo(Integer id, String name, Double size)
    {
        this.setId(id);
        this.setName(name);
        this.setSize(size);
    }

    protected ImageInfo(Parcel in)
    {
        this.setId(Integer.valueOf(in.readInt()));
        this.setName(in.readString());
        this.setSize(Double.valueOf(in.readDouble()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this._id);
        dest.writeString(this._name);
        dest.writeDouble(this._size);
    }

    public static final Creator<ImageInfo> CREATOR = new Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel parcel) {
            return new ImageInfo(parcel);
        }

        @Override
        public ImageInfo[] newArray(int i) {
            return new ImageInfo[i];
        }
    };

    @Override
    public int describeContents() { return 0; }
}
