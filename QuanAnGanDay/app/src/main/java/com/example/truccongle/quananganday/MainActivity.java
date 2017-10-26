package com.example.truccongle.quananganday;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import static com.example.truccongle.quananganday.R.id.image;
import static com.example.truccongle.quananganday.R.id.map;
import static com.example.truccongle.quananganday.R.id.nav_manage;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    public GoogleMap mMap;
    Spinner spinner;
    DatabaseReference mData;
    FloatingSearchView floatingSearchView;

    GoogleMap.OnMyLocationChangeListener listener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            if (mMap != null) {
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VerifyPermissions.verify(MainActivity.this);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        mData = FirebaseDatabase.getInstance().getReference();


//        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
//        floatingSearchView.setOnHomeActionClickListener(
//                new FloatingSearchView.OnHomeActionClickListener() {
//                    @Override
//                    public void onHomeClicked() {
//                        floatingSearchView.attachNavigationDrawerToMenuButton(drawer);
//                    } });
//        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
//            @Override
//            public void onSearchTextChanged(String oldQuery, final String newQuery) {
//
//                //get suggestions based on newQuery
//
//                //pass them on to the search view
//                floatingSearchView.swapSuggestions(newSuggestions);
//            }
//        });



//        Toolbar toolbar = (Toolbar) findViewById(R.id.floating_search_view);
//        setSupportActionBar(toolbar);
//Floating
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Bạn muốn thêm Quán Ăn Gần Đây", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    //Seting
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //nav
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            mMap.setMapType(MAP_TYPE_NORMAL);
        } else if (id == R.id.nav_gallery) {

            mMap.setMapType(MAP_TYPE_SATELLITE);

        } else if (id == R.id.nav_slideshow) {
            mMap.setMapType(MAP_TYPE_TERRAIN);


        } else if (id == R.id.nav_manage) {
                AA();

        } else if (id == R.id.nav_share) {

            Intent intent =new Intent(getApplication(),About.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Thông báo");
            String m="Mọi thông tin phản hồi xin vui lòng gửi về";
            String n="Email: truccongle@gmail.com";
            alertDialog.setMessage(m+"\n"+n);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
            public  void AA(){
                mMap.setOnMyLocationChangeListener(listener);
            }


    //GG Map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LoadMarker();


        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(20.942265, 106.059878), 15));
        mMap.setMyLocationEnabled(true);
    }
//
//
//
//        // You can customize the marker image using images bundled with
//        // your app, or dynamically generated bitmaps.
//        mMap.addMarker(new MarkerOptions()
//                .title("Thái Tử Gà")
//                .snippet("Nhà hàng chuyên các món ăn từ gà")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurants))
//                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
//                .position(new LatLng(20.932221,106.062641)));
//
//
//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                Intent intent = new Intent(MainActivity.this,ThongTinNhaHang.class);
//                startActivity(intent);
//            }
//        });
////        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
////            @Override
////            public boolean onMarkerClick(Marker marker) {
////                if(marker.getTitle().equals("Nhà Hàng"))
////                    Toast.makeText(MainActivity.this,marker.getTitle(),Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(MainActivity.this,ThongTinNhaHang.class);
////                startActivity(intent);
////                return true;
////            }
////        });
//
//        LatLng senHo = new LatLng(20.933200,106.070719);
//
////        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));
//
//        // Flat markers will rotate when the map is rotated,
//        // and change perspective when the map is tilted.
//        mMap.addMarker(new MarkerOptions()
//                .title("Âm Thực Sinh Thái Sen Hồ")
//                .snippet("Nhà hàng ẩm thực")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurants))
//                .position(senHo)
//                .flat(true)
//                .rotation(0));
//
//        CameraPosition cameraPosition = CameraPosition.builder()
//                .target(senHo)
//                .zoom(13)
//                .bearing(0)
//                .build();
//
//        // Animate the change in camera view over 2 seconds
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
//                2000, null);
//
//
//
//    }

//    //Dinh vi
//    private void MyLoacation() {
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
//        if (lastLocation != null) {
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                    new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 13));
//
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()))      // Sets the center of the map to location user
//                    .zoom(15)                   // Sets the zoom
//                    .bearing(90)                // Sets the orientation of the camera to east
//                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
//                    .build();                   // Creates a CameraPosition from the builder
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null);
//        }
//    }

    public void LoadMarker() {
        mData.child("NhaHang").addChildEventListener(new ChildEventListener() {
            double kinhDo, viDo;

            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                kinhDo = Double.parseDouble((dataSnapshot.child("Lat").getValue().toString()));
                viDo = Double.parseDouble(dataSnapshot.child("Lon").getValue().toString());
                LatLng NhaHang = new LatLng(kinhDo, viDo);

                mMap.addMarker(new MarkerOptions()

                        .title(dataSnapshot.child("TenNhaHang").getValue().toString())
                        .snippet("Đánh Giá: " + (dataSnapshot.child("DanhGia").getValue().toString()))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurants))
                        .position(NhaHang)
                        .flat(true)
                        .rotation(0));
                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(NhaHang)
                        .zoom(13)
                        .bearing(0)
                        .build();

                // Animate the change in camera view over 2 seconds
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                        1500, null);
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {


                        Intent intent = new Intent(MainActivity.this, ThongTinNhaHang.class);
                        intent.putExtra("TenNhaHang", marker.getTitle().toString());
                        startActivity(intent);


                    }
                });

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


