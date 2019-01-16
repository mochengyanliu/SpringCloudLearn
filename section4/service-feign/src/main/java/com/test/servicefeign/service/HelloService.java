package com.test.servicefeign.service;

import com.test.servicefeign.service.impl.HelloServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",fallback = HelloServiceImpl.class)
public interface HelloService {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String sayHelloFromClient(@RequestParam(value = "name") String name);
}
