package com.rockies.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rockies.webservice.Greeting;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    @RequestMapping(value="/greeting2")
    public String Greeting2(@RequestParam(value="name",defaultValue="meiyou") String name){
        return name;
    }
    @RequestMapping("/greeting3")
    public List<Greeting> greeting3(@RequestParam(value="name", defaultValue="World") String name) {
        List<Greeting> list=new ArrayList<Greeting>();
        list.add(new Greeting(counter.incrementAndGet(),
                String.format(template, name)));
        list.add(new Greeting(counter.incrementAndGet(),
                String.format(template, name)));
        return list;
    }
}
