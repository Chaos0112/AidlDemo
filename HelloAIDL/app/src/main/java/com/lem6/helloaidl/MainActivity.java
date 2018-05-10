package com.lem6.helloaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lem6.aidlserver.IHelloAIDLInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "helloaidl";
    private IHelloAIDLInterface iHelloAIDLInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent();
        intent.setAction("com.lem6.server");
        intent.setPackage("com.lem6.aidlserver");

        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                iHelloAIDLInterface = IHelloAIDLInterface.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },BIND_AUTO_CREATE);
    }

    public void onClick(View view) {
        try {
            Log.d(TAG, "onClick: "+iHelloAIDLInterface.getName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
