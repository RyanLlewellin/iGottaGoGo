package com.underthejava.igottagogo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.lang.Object;
import java.util.ArrayList;

import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class MapsActivity extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        LocationListener
{

    MapView mMapView;
    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_maps, container, false);

        getActivity().setTitle("Map");

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        // mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mMapView.getMapAsync(this);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        FloatingActionButton searchFab = (FloatingActionButton) rootView.findViewById(R.id.searchButton);
        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Search Button", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
        loadWashrooms();
            }
        });


        FloatingActionButton addFab = (FloatingActionButton) rootView.findViewById(R.id.addButton);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddWashroomActivity();
            }
        });

        return rootView;
    }

    public void searchWashrooms() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             return;
        }
        startLocationUpdates();
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            showCurrentLocation(mLastLocation);
        }


    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("","");
        }


        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("HELLO WORLD", "HELLO WORLD");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("HELLO WORLD", "HELLO WORLD");
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }

    private void showCurrentLocation(Location location) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(location.getLatitude(), location.getLongitude())).zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // For showing a move to my location button
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        this.googleMap.setMyLocationEnabled(true);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker arg0) {
                String text = String.format("hello\n hello\n hello\n hello %s", arg0.getTitle());
                    Snackbar.make(getView(), text, Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show();
                return false;
            }

        });


    }

    public void loadWashrooms() {
        ArrayList<Washroom> washrooms = WashroomManager.getInstance().getWashrooms();
        double lat = mLastLocation.getLatitude();
        double lang = mLastLocation.getLongitude();

        for (Washroom wash : washrooms) {
            double washLat = wash.getLat();
            double washLang = wash.getLang();
            // double distance = distance(toRadians(lat), toRadians(washLat), toRadians(lang), toRadians(washLang));
            double distance = dist2(lat, lang, washLat, washLang);
            if(distance < User.getInstance().getRadius()) {
                LatLng marker = new LatLng(washLat, washLang);
                this.googleMap.addMarker(new MarkerOptions().position(marker).title(wash.getTitle()).snippet(wash.getDescription()));
            }
        }
    }

    public double distance(double lat1, double lat2, double lang1, double lang2){
        int R = 6371; //radius of earth in km
        double dlon = lang2 - lang1;
        double dlat = lat2 - lang1;
        double a = pow(sin(dlat/2),2) + cos(lat1) * cos(lat2) * pow(sin(dlon/2),2);
        double c = 2 * asin(sqrt(a));
        double distance = R * c;
        return distance/1000;
    }

    public double dist2(double lat1, double lon1, double lat2, double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(toRadians(lat1)) * Math.sin(toRadians(lat2)) + Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * Math.cos(toRadians(theta));
        dist = Math.acos(dist);
        dist = toDegrees(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344; // convert to km

        return dist;
    }

    public void loadWashroomsCheater() {

        // For dropping a marker at a point on the Map
        LatLng utm = new LatLng(43.550652, -79.666222);
        LatLng utm1 = new LatLng(43.547907, -79.660944);
        LatLng utm2 = new LatLng(43.548374, -79.662950);
        LatLng southCommon = new LatLng(43.544513, -79.682694);
        LatLng ErinMills = new LatLng(43.539157, -79.662771);

        this.googleMap.addMarker(new MarkerOptions().position(utm).title("Deerfield").snippet("All gender washrooms"));
        this.googleMap.addMarker(new MarkerOptions().position(utm1).title("Davis").snippet("Old washrooms"));
        this.googleMap.addMarker(new MarkerOptions().position(southCommon).title("South Common").snippet("Old Washrooms"));
        this.googleMap.addMarker(new MarkerOptions().position(ErinMills).title("ErinMills Pump").snippet("Pub Washrooms"));
        this.googleMap.addMarker(new MarkerOptions().position(utm2).title("Kaneff").snippet("Nice washrooms"));
    }

    public void startAddWashroomActivity() {
        Intent intent = new Intent(getActivity(), AddWashroomActivity.class);
        intent.putExtra("LAT", mLastLocation.getLatitude());
        intent.putExtra("LONG", mLastLocation.getLongitude());
        startActivity(intent);
    }

}