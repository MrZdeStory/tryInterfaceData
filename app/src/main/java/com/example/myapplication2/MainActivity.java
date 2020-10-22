package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.myapplication2.adpater.WeatherAdapter;
import com.example.myapplication2.bean.Weather;
import com.example.myapplication2.data.getAlldata;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("tag001","我来了=====================================================");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView)findViewById(R.id.lv);
        Log.v("tag002","mLv视图对象=====================================================");
        //在Activity中注册事件,此Activity中需要接受消息
        EventBus.getDefault().register(this);
        Log.v("tag003","EventBus事件进行了注册=====================================================");
        //ui线程不能有耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //执行获取接口数据的方法，完成对数据的获取以及借助于EventBus将结果数据传递回到此Activity
                    getAlldata.getWeatherData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.v("tag004","请求并发送了数据=====================================================");

    }

    //EventBus   进行事件处理  ，事件响应方法，接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(List<Weather> list){
        Log.v("tag005","即将进行事件处理=====================================================");
        //此处调用ListView的setAdapter方法，用于显示视图中的列表项，这里显示的是ListView中的列表项
        //其中需要传入一个ListAdapter对象，这里的对象为自定义，使用了其中的BaseAdapter
        mLv.setAdapter(new WeatherAdapter(MainActivity.this,list));
        Log.v("tag006","进行了事件处理=====================================================");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //此为Activity的其中一个生命周期方法，会在程序退出时回调
        //回调此方法时，会取消EventBus事件订阅
        EventBus.getDefault().unregister(this);
    }

}