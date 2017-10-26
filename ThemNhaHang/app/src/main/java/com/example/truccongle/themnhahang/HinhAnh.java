package com.example.truccongle.themnhahang;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by truccongle on 5/8/2017.
 */
@IgnoreExtraProperties
public class HinhAnh {
    public String TenNhaHang;
    public  String HinhAnh;
    public  String DiaChi;
    public String Time;
    public String DanhGia;
    public String Phone;
    public String Lat;
    public String Lon;

    public HinhAnh(String s, String valueOf) {
    }

    public HinhAnh(String tenNhaHang, String hinhAnh, String diaChi, String time,String danhGia, String phone, String lat, String lon) {
        TenNhaHang = tenNhaHang;
        HinhAnh = hinhAnh;
        DiaChi = diaChi;
        Time=time;
        DanhGia = danhGia;
        Phone = phone;
        Lat = lat;
        Lon = lon;
    }}

