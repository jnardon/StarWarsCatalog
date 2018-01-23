package br.com.accera.starwarscatalog.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by juliano.nardon on 23/01/18.
 */

public class DateHelper {

    public static final DateHelper instance = new DateHelper();

    public String getCurrentDateString() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }
}
