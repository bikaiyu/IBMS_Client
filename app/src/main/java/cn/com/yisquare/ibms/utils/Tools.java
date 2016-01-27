package cn.com.yisquare.ibms.utils;

import android.content.Context;
import android.util.TypedValue;

import java.text.SimpleDateFormat;
import java.util.Random;


public class Tools {
    public static int dp2px(Context context,int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
    public static int random(int min , int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }

    public static String stringarrayToString(String[] temp){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            sb.append(temp[i]+"\n");
        }
        return sb.toString();
    }
    public static String MilisToDate(Long time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(time);
    }
    public static Long getCurrentTimeMilis(){
        return System.currentTimeMillis();
    }
    public static String getStringTime(){
        return MilisToDate(getCurrentTimeMilis());
    }
    public static String cenvertMilisToTime(Long time){
        if(time >=getCurrentTimeMilis())
            return "输入错误:"+time;
        long minute = (getCurrentTimeMilis()-time)/60000;
        long sec = (getCurrentTimeMilis()-time)/1000;
        String result="";
        if(sec < 60)
            return sec+"秒以前";
        else if(minute <60)
            return  minute+"分钟以前";
        else if(minute<60*24) //小于24小时内的时间都可以用 "小时"作单位
            return  minute/(60*24)+"小时以前";
        else
             return minute/(60*24)+"天以前";//大于或等于 24小时的 单位就换成 "天"
    }
}
