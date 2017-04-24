package com.example.liuqi.recieveservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import com.example.liuqi.serviceaidl.IMyAidlInterface;

/**
 * Created by liuqi on 2017/4/24.
 */

public class RemoteConnection implements ServiceConnection {

    private final Context context;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(context,msg.what+"",Toast.LENGTH_SHORT).show();
        }
    };
    private final Messenger clientMessenger;

    public RemoteConnection(Context context){
        this.context = context;
        clientMessenger = new Messenger(handler);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        IMyAidlInterface aidlInterface = IMyAidlInterface.Stub.asInterface(service);
        try {
            Messenger serviceMessenger = aidlInterface.getMessenger();
            Message message = Message.obtain();
            message.replyTo = clientMessenger;
            serviceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
