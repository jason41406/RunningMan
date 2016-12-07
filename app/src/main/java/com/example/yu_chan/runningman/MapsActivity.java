package com.example.yu_chan.runningman;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, DialogInterface.OnCancelListener {

    private GoogleMap mMap;
    Spinner spnMapType;
    CheckBox chkTraffic;
    String[] mapType = {"街道圖", "衛星圖", "衛星圖+街道圖", "地形圖"};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO:  1. Creat a location Request called  mLocationRequest
        //TODO:  2. Set it's priority to high accuracy
        //TODO:  3. set it to update every second (1000ms)
        //TODO:  4. Call RequsetLocatioinUpdates in the Api with this request
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (errorCode != ConnectionResult.SUCCESS)
            GooglePlayServicesUtil.showErrorDialogFragment(errorCode, this, 111, this);
        else {
            spnMapType = (Spinner) findViewById(R.id.spinner1);
            chkTraffic = (CheckBox) findViewById(R.id.checkBox1);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mapType);
            spnMapType.setAdapter(adapter);
            spnMapType.setOnItemSelectedListener(this);
            chkTraffic.setOnCheckedChangeListener(this);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(LocationServices.API).build();//addApi參數:6AppIndex.API
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(121.5729889, 25.0776557);
        mMap.addMarker(new MarkerOptions().position(sydney).title("This is my first Maker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("This is my First Marker"));
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        finish(); /* 將視窗關閉*/
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked)
            mMap.setTrafficEnabled(true);/* 顯示交通狀況 */
        else
            mMap.setTrafficEnabled(false);/* 不顯示交通狀況 */
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:/* 一般街道模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 1:/* 衛星模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 2:/* 街道衛星混和模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3:/* 地形模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.yu_chan.runningman/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.yu_chan.runningman/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
