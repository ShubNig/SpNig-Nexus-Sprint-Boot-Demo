package com.sinlov.sprint.demo.hello;

import com.sinlov.sprint.demo.conf.RouterConf;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * this set base router
 */
@RestController
@RequestMapping(value = RouterConf.TAG, headers = "Accept=application/json", produces = "application/json")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * {@value RouterConf#TAG}{@value RouterConf#GREETING}
     *
     * @param name
     * @return
     */
    @RequestMapping(
            value = RouterConf.GREETING,
            method = RequestMethod.GET
    )
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "world") String name) {
//        if (StringUtils.isEmpty(name)) {
//            name = "empty";
//        }
        Greeting greeting = new Greeting(counter.incrementAndGet(),
                String.format(template, name));
        greeting.setContent(String.format(template, name));
        return greeting;
    }
}
