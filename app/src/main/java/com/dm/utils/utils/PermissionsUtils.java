package com.dm.utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionsUtils {

    public static final String[] STORAGE_PERMISSIONS = new String[]{
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final int REQUEST_CODE_STORAGE_PERMISSIONS = 101;

    public static void askForPermissions(String[] permissions, Activity activity, int requestCode) {
        List<String> permissionsNeeded = new ArrayList<>();
        final List<String> permissionsList = new ArrayList<>();
        for (String PERMISSION : permissions) {
            if (!addPermission(permissionsList, PERMISSION, activity))
                permissionsNeeded.add(PERMISSION);
        }
        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity,
                permissionsList.toArray(new String[permissionsList.size()]),
                requestCode);
        }
    }

    public static boolean addPermission(List<String> permissionsList, String permission, Activity activity) {
        if (permission != null && permission.length() > 0) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
                    return false;
            }
            return true;
        }
        return false;
    }

    public static boolean hasPermissionsGranted(String[] permissions, Context context) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission)
                != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissionsGranted(@NonNull String[] permissionsRequested, @NonNull String[] permissionsList, @NonNull int[] grantResults){
        Map<String, Integer> perms = new HashMap<>();
        for (String permission : permissionsList) {
            perms.put(permission, PackageManager.PERMISSION_GRANTED);
        }
        for (int i = 0; i < permissionsRequested.length; i++)
            perms.put(permissionsRequested[i], grantResults[i]);
        boolean permissionsGranted = true;
        for (String permission : permissionsList) {
            permissionsGranted = perms.get(permission) == PackageManager.PERMISSION_GRANTED;
            if (!permissionsGranted) break;
        }
        return permissionsGranted;
    }
}

