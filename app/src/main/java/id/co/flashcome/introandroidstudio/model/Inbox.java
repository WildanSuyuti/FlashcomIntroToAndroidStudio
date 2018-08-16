package id.co.flashcome.introandroidstudio.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kakaroto on 16/08/18.
 */
public class Inbox implements Parcelable {
    private String pengirim;
    private String pesan;
    private String jam;

    public Inbox(String pengirim, String pesan, String jam) {
        this.pengirim = pengirim;
        this.pesan = pesan;
        this.jam = jam;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getPesan() {
        return pesan;
    }

    public String getJam() {
        return jam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pengirim);
        dest.writeString(this.pesan);
        dest.writeString(this.jam);
    }

    protected Inbox(Parcel in) {
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
                "pengirim='" + pengirim + '\'' +
                ", pesan='" + pesan + '\'' +
                ", jam='" + jam + '\'' +
                '}';
    }
}
