package com.lem6.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by chenjialin on 2018/5/9 0009
 */
public class HelloService extends Service {

    private final HelloBinder helloBinder;

    public HelloService() {
        helloBinder = new HelloBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return helloBinder;
    }


    class HelloBinder extends IHelloAIDLInterface.Stub{


        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            return "I am robot";
        }
    }
}
