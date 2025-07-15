package com.centime.service2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HelloController{


    @GetMapping("/hello")
    public ResponseEntity<String> sayhello(@RequestHeader(value = "traceId",required = false) String traceId){
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }

        System.out.println("[Service2] traceId: " + traceId + " — /hello endpoint called");
        return ResponseEntity.ok("Hello");

    }
}