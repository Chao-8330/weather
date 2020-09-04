package com.iotat.weather.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class HttpClient {
    public static String EnGzipGetHttp(String httpUrl) throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                        + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        //连接网站接口
        URL serverUrl = new URL(httpUrl);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
        conn.setInstanceFollowRedirects(false);
        conn.connect();


        //接收json字符串
        String weatherStr;
        //接收返回的json字符页
        StringBuffer buffer = new StringBuffer();
        try (GZIPInputStream inputStream = new GZIPInputStream(conn.getInputStream());
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            //中间变量，读取字符流
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            //接收结果
            weatherStr = buffer.toString();
        }
        return weatherStr;
    }

    public static String GzipGetHttp(String httpUrl) throws Exception {
        //连接网站接口
        URL serverUrl = new URL(httpUrl);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
        conn.setInstanceFollowRedirects(false);
        conn.connect();


        //接收json字符串
        String weatherStr;
        //接收返回的json字符页
        StringBuffer buffer = new StringBuffer();
        try (GZIPInputStream inputStream = new GZIPInputStream(conn.getInputStream());
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            //中间变量，读取字符流
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            //接收结果
            weatherStr = buffer.toString();
        }
        return weatherStr;
    }
    public static String GetHttp(String httpUrl) throws Exception{
        URL serverUrl = new URL(httpUrl);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
        conn.setInstanceFollowRedirects(false);
        conn.connect();

        //接收json字符串
        String weatherStr;
        //接收返回的json字符页
        StringBuffer buffer = new StringBuffer();
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"))

        ) {
            //中间变量，读取字符流
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            //接收结果
            weatherStr = buffer.toString();
        }
        return weatherStr;
    }
    public static String EnGetHttp(String httpUrl) throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                        + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        URL serverUrl = new URL(httpUrl);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
        conn.setInstanceFollowRedirects(false);
        conn.connect();

        //接收json字符串
        String weatherStr;
        //接收返回的json字符页
        StringBuffer buffer = new StringBuffer();
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"))

        ) {
            //中间变量，读取字符流
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            //接收结果
            weatherStr = buffer.toString();
        }
        return weatherStr;
    }
    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }
    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}
