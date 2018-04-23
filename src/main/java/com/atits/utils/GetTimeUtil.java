package com.atits.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimeUtil {

    /**
     * @return time 格式（22:35:29）
     */
    public static String getTime(){
        SimpleDateFormat time1 = new SimpleDateFormat("HH:mm:ss");//设置时间点格式
        String time=time1.format(new Date());
        return time;
    }

    /**
     * @return date 格式（2018-04-22）
     */
    public static String getDate(){
        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=date1.format(new Date());// new Date()为获取当前系统时间
        return date;
    }
}
