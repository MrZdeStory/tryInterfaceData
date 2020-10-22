package com.example.myapplication2.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.bean.Weather;

import java.util.List;
//此处使用的是BaseAdapter，适配器中的其中一种
public class WeatherAdapter extends BaseAdapter {

    private Context context;
    private List<Weather> list;

    public WeatherAdapter(Context context, List<Weather> list) {
        this.context = context;
        this.list = list;
    }


    //使用了BaseAdapter的文艺式模式
    //首先创建了一个内部类
    //其次判断convertView是否为空
    //通过setTag方法将viewHolder与convertView建立关系绑定

    //表示适配器中数据集的数据个数
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
    //表示获取数据集中与索引对应的数据项
    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }
    //表示获取指定行对应的ID
    @Override
    public long getItemId(int position) {
        return position;
    }
    //获取每一行item的显示内容
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //以下通过判断convertView是否为空来完成convertView与viewHolder的绑定
        //目的在于绑定之后完成将获取到的数据放到对应的ui控件上
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.weather_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Weather weather =  list.get(position);
        viewHolder.mDate.setText(weather.getDate());
        viewHolder.mTempter.setText(weather.getTemperature());
        viewHolder.mWeather.setText(weather.getWeather());
        viewHolder.mDescript.setText(weather.getDirect());
        return convertView;
    }

    //存放控件   获取view对象

    static
    class ViewHolder{
        View view;
        TextView mDate;
        TextView mTempter;
        TextView mWeather;
        TextView mDescript;
        ViewHolder(View view){
            this.view = view;
            this.mDate = view.findViewById(R.id.date);
            this.mTempter = view.findViewById(R.id.tempter);
            this.mWeather = view.findViewById(R.id.weather);
            this.mDescript = view.findViewById(R.id.description);
        }
    }

}














































