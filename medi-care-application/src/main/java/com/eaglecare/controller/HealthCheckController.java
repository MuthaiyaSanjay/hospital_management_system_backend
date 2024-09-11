package com.medicare.controller;


import com.medicare.api.HealthApi;
import com.medicare.model.GetHealth200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController implements HealthApi {


    @Override
    public ResponseEntity<GetHealth200Response> getHealth() {
        GetHealth200Response getHealth200Response=new GetHealth200Response();
            getHealth200Response.setStatus("WORKING");

        return ResponseEntity.ok(getHealth200Response);
    }
}
