package com.example.truccongle.themnhahang;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
Button btnThem,btnDS;
    EditText edtNhaHang,edtDiaChi,edtTime,edtPhone,edtDanhGia,edtLat,edtLon;
    ImageView imgHinh;
    int REQUEST_CODE_IMAGE = 1;
    DatabaseReference mData;
    FirebaseStorage storage =FirebaseStorage.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://quananganday-166002.appspot.com/");
         mData= FirebaseDatabase.getInstance().getReference();
         /*
        /Anhxa
         */
        btnThem=(Button)findViewById(R.id.buttonThem);
        imgHinh=(ImageView)findViewById(R.id.imageButtonHinh);
        edtNhaHang=(EditText)findViewById(R.id.editTextNhaHang);
        edtDiaChi=(EditText)findViewById(R.id.editTextDiaChi);
        edtTime=(EditText)findViewById(R.id.editTextTime);
        edtPhone=(EditText)findViewById(R.id.editTextPhone);
        edtDanhGia=(EditText)findViewById(R.id.editTextDanhGia);
        edtLat=(EditText)findViewById(R.id.editTextLat);
        edtLon=(EditText)findViewById(R.id.editTextLon);


        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =Calendar.getInstance();
                StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");
                // Get the data from an ImageView as bytes
                imgHinh.setDrawingCacheEnabled(true);
                imgHinh.buildDrawingCache();
                Bitmap bitmap = imgHinh.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(MainActivity.this,"Lỗi",Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Log.d("aaaa", downloadUrl + "");

//Tạo mode

                        HinhAnh NhaHang= new HinhAnh(edtNhaHang.getText().toString(),String.valueOf(downloadUrl),edtDiaChi.getText().toString(),edtTime.getText().toString(),edtDanhGia.getText().toString(),edtPhone.getText().toString(),edtLat.getText().toString(),edtLon.getText().toString());
                         mData.child("NhaHang").push().setValue(NhaHang, new DatabaseReference.CompletionListener() {
                             @Override
                             public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                 if (databaseError==null){
                                     Toast.makeText(MainActivity.this,"Đã thêm thành công",Toast.LENGTH_SHORT).show();
                                     edtDiaChi.setText("");
                                     edtNhaHang.setText("");
                                     edtDanhGia.setText("");
                                     edtTime.setText("");
                                     edtPhone.setText("");
                                     edtLon.setText("");
                                     edtLat.setText("");
                                     imgHinh.setImageResource(R.drawable.logo);

                                 }
                                 else {
                                     Toast.makeText(MainActivity.this,"Lỗi",Toast.LENGTH_LONG).show();
                                 }
                             }
                         });



                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);

        }
    }
}
