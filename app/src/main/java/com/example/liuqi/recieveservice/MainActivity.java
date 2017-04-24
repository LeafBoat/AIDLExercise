package com.example.liuqi.recieveservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service = new Intent();
        ComponentName componentName = new ComponentName("com.example.liuqi.serviceaidl","com.example.liuqi.serviceaidl.service.RemoteService");
        service.setComponent(componentName);
        RemoteConnection conn = new RemoteConnection(this);
        bindService(service,conn,BIND_AUTO_CREATE);
    }
}
