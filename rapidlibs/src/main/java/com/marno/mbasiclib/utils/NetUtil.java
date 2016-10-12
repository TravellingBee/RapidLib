package com.marno.mbasiclib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by 李刚 on 2016/3/25/10:46.
 * 网络相关辅助类
 */
public class NetUtil {

    /**
     * isNetConnected
     *
     * @param context
     * @return boolean
     */
    public static boolean isNetConnected(Context context) {
        boolean ret = false;
        //获取到管理系统连接状态的manager
        ConnectivityManager manager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        //manager.getActiveNetwork();//API23中的方法，使用时注意版本判断
        //返回当前正在活动的网络状态的信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {//如果信息为空，说明没有网络连接，直接返回
            return ret;
        }
        //如果有网络，判断网络是否可用，是否连接
        ret = info.isAvailable() && info.isConnected();
        return ret;
    }


    /**
     * is Wifi enable
     *
     * @param context
     * @return boolean
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * isWifi
     *
     * @param context
     * @return boolean
     */
    public static boolean isWifi(Context context) {
        boolean ret = false;
        //先判断是否有网络连接
        if (!isNetConnected(context)) {
            return ret;
        }
        ConnectivityManager manager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        //判断网络连接类型是否是Wifi
        ret = manager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        return ret;
    }


    /**
     * is2G
     *
     * @param context
     * @return boolean
     */
    public static boolean is2G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && (activeNetInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
                || activeNetInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
                .getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA)) {
            return true;
        }
        return false;
    }

    /**
     * check is3G
     *
     * @param context
     * @return boolean
     */
    public static boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

}
