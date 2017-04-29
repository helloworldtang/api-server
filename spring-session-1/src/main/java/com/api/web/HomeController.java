package com.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tang.cheng on 2017/3/28.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "setValue", method = RequestMethod.POST)
    public String setValue(@RequestParam(name = "key", required = false) String key,
                           @RequestParam(name = "value", required = false) String value,
                           HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(key) && !ObjectUtils.isEmpty(value)) {
            request.getSession().setAttribute(key, value);
        }
        return "home";
    }

}
