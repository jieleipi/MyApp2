package com.jieleipi.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jieleipi on 2017/11/27.
 */

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.jieleipi.myapp.FORCE_OFFLINE");
        receiver=new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onPause(){
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
        }
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("warnning");
            builder.setMessage("You are force to be offline. Please try to login again. ");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
               @Override
                public void onClick(DialogInterface dialog,int which){
                   ActivityCollector.finishAll();
                   Intent intent=new Intent(context,LoginActivity.class);
                   context.startActivity(intent);
               }
            });
            builder.show();
        }
    }
}