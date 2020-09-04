package com.iotat.weather.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 通用返回json格式串
 * @author xty
 *
 */
public class Result {


    public static  String resultStatus(Integer code,String msg){
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("msg", msg);
        return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }
    /**
     * 数据格式返回
     * @return
     */
    public static  String resultData(Integer code,Object data){
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("msg", "成功");
        obj.put("data", data);

        return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 数据格式返回 分页
     * @return
     */
//	public static  String resultPage(CodeEnum serviceCode,Integer count,Object data){
//		JSONObject obj = new JSONObject();
//		obj.put("code", serviceCode.getCode());
//		obj.put("msg", serviceCode.getMessage());
//		obj.put("count", count);
//		obj.put("data", data);
//
//		return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue);
//	}
//


}

