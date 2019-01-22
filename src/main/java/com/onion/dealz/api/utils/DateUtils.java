package com.onion.dealz.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Date getCurrentDateTime(){
        Date date = new Date();
        return date;
    }

    public String dateToString(Date date){
        return this.simpleDateFormat.format(date);
    }

    public Date stringToDate(String str){
        Date date = null;
        try {
            if(str != null){
                date = this.simpleDateFormat.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
