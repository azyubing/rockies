package com.rockies.ec.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {

    public static void main(String[] args) throws Throwable {
        for(int i = 0 ; i < 10 ; i ++){
            try {
                Thread.sleep(1); //1000 毫秒，也就是1秒.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println(System.currentTimeMillis());
        }
        
    }


}
