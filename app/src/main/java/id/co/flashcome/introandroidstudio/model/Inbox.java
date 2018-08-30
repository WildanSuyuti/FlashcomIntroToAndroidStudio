package id.co.flashcome.introandroidstudio.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kakaroto on 16/08/18.
 */
public class Inbox implements Parcelable {
    private int id;
    private String pengirim;
    private String pesan;
    private String jam;

    public Inbox() {
    }

    public Inbox(int id, String pengirim, String pesan, String jam) {
        this.id = id;
        this.pengirim = pengirim;
        this.pesan = pesan;
        this.jam = jam;
    }

    public Inbox(String pengirim, String pesan, String jam) {
        this.pengirim = pengirim;
        this.pesan = pesan;
        this.jam = jam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.pengirim);
        dest.writeString(this.pesan);
        dest.writeString(this.jam);
    }

    protected Inbox(Parcel in) {
        this.id = in.readInt();
        this.pengirim = in.readString();
        this.pesan = in.readString();
        this.jam = in.readString();
    }

    public static final Parcelable.Creator<Inbox> CREATOR = new Parcelable.Creator<Inbox>() {
        @Override
        public Inbox createFromParcel(Parcel source) {
            return new Inbox(source);
        }

        @Override
        public Inbox[] newArray(int size) {
            return new Inbox[size];
        }
    };

    @Override
    public String toString() {
        return "Inbox{" +
                "id=" + id +
                ", pengirim='" + pengirim + '\'' +
                ", pesan='" + pesan + '\'' +
                ", jam='" + jam + '\'' +
                '}';
    }
}
