package com.dsm.ims.controller;

import com.dsm.ims.domains.domain.User;
import com.dsm.ims.domains.service.HelloService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String hello() {
        return "dd";
    }

    @GetMapping("/json")
    public String json() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa", "bb");
        jsonObject.put("cc", "dd");
        return jsonObject.toJSONString();
    }

    @GetMapping("/jpa")
    public String jpa(User user) {
        User findUser = helloService.useJpa(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", findUser.getId());
        jsonObject.put("pw", findUser.getPw());
        jsonObject.put("name", findUser.getName());
        return jsonObject.toJSONString();
    }

    @GetMapping("/save")
    public String save(User user) {
        helloService.save(user);
        return "save";
    }
}
