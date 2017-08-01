package com.dm.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import java.io.File;

public class FileUtils {

    public static String createDir(int actionId, Activity activity, String dir) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // RUNTIME PERMISSION Android M
                if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    return createDir(dir);
                } else {
                    ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        actionId);
                }
            } else {
                // LOLLIPOP
                return FileUtils.createDir(dir);
            }
        }
        return "";
    }

    public static String createDir(String dir) {
        File storageDir = new File(Environment.getExternalStorageDirectory(), dir);
        if (storageDir.exists()) return storageDir.getPath();
        if (storageDir.mkdirs()) {
            return storageDir.getPath();
        } else if (!storageDir.exists()) {
            return "";
        }
        return storageDir.getPath();
    }
}
