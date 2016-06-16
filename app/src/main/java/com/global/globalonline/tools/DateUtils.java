package com.global.globalonline.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sgf on 2015/9/24 0024.
 */
public class DateUtils {
    static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat simpleDateFormat_1=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 比较两个日期大小,结束日期>=开始日期,返回true
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean compareDate(String startDate, String endDate) {
        return  compareDate(startDate,endDate,simpleDateFormat);

    }

    /**
     * 比较两个日期大小,结束日期>开始日期,返回true
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param simpleDateFormat 日期格式
     * @return
     */
    public static boolean compareDate(String startDate, String endDate, SimpleDateFormat simpleDateFormat) {
        boolean boo=false;

        try {
            long start=simpleDateFormat.parse(startDate).getTime();
            long end=simpleDateFormat.parse(endDate).getTime();
            if (start < end) {
                boo=true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return boo;
    }

    /**
     * @param date
     *
     */
    public static int getDatePaseInt(String date) {
        return getDatePaseInt(date,simpleDateFormat);
    }

    /**
     * @param date
     * @param simple 返回日期时间戳/1000
     * @return
     */
    public static int getDatePaseInt(String date, SimpleDateFormat simple) {
        int dateTime=0;
        try {
            dateTime= (int) (simple.parse(date).getTime()/1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * @param time
     * @return 根据时间戳转换为日期格式
     */
    public static String getDateStringByLong(Long time) {
        return getDateStringByLong(time, simpleDateFormat);
    }

    /**
     * @param time
     * @return 根据时间戳转换为日期格式
     */
    public static String getDateString(String time) {
        String date = "不限";

        if(!StringUtil.isBlank(time)){

            try {
                date =  getDateStringByLong(Long.parseLong(time), simpleDateFormat);
            }catch (Exception e){
                date = "不限";
            }

        }
        return date ;
    }

    /**
     * @param time
     * @param simple
     * @return 根据时间戳转换为日期格式
     */
    public static String getDateStringByLong(Long time, SimpleDateFormat simple) {
        return simple.format(new Date(time*1000));
    }
}
