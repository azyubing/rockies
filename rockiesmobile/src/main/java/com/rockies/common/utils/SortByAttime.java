package com.rockies.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class SortByAttime implements Comparator {

    @Override
    public int compare(Object arg0, Object arg1) {
        Map<String,Object> s1 = (Map)arg0;
        Map<String,Object> s2 = (Map)arg1;
        SimpleDateFormat format = new SimpleDateFormat("hh:ss");
        int flag = 0;
        try {
            Date d1 = format.parse(s1.get("attime").toString());
            Date d2 = format.parse(s2.get("attime").toString());
            System.out.println(format.format(d1));
            System.out.println(format.format(d2));
            flag = d1.compareTo(d2); 
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        return flag; 
    }

}
