package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManipulation {

    //Easier to process Date this way.

    public static Date parseDate(String date) {

        try {

            return new SimpleDateFormat("yyy-mm-dd").parse(date);

        } catch(Exception exception) {
            System.out.println("Exception in parsing date");
            exception.printStackTrace();

            return null;
        }

    }
}
