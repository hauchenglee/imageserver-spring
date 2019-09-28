package com.server.controller;

import com.server.util.Constants;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j
public class TestController {
    @GetMapping(value = "api/test", produces = Constants.CONTENT_TYPE_JSON)
    public String test() {
        log.info("/api/test");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "/api/test");
        return jsonObject.toString();
    }
}
