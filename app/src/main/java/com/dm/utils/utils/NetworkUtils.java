package com.dm.utils.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by dmitrii
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    public static final String DEFAULT_IPV4 = "0.0.0.0";

    /**
     * Collects list of all local IP addresses
     *
     * @return Addresses list, starting from default.
     */
    static public CharSequence[] getLocalIpAddresses() {
        ArrayList<String> listDisplayNames = new ArrayList<String>();
        boolean isPreferIPv6 = true;

        return getLocalIpAddresses(listDisplayNames, isPreferIPv6);
    }

    /**
     * Collects list of all local IP addresses
     *
     * @param listDisplayNames Array list of network type names to display
     * @param isIncludeIpv6 true, if Ipv6 addresses should be included too
     * @return Addresses list, starting from default and fill list of display names
     */
    @SuppressLint("DefaultLocale") static public CharSequence[] getLocalIpAddresses(
        ArrayList<String> listDisplayNames, boolean isIncludeIpv6) {

        ArrayList<String> list = new ArrayList<String>();
        list.add(DEFAULT_IPV4);
        listDisplayNames.add("auto");
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                if (intf.isUp()) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                         enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            if (inetAddress instanceof Inet4Address) {
                                String addr = inetAddress.getHostAddress().toString();
                                Log.d(TAG, "NetworkUtils.getLocalIpAddresses - " + addr);
                                list.add(addr);
                                listDisplayNames.add(intf.getDisplayName().toLowerCase());
                            } else if (isIncludeIpv6 && inetAddress instanceof Inet6Address) {
                                Inet6Address addr6 = (Inet6Address) inetAddress;
                                if (!addr6.isLinkLocalAddress() && !addr6.isSiteLocalAddress()) {
                                    String addr = formatIpv6Address(inetAddress.getHostAddress().toString());
                                    Log.d(TAG, "NetworkUtils.getLocalIpAddresses - " + addr);
                                    list.add(addr);
                                    listDisplayNames.add(intf.getDisplayName().toLowerCase());
                                }
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, "getLocalIpAddresses ex: " + ex.toString());
        }
        Log.i(TAG, "getLocalIpAddresses count: " + list.size());
        return list.toArray(new CharSequence[list.size()]);
    }

    public static String formatIpv6Address(String src) {
        int pos = src.indexOf("%");
        if (pos > 0) {
            src = src.substring(0, pos);
        }
        if (!src.startsWith("[")) {
            src = "[" + src;
        }
        if (!src.endsWith("]")) {
            src += "]";
        }
        return src;
    }
}
