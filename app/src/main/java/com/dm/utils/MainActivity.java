package com.dm.utils;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.dm.utils.utils.PermissionsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Read / Write file permissions
            if (!PermissionsUtils.hasPermissionsGranted(PermissionsUtils.STORAGE_PERMISSIONS, this))
                PermissionsUtils.askForPermissions(PermissionsUtils.STORAGE_PERMISSIONS, this, PermissionsUtils.REQUEST_CODE_STORAGE_PERMISSIONS);
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionsUtils.REQUEST_CODE_STORAGE_PERMISSIONS: {
                boolean granted = PermissionsUtils.checkPermissionsGranted(permissions, PermissionsUtils.STORAGE_PERMISSIONS, grantResults);
                if (!granted) {
                    PermissionsUtils.askForPermissions(
                        PermissionsUtils.STORAGE_PERMISSIONS, this,
                        PermissionsUtils.REQUEST_CODE_STORAGE_PERMISSIONS);
                }
                break;
            }
        }
    }
}
