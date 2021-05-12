package com.tests;

import com.Utility.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestClass {

    public static void main(String[] args) throws Exception{
//        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
//        System.out.println(dateFormat.format(new Date()));
//        ?
        SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = formDate.parse("01/01/2005");
        System.out.println(date.getClass().getSimpleName());
    }

    public static String fnDate(String Date){
        String str1[] = Date.split("-");return str1[0];
    }
    public static String fnMonth(String Date){
        String str1[] = Date.split("-");return str1[1];
    }
    public static String fnYear(String Date){
        String str1[] = Date.split("-");return str1[2];
    }
}
