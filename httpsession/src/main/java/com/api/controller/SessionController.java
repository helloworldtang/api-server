package com.api.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by tang.cheng on 2017/3/18.
 */
@RestController
@Lazy
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SessionController implements Serializable {
    /**
     * Caused by: java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type [com.api.controller.SessionController]
     *
     * @param args
     */
    private static LongAdder longAdder = new LongAdder();

    @RequestMapping("/")
    public ResponseEntity<Long> defaultIndex() {
        longAdder.increment();
        return ResponseEntity.ok(longAdder.longValue());
    }


}
