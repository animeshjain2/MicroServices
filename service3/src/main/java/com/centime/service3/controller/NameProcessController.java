package com.centime.service3.controller;

import com.centime.service3.dto.NameRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class NameProcessController{

    @PostMapping("/process")
    public ResponseEntity<String> processName(@Valid @RequestBody NameRequest request,
                                              @RequestHeader(value = "traceId", required = false) String traceId) {

        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }

        System.out.println("[Service3] traceId: " + traceId + " — Input: " + request.getName() + " " + request.getSurname());

        String fullName = request.getName().trim() + " " + request.getSurname().trim();
        return ResponseEntity.ok(fullName);

    }
}