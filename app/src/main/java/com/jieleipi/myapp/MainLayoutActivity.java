package com.jieleipi.myapp;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button botton_1=(Button)findViewById(R.id.button_1);
        botton_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainLayoutActivity.this,BroadcaseActivity.class);
                startActivity(intent);
            }
        });

        Button botton_2=(Button)findViewById(R.id.button_2);
        botton_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent("com.jieleipi.myapp.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

        Button force_offline=(Button)findViewById(R.id.force_offline);
        force_offline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent("com.jieleipi.myapp.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

        Button make_call=(Button)findViewById(R.id.make_call);
        make_call.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
                if(ContextCompat.checkSelfPermission(MainLayoutActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainLayoutActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    call();
                }

           }
        });

        Button make_contacts=(Button)findViewById(R.id.make_contacts);
        make_contacts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainLayoutActivity.this,ContactsActivity.class);
                startActivity(intent);
            }
        });

        Button make_notice =(Button)findViewById(R.id.make_notice);
        make_notice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainLayoutActivity.this,Notification.class);
                startActivity(intent);
            }
        });

        Button webview=(Button)findViewById(R.id.webview);
        webview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainLayoutActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private void call(){
        try{
            Intent intent=new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]== getPackageManager().PERMISSION_GRANTED){
                    call();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
