package com.example.truccongle.quananganday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThongTinNhaHangChiTiet extends AppCompatActivity {
    Button danhGia;

    ListView lvNhaHang;
    ArrayList<NhaHang> mangNhaHang;
    NhaHangAdapter adapter = null;
    ListView listView;
    DatabaseReference mData;
    ImageView imageViewHA;
    TextView textViewNhaHang;
    EditText editTextDanhGia;
    Button btnDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nha_hang_chi_tiet);

        imageViewHA = (ImageView) findViewById(R.id.imgHinhAnh);
        textViewNhaHang = (TextView) findViewById(R.id.tvTen);
        listView = (ListView) findViewById(R.id.lvDanhGia);
        editTextDanhGia = (EditText) findViewById(R.id.editDanhGia);
        btnDanhGia = (Button) findViewById(R.id.buttonVietDanhGia);
//        adapter = new NhaHangAdapter(this, R.layout.custom_list_view, mangNhaHang);
//        lvNhaHang.setAdapter(adapter);
//        LoadData();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
            Bundle bundle = getIntent().getExtras();
            String TenNhaHang = bundle.getString("TenNhaHang");

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (TenNhaHang.equals(dataSnapshot.child("TenNhaHang").getValue().toString())) {
                    textViewNhaHang.append(dataSnapshot.child("TenNhaHang").getValue().toString());
                    Picasso.with(getApplication()).load(dataSnapshot.child("HinhAnh").getValue().toString()).into(imageViewHA);
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

//        btnDanhGia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = getIntent().getExtras();
//                final String TenNhaHang = bundle.getString("TenNhaHang");
//                final NhaHang NhaHang = new NhaHang(editTextDanhGia.getText().toString());
//
//                mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        if (TenNhaHang.equals(dataSnapshot.child("TenNhaHang").getValue().toString())) {
//                         mData.child("NhaHang").child("NguoiDungDanhGia").push().setValue(NhaHang);
//
//                            new DatabaseReference.CompletionListener() {
//                                @Override
//                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                    if (databaseError==null){
//                                        Toast.makeText(ThongTinNhaHangChiTiet.this,"Đã thêm thành công",Toast.LENGTH_SHORT).show();
//                                        editTextDanhGia.setText("");
//
//                                    }
//                                    else {
//                                        Toast.makeText(ThongTinNhaHangChiTiet.this,"Lỗi",Toast.LENGTH_LONG).show();
//                                    }
//                                }
//
//                            });
//                        }
//                    }

//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
//    }
//    private  void LoadData(){
//        mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
//
//
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                NhaHang  nhaHang=dataSnapshot.getValue(NhaHang.class);
//                mangNhaHang.add(new NhaHang(nhaHang.getNguoiDungDanhGia()));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

}