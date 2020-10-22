package com.example.myapplication2.data;
import android.util.Log;

import com.example.myapplication2.bean.Weather;
import com.example.myapplication2.utils.OkhttpUtil;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
public class getAlldata {
    //请求天气数据
    public static void  getWeatherData() throws JSONException {
        String url = "http://apis.juhe.cn/simpleWeather/query?city=西安&key=4a44459f6a8c7b4fd166a150abcb78ac";
        JSONObject jsonParameter = new JSONObject();
        jsonParameter.put("","");
        //调用之前封装好的okhttpManager方法，传入必要参数，获取返回的数据，并对数据进行相应的处理
        OkhttpUtil.okhttpManager(url, jsonParameter, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("天气异常信息；",e.getMessage());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                List<Weather> list = new ArrayList<Weather>();
                //获取返回数据，并将数据转换为字符串
                String string = response.body().string();
                try {
                    //使用JSONObject对字符串进行解析
                    JSONObject json = new JSONObject(string);
                    //根据postman给出的数据格式，先获取result的value值
                    JSONObject result = json.getJSONObject("result");
                    //将result中的value值中的future的值进行获取，并由于其value是数据所以保存在JSONArray中
                    JSONArray JSONArray = result.getJSONArray("future");
                    //对相应的数据进行遍历，并将每次遍历的数据保存在提前定义好的实体类中，最后将每次的实体类对象作为其中一个元素添加到集合中
                    for (int i = 0; i<JSONArray.length();i++){
                        JSONObject item =  JSONArray.getJSONObject(i);
                        String date = item.getString("date");
                        String temperature = item.getString("temperature");
                        String weatherdata = item.getString("weather");
                        String direct = item.getString("direct");
                        Weather weather = new Weather(date,temperature,weatherdata,direct);
                        list.add(weather);
                    }
                    //使用EventBus向另一个地方发送事件
                    EventBus.getDefault().post(list);//传递数据
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
