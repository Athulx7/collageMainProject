package com.syntax.instantfuel.COMMON;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestPojo implements Parcelable {


    String mid,s_id,mech_name,mech_phone,mech_email,adhar;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMech_name() {
        return mech_name;
    }

    public void setMech_name(String mech_name) {
        this.mech_name = mech_name;
    }

    public String getMech_phone() {
        return mech_phone;
    }

    public void setMech_phone(String mech_phone) {
        this.mech_phone = mech_phone;
    }

    public String getMech_email() {
        return mech_email;
    }

    public void setMech_email(String mech_email) {
        this.mech_email = mech_email;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    String sr_id, s_name, s_owner, s_address, s_phone, s_district, s_email, s_doj, s_status, s_lat, s_long;      //FuelStation

    String c_id, c_name, c_address, c_phone, c_email, c_doj;      //User

    String p_id, p_name, p_address, p_description, p_rate, p_image, p_lat, p_long, p_uid, p_status, c_rqst_id, c_status;     //SellerProperty

    String fid, uid, subject, description, status, type, name;     //Feedback

    String rqstStatus, fuelRqstd, rqst_time, station_rate, rqst_lat, rqst_long, serviceType;  //  Customer Request Details

    public String getRqst_lat() {
        return rqst_lat;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setRqst_lat(String rqst_lat) {
        this.rqst_lat = rqst_lat;
    }

    public String getRqst_long() {
        return rqst_long;
    }

    public void setRqst_long(String rqst_long) {
        this.rqst_long = rqst_long;
    }

    public String getStation_rate() {
        return station_rate;
    }

    public void setStation_rate(String station_rate) {
        this.station_rate = station_rate;
    }

    public String getRqst_time() {
        return rqst_time;
    }

    public void setRqst_time(String rqst_time) {
        this.rqst_time = rqst_time;
    }

    public String getFuelRqstd() {
        return fuelRqstd;
    }

    public void setFuelRqstd(String fuelRqstd) {
        this.fuelRqstd = fuelRqstd;
    }

    public String getRqstStatus() {
        return rqstStatus;
    }

    public void setRqstStatus(String rqstStatus) {
        this.rqstStatus = rqstStatus;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getS_lat() {
        return s_lat;
    }

    public void setS_lat(String s_lat) {
        this.s_lat = s_lat;
    }

    public String getS_long() {
        return s_long;
    }

    public void setS_long(String s_long) {
        this.s_long = s_long;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getC_status() {
        return c_status;
    }

    public void setC_status(String c_status) {
        this.c_status = c_status;
    }

    public String getC_rqst_id() {
        return c_rqst_id;
    }

    public void setC_rqst_id(String c_rqst_id) {
        this.c_rqst_id = c_rqst_id;
    }

    public String getP_uid() {
        return p_uid;
    }

    public void setP_uid(String p_uid) {
        this.p_uid = p_uid;
    }

    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }

    public String getP_lat() {
        return p_lat;
    }

    public void setP_lat(String p_lat) {
        this.p_lat = p_lat;
    }

    public String getP_long() {
        return p_long;
    }

    public void setP_long(String p_long) {
        this.p_long = p_long;
    }

    protected RequestPojo(Parcel in) {
        sr_id = in.readString();
        s_name = in.readString();
        s_owner = in.readString();
        s_address = in.readString();
        s_phone = in.readString();
        s_district = in.readString();
        s_email = in.readString();
        s_doj = in.readString();
        s_status = in.readString();
        c_id = in.readString();
        c_name = in.readString();
        c_address = in.readString();
        c_phone = in.readString();
        c_email = in.readString();
        c_doj = in.readString();
        p_id = in.readString();
        s_id = in.readString();
        p_name = in.readString();
        p_address = in.readString();
        p_description = in.readString();
        p_rate = in.readString();
        p_image = in.readString();
    }

    public static final Creator<RequestPojo> CREATOR = new Creator<RequestPojo>() {
        @Override
        public RequestPojo createFromParcel(Parcel in) {
            return new RequestPojo(in);
        }

        @Override
        public RequestPojo[] newArray(int size) {
            return new RequestPojo[size];
        }
    };

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public String getP_rate() {
        return p_rate;
    }

    public void setP_rate(String p_rate) {
        this.p_rate = p_rate;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_doj() {
        return c_doj;
    }

    public void setC_doj(String c_doj) {
        this.c_doj = c_doj;
    }

    public String getS_status() {
        return s_status;
    }

    public void setS_status(String s_status) {
        this.s_status = s_status;
    }

    public String getSr_id() {
        return sr_id;
    }

    public void setSr_id(String sr_id) {
        this.sr_id = sr_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_owner() {
        return s_owner;
    }

    public void setS_owner(String s_owner) {
        this.s_owner = s_owner;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_district() {
        return s_district;
    }

    public void setS_district(String s_district) {
        this.s_district = s_district;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_doj() {
        return s_doj;
    }

    public void setS_doj(String s_doj) {
        this.s_doj = s_doj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sr_id);
        dest.writeString(s_name);
        dest.writeString(s_owner);
        dest.writeString(s_address);
        dest.writeString(s_phone);
        dest.writeString(s_district);
        dest.writeString(s_email);
        dest.writeString(s_doj);
        dest.writeString(s_status);
        dest.writeString(c_id);
        dest.writeString(c_name);
        dest.writeString(c_address);
        dest.writeString(c_phone);
        dest.writeString(c_email);
        dest.writeString(c_doj);
        dest.writeString(p_id);
        dest.writeString(s_id);
        dest.writeString(p_name);
        dest.writeString(p_address);
        dest.writeString(p_description);
        dest.writeString(p_rate);
        dest.writeString(p_image);
    }
}
