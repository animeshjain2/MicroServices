package com.centime.service1.controller;

import com.centime.service1.dto.NameRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import javax.swing.text.html.HTML;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class Service1Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        System.out.println("Service1 /health called");
        return ResponseEntity.ok("Up");
    }

    @PostMapping("/call-services")
    public ResponseEntity<String> callServices(@Valid @RequestBody NameRequest request, @RequestHeader(value = "traceId", required = false) String traceId) {

        if(traceId == null || traceId.isEmpty()){
            traceId = UUID.randomUUID().toString();
        }

        System.out.println("[Service1] traceId: " + traceId + " — Calling Service 2 (/hello)");

        System.out.println("Call is here");
        HttpHeaders headers = new HttpHeaders();
        headers.set("traceId", traceId);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String helloResponse;

        try{
            ResponseEntity<String> response  = restTemplate.exchange(
                    "http://localhost:8081/api/hello",
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            helloResponse = response.getBody();

        }
        catch (Exception e){
            System.err.println("[Service1] Failed calling Service 2: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Service 2 error: " + e.getMessage());

        }

        HttpEntity<NameRequest> postEntity = new HttpEntity<>(request,headers);
        String nameResponse;

        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:8082/api/process",
                    HttpMethod.POST,
                    postEntity,
                    String.class
            );
            nameResponse = response.getBody();
        }
        catch (Exception e){
            System.err.println("[Service1] Failed calling Service 3: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Service 3 error: " + e.getMessage());
        }

        return ResponseEntity.ok(helloResponse + " " + nameResponse);

    }

}