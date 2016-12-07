package com.example.skuo.yuezhan.Util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理帮助类
 * Created by sendMyLove on 2016/11/10 14:15.
 */
public class PermissionUtils1 {
    private static final int PERMISSION_TYPE_DEFAULT = 0x00;                //默认类型
    public static final int PERMISSION_TYPE_STORAGE = 0x01;                 //SDCard读写相关权限
    public static final int PERMISSION_TYPE_CAMERA = 0x02;                  //相机相关权限
    public static final int PERMISSION_TYPE_AUDIO = 0x03;                  //录音权限
    public static final int PERMISSION_TYPE_SDCARD = 0x04;                 //挂载SDCard权限
    public static final int PERMISSION_TYPE_INTERNET = 0x05;               //联网权限
    private static final int REQUEST_CODE = 1;

    private PermissionUtils1() {
    }

    /** 检查权限 ，可以同时设定检测多个权限 **/
    public static boolean checkPermission(Context context, int... permissionType) {
        Activity activity = (Activity) context;
        return checkPermission(activity, permissionType);
    }

    /** 检查权限 ，可以同时设定检测多个权限 **/
    public static boolean checkPermission(Activity activity, int... permissionType) {
        int code = -1; //检验是否有权限code

        boolean isPermission = true;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //6.0以下
        List<String> permissions = getPermissionList(permissionType);

        for (int position = 0; position < permissions.size(); position++) {
            code = ActivityCompat.checkSelfPermission(activity, permissions.get(position));
            if (code != PackageManager.PERMISSION_GRANTED) {
                isPermission = false;
                break;
            }
        }
        return isPermission;
    }

    /** 请求权限 ，可以同时设定注册多个权限 **/
    public static boolean requestPermission(Context context, int... permissionType) {
        Activity activity = (Activity) context;
        return requestPermission(activity, permissionType);
    }

    /** 请求权限 ，可以同时设定注册多个权限 **/
    public static boolean requestPermission(Activity activity, int... permissionType) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> permissions = getPermissionList(permissionType);
        if (permissions == null || permissions.size() <= 0) {
            return false;
        }
        //AppNotice.showToast(activity, "申请权限中", AppNotice.TOAST_COLOR_NOTICE);
        //权限的注册
        String[] HEX_PERMISSION = new String[permissions.size()];
        for (int position = 0; position < permissions.size(); position++) {
            HEX_PERMISSION[position] = permissions.get(position);
        }
        ActivityCompat.requestPermissions(activity, HEX_PERMISSION, REQUEST_CODE);

        return true;
    }


    //权限List
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static List<String> getPermissionList(int... permissionType) {
        List<String> permissions = new ArrayList<>();
        for (int type : permissionType) {
            switch (type) {
                case PERMISSION_TYPE_STORAGE:
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    break;
                case PERMISSION_TYPE_CAMERA:
                    permissions.add(Manifest.permission.CAMERA);
                    break;
                case PERMISSION_TYPE_AUDIO:
                    permissions.add(Manifest.permission.RECORD_AUDIO);
                    break;
                case PERMISSION_TYPE_SDCARD:
                    permissions.add(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
                    break;
                case PERMISSION_TYPE_INTERNET:
                    permissions.add(Manifest.permission.INTERNET);
                    break;
                case PERMISSION_TYPE_DEFAULT:
                default:
                    break;
            }
        }
        return permissions;
    }
}

