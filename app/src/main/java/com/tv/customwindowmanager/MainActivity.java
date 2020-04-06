package com.tv.customwindowmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.tv.customwindowmanager.service.PopupService;

public class MainActivity extends AppCompatActivity {

//    private PopupManager popupManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                int REQUEST_CODE = 0;
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                myIntent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(myIntent, REQUEST_CODE);
            }
        }

        startService(new Intent(this, PopupService.class));

        /*popupManager = new PopupManager(this);
        popupManager.show();*/
    }

    /*@Override
    protected void onDestroy() {
        popupManager.remove();
        super.onDestroy();
    }*/
}
