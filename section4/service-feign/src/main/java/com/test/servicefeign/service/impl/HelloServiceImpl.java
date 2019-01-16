package com.test.servicefeign.service.impl;

import com.test.servicefeign.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHelloFromClient(String name) {
        return "sorry " + name;
    }
}
