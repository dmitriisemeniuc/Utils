package com.dm.utils.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;

/**
 * Created by dmitrii
 */

public class AssetsUtils {

    private static final String TAG = AssetsUtils.class.getSimpleName();
    private static final String DATABASE_PATH = "database/";
    private static final String DATABASE_FILE_NAME = "database.db";

    private Context context;

    public AssetsUtils(Context context) {
        this.context = context;
    }

    public String getDatabaseFilePath(){
        PackageManager m = context.getPackageManager();
        String s = context.getPackageName();
        try {
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
            File dbFile = new File(s + "/files/" + DATABASE_PATH + DATABASE_FILE_NAME);
            return dbFile.getAbsolutePath();
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Error Package name not found ", e);
        }
        return "";
    }
}
