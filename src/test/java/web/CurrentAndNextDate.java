package web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentAndNextDate {
    static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMM20yy");
        Date currentDate = new Date();

        return sdf.format(currentDate);
    }

    static String nextDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMM20yy");
        Date currentDate = new Date();
        Long time = currentDate.getTime();
        time = time + 60*60*24*1000;
        currentDate = new Date(time);
        return sdf.format(currentDate);
    }
}
