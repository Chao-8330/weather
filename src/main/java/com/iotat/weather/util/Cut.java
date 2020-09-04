package com.iotat.weather.util;

public class Cut {
    public static String CutStr(String str){
        if (!str.equals("西藏") && !str.equals("内蒙古") && !str.equals("广西") && !str.equals("宁夏") && !str.equals("新疆") && str.length() >= 3) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
