package pl.saramak.connectwithwearapp;

/**
 * Created by aleksandra on 29.04.2017.
 */


import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by aleksandra on 29.04.2017.
 */

public class WatchfaceConfigActivity extends MainActivity {
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                    }
                    @Override
                    public void onConnectionSuspended(int cause) {
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                    }
                })
                .addApi(Wearable.API)
                .build();
        mGoogleApiClient.connect();
    }
    private void syncConfiguration() {
        if(mGoogleApiClient==null)
            return;

        final PutDataMapRequest putRequest = PutDataMapRequest.create("/CONFIG");
        final DataMap map = putRequest.getDataMap();
        map.putInt("mode", 1);
        map.putInt("color", Color.RED);
        map.putString("string_example", "MyWatchface");
        Wearable.DataApi.putDataItem(mGoogleApiClient,  putRequest.asPutDataRequest());
    }
}