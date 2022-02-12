package com.uhablog.fuyakake.common;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
    
    /**
     * 現在日付の月初日を取得する
     * @return 現在日付の月初日
     */
    private static Date getNowFirstDate(String displayMonth) {
        
        // 現在時刻の取得
        Date firstDate;
        if (displayMonth == null) {
            firstDate = new Date(System.currentTimeMillis());
        } else {
            // TODO illegalArgumentExceptionの考慮
            firstDate = Date.valueOf(displayMonth);
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
    private static Date getNowLastDate(String displayMonth) {

        // 現在時刻の取得
        Date lastDate;
        if (displayMonth == null) {
            lastDate = new Date(System.currentTimeMillis());
        } else {
            // TODO illegalArgumentExceptionの考慮
            lastDate = Date.valueOf(displayMonth);
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

        days[0] = getNowFirstDate(displayMonth); 
        days[1] = getNowLastDate(displayMonth);

        return days;
    }
}
