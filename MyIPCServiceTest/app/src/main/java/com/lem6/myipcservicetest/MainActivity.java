package com.lem6.myipcservicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "myaidl";
    IBinder mBinder;
    private int plus = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent();
        intent.setAction("com.lem6.myserver");
        intent.setPackage("com.lem6.aidlserver");

        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinder = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);
    }


    public void onClick(View view) {

        Parcel _data = Parcel.obtain();
        Parcel _replay = Parcel.obtain();
        int result;


        try {
            _data.writeInterfaceToken("CalcPlusService");
            _data.writeInt(5);
            _data.writeInt(plus);
            mBinder.transact(0x110,_data,_replay,0);

            _replay.readException();
            result = _replay.readInt();
            Log.d(TAG, "onClick: result="+result);
            plus++;
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            _replay.recycle();
            _data.recycle();
        }


    }
}
