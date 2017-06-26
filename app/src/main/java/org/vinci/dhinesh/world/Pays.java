package org.vinci.dhinesh.world;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SATTIA Dhinesh on 03/04/2017.
 */

public class Pays implements Parcelable {
    private int id;
    private String name;
    private String text;
    private String code;
    private String region;
    private int surface;
    private String codepng;


    protected Pays(Parcel in) {
        id = in.readInt();
        name = in.readString();
        text = in.readString();
        code = in.readString();
        region = in.readString();
        surface = in.readInt();
        codepng = in.readString();
    }

    public static final Creator<Pays> CREATOR = new Creator<Pays>() {
        @Override
        public Pays createFromParcel(Parcel in) {
            return new Pays(in);
        }

        @Override
        public Pays[] newArray(int size) {
            return new Pays[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(text);
        dest.writeString(code);
        dest.writeString(region);
        dest.writeInt(surface);
        dest.writeString(codepng);
    }

    public Pays(int id, String name, String text, String code, String region, int surface, String codepng) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.code = code;
        this.region = region;
        this.surface = surface;
        this.codepng = codepng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getCodepng() {
        return codepng;
    }

    public void setCodepng(String codepng) {
        this.codepng = codepng;
    }

    public static Creator<Pays> getCREATOR() {
        return CREATOR;
    }
}

