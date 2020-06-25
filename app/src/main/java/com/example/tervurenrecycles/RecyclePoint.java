package com.example.tervurenrecycles;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RecyclePoint extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapAPI;
    private Toolbar mTopToolbar;
    private BottomNavigationView bottomNavigationView;

    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_point);
        
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);

        // Check Permissions
        if(ActivityCompat.checkSelfPermission(RecyclePoint.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            // When Permission granted
            // Call Method
            getCurrentLocation();
        }else {
            // When Permission Denied
            // Request Permission
            ActivityCompat.requestPermissions(RecyclePoint.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.RecycleBins:
                            Intent Recycled = new Intent(getApplicationContext(), Mainmenu.class);
                            startActivity(Recycled);
                        case R.id.home:
                            Intent newAct = new Intent(getApplicationContext(), Mainmenu.class);
                            startActivity(newAct);
                        case R.id.Batteries:
                            Intent Batteries = new Intent(getApplicationContext(), Mainmenu.class);
                            startActivity(Batteries);
                    }
                    return false;
                }
            };




    private void getCurrentLocation() {
        //Intialize task Location
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if(location != null){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            // Initialize lat lng
                            LatLng latLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());

                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("Current");

                            // Zoom Map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapAPI = googleMap;
        //mapAPI.setMyLocationEnabled(true);
        LatLng Tervuren = new LatLng(50.824125, 4.513850);
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.817627, 4.510866)).title("Diependal bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.817427, 4.510840)).title("Diependal bin 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.819020, 4.512878)).title("Skatepark bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.819470, 4.514138)).title("Scouts Tervuren bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.820805, 4.511426)).title("GBS Tervuren bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.820405, 4.507264)).title("Kapellestraat bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.822602, 4.502140)).title("Hippolyte Boulengerlaan bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.822802, 4.502253)).title("Brusselsesteenweg 192 bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.822922, 4.502395)).title("Brusselsesteenweg 182 bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.addMarker(new MarkerOptions().position(new LatLng(50.824125, 4.513850)).title("Jezus Eiklaan bin").icon(BitmapDescriptorFactory.fromResource(R.drawable.resized_marker)));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(Tervuren, 15F));

        if ((ActivityCompat.checkSelfPermission(RecyclePoint.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
            mapAPI.setMyLocationEnabled(true);


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // When Permission Granted
                getCurrentLocation();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(RecyclePoint.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
