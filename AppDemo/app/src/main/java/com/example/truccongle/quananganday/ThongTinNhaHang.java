package com.example.truccongle.quananganday;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.Rating;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.jar.*;
import java.util.jar.Manifest;

public class ThongTinNhaHang extends AppCompatActivity {
    DatabaseReference mData;
    ImageView imgHinh;
    TextView tvTen, tvDiaChi, tvPhone, tvTime, tvDanhGia, tvLat, tvLon;
    Button btnCall, btnshareFB, btnShare, danhGia;
    String url;
    RatingBar ratingBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.thong_tin_nha_hang);
        imgHinh = (ImageView) findViewById(R.id.imgHinhAnh);
        tvTen = (TextView) findViewById(R.id.tvTen);
        tvDiaChi = (TextView) findViewById(R.id.tvDiaChi);
        tvPhone = (TextView) findViewById(R.id.tvSoDienThoai);
        tvTime = (TextView) findViewById(R.id.tvThoiGian);
        tvDanhGia = (TextView) findViewById(R.id.tvDanhGia);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnshareFB = (Button) findViewById(R.id.btnFaceBook);
        btnShare = (Button) findViewById(R.id.btnShare);
//        danhGia=(Button)findViewById(R.id.buttonDanhGia);
//        danhGia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(ThongTinNhaHang.this,ThongTinNhaHangChiTiet.class);
//                Bundle bundle = getIntent().getExtras();
//                final String TenNhaHang = bundle.getString("TenNhaHang");
//                intent.putExtra("TenNhaHang",TenNhaHang);
//                startActivity(intent);
//            }
//        });
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
            Bundle bundle = getIntent().getExtras();
            final String TenNhaHang = bundle.getString("TenNhaHang");

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (TenNhaHang.equals(dataSnapshot.child("TenNhaHang").getValue().toString())) {
                    tvTen.append(dataSnapshot.child("TenNhaHang").getValue().toString());
                    tvDiaChi.append(dataSnapshot.child("DiaChi").getValue().toString());
                    tvTime.append(dataSnapshot.child("Time").getValue().toString());
                    tvPhone.append(dataSnapshot.child("Phone").getValue().toString());
                    tvDanhGia.append(dataSnapshot.child("DanhGia").getValue().toString());
                    Picasso.with(getApplication()).load(dataSnapshot.child("HinhAnh").getValue().toString()).into(imgHinh);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                final String TenNhaHang = bundle.getString("TenNhaHang");
                mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (TenNhaHang.equals(dataSnapshot.child("TenNhaHang").getValue().toString())) {
                            String number = dataSnapshot.child("Phone").getValue().toString();
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + number));
                            if (ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        btnshareFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShareFB();
            }
        });


    }

    public void onClickShareFB() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        url = "http://quananganday.blogspot.com/2017/05/blog-post.html";
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.setPackage("com.facebook.katana");
        startActivity(intent);


    }

    public void Share() {
        Bundle bundle = getIntent().getExtras();
        final String TenNhaHang = bundle.getString("TenNhaHang");
        mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (TenNhaHang.equals(dataSnapshot.child("TenNhaHang").getValue().toString())) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String TenNhaHang = dataSnapshot.child("TenNhaHang").getValue().toString();
                    String DiaChi = dataSnapshot.child("DiaChi").getValue().toString();
                    String Phone = dataSnapshot.child("Phone").getValue().toString();
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Ứng dụng Quán Ăn Gần Đây");
                    intent.putExtra(android.content.Intent.EXTRA_TEXT, "Nhà Hàng:" + TenNhaHang + "\nĐịa Chỉ:" + DiaChi + "\nSố điện thoại:" + Phone);

                    startActivity(Intent.createChooser(intent, "Share via"));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//

    }
}
