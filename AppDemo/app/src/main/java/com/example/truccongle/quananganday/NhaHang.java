package com.example.truccongle.quananganday;

import android.app.Service;

/**
 * Created by truccongle on 5/6/2017.
 */

public class NhaHang {
    private String TenNhaHang;
    private String HinhAnh;
    private String DiaChi;
    private String Sdt;
    private String DanhGia;
    private String ThoiGian;
    private String Lat;
    private String Lon;

    public NhaHang(String tenNhaHang, String hinhAnh, String diaChi, String sdt, String danhGia, String thoiGian, String lat, String lon) {
        TenNhaHang = tenNhaHang;
        HinhAnh = hinhAnh;
        DiaChi = diaChi;
        Sdt = sdt;
        DanhGia = danhGia;
        ThoiGian = thoiGian;
        Lat = lat;
        Lon = lon;
    }

    public NhaHang(String s) {

    }

    public String getTenNhaHang() {
        return TenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        TenNhaHang = tenNhaHang;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(String danhGia) {
        DanhGia = danhGia;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

}

