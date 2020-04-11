package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.view.gui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.R;

public class PropertyMapActivity extends FragmentActivity implements OnMapReadyCallback {
    public static class KEY
    {
        public static final String LAT="lat";
        public static final String LNG="lng";
    }
    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;

    private double lat;
    private double lng;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_map);

        lat=getIntent().getDoubleExtra(KEY.LAT,0.0);
        lng=getIntent().getDoubleExtra(KEY.LNG,0.0);

        supportMapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        this.googleMap=googleMap;
        showLocation(this.googleMap,lat,lng,"Property Location");
    }

    private void showLocation(GoogleMap googleMap,double lat,double lng,String title)
    {
        LatLng latLng=new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18.0f));
    }

}
