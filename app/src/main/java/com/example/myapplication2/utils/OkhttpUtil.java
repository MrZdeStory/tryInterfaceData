package com.example.myapplication2.utils;

import org.json.JSONObject;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
//此处为对于Okhttp的第三方库的使用
//用于编写传入获取数据的url以及JSONObject和Callback对象之后的一系列处理逻辑
public class OkhttpUtil {
    public static void okhttpManager(String url, JSONObject JSONObject, Callback Callback)
    {
        //获取OkhttpClient对象
        OkHttpClient OkHttpClient = new OkHttpClient();
        //获取RequstBody对象
        RequestBody RequestBody = okhttp3.RequestBody.create(JSONObject.toString(), MediaType.parse("application/json;charset=utf-8"));
        //获取Requst对象
        Request Request = new Request.Builder().url(url).post(RequestBody).build();
        //调用OkhttpClent对象的newCall（）方法传入前面的Request对象
        OkHttpClient.newCall(Request).enqueue(Callback);
    }
}
























































