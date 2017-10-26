package com.example.truccongle.themnhahang;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by truccongle on 5/8/2017.
 */

public class DanhSachNhaHang  extends MainActivity {
    ListView lvNhaHang;
    ArrayList<HinhAnh> mangNhaHang;
    NhaHangAdapter adapter= null;
    DatabaseReference    mData= FirebaseDatabase.getInstance().getReference();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_nhahang);
        lvNhaHang=(ListView)findViewById(R.id.lvDSNhaHang);
        adapter= new NhaHangAdapter(this,R.layout.custom_list_view,mangNhaHang);
        lvNhaHang.setAdapter(adapter);
    }
    private  void LoadData(){
mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        HinhAnh  hinhAnh=dataSnapshot.getValue(HinhAnh.class);
        mangNhaHang.add(new HinhAnh(hinhAnh.HinhAnh,hinhAnh.DiaChi));
        adapter.notifyDataSetChanged();
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
}