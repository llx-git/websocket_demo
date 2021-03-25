package com.ccc.controller;

import com.ccc.conf.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description:
 * @author: luolixi
 * @date: 2021-03-25
 */
@RestController
@RequestMapping("/socket")
public class WebSocketController {

    @GetMapping
    public void sendInfo(@RequestParam String text, @RequestParam String sign){
        try {
            WebSocketServer.sendInfo(sign, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
