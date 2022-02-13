package com.uhablog.fuyakake.common;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
    
    /**
     * 現在日付の月初日を取得する
     * @return 現在日付の月初日
     */
    private static Date getFirstDate(String displayMonth) {
        
        // 現在時刻の取得
        Date firstDate;
        if (displayMonth == null) {
            firstDate = new Date(System.currentTimeMillis());
        } else {
            // 受け取った表示年月で初日取得する
            try {
                firstDate = Date.valueOf(displayMonth);
                System.out.println("リクエストパラメータの表示年月をDate型に変換成功");
            } catch (IllegalArgumentException e) {
                System.out.print("リクエストパラメータの表示年月をDate型に変換失敗");
                firstDate = new Date(System.currentTimeMillis());
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);
        int first = calendar.getActualMinimum(Calendar.DATE);
		calendar.set(Calendar.DATE, first);

		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
        firstDate.setTime(calendar.getTimeInMillis());
        return firstDate;
    }

    /**
     * 現在日付の月末日を取得する
     * @return 現在日付の月末日
     */
    private static Date getLastDate(String displayMonth) {

        // 現在時刻の取得
        Date lastDate;
        if (displayMonth == null) {
            lastDate = new Date(System.currentTimeMillis());
        } else {
            // 受け取った表示年月で末日を取得する
            try{
                lastDate = Date.valueOf(displayMonth);
                System.out.println("リクエストパラメータの表示年月をDate型に変換成功");
            } catch (IllegalArgumentException e) {
                System.out.print("リクエストパラメータの表示年月をDate型に変換失敗");
                lastDate = new Date(System.currentTimeMillis());
            }
        }

        Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastDate);
		int last = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE, last);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
        lastDate.setTime(calendar.getTimeInMillis());
        return lastDate;
    }

    /**
     * 指定された表示年月の初日と末日を格納したjava.sql.Dateの配列を返却
     * 0: 初日
     * 1: 末日
     * @param displayMonth
     * @return 指定された表示年月の初日と末日を格納したjava.sql.Dateの配列 
     */
    public static Date[] getFirstDayAndLastDay(String displayMonth) {
        
        // 返却用配列の宣言
        Date[] days = new Date[2];

        days[0] = getFirstDate(displayMonth); 
        days[1] = getLastDate(displayMonth);

        return days;
    }

    public static String[] getStringDisplayMonth(String displayMonth) {

        String[] retDisplayMonth = new String[3];

        // 現在時刻の取得
        Date date;

        // 受け取った表示年月がnullだったら現在日時で取得
        if (displayMonth == null) {
            date = new Date(System.currentTimeMillis());
        } else {
            // 受け取った表示年月で末日を取得する
            try{
                date = Date.valueOf(displayMonth);
            } catch (IllegalArgumentException e) {
                date = new Date(System.currentTimeMillis());
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // カレンダーインスタンスの取得
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

        // 配列の0番目に前月の情報を格納
        int month = calendar.get(Calendar.MONTH);
        calendar.add(Calendar.MONTH, -1);
        retDisplayMonth[0] = sdf.format(calendar.getTime());
        System.out.println("DateUtil.java line 123 前月 = " + retDisplayMonth[0]);

        // 配列の1番目, 2番目に指定年月、次月を格納
        for (int index = 1; index < 3; index++ ) {
            // カレンダーに次の月を設定する
            calendar.add(Calendar.MONTH, 1);
            retDisplayMonth[index] = sdf.format(calendar.getTime());
            System.out.println("DateUtil.java line 130 = " + retDisplayMonth[index]);
        }

        return retDisplayMonth;
    }
}
