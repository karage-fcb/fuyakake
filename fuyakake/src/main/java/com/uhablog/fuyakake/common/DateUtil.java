package com.uhablog.fuyakake.common;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
    
    /**
     * 現在日付の月初日を取得する
     * @return 現在日付の月初日
     */
    private static Date getNowFirstDate() {
        
        // 現在時刻の取得
        Date now = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int first = calendar.getActualMinimum(Calendar.DATE);
		calendar.set(Calendar.DATE, first);

		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
        now.setTime(calendar.getTimeInMillis());
        return now;
    }

    /**
     * 現在日付の月末日を取得する
     * @return 現在日付の月末日
     */
    private static Date getNowLastDate() {

        // 現在時刻の取得
        Date now = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		int last = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE, last);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
        now.setTime(calendar.getTimeInMillis());
        return now;
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

        // 表示年月がnullだった場合は現在日時の初日と末日を格納
        if (displayMonth == null) {
            days[0] = getNowFirstDate(); 
            days[1] = getNowLastDate();
        // TODO nullじゃない場合の時の取得
        } else {
            days[0] = Date.valueOf(displayMonth + "-01");
            days[1] = Date.valueOf(displayMonth + "-28");
        }

        return days;
    }
}
