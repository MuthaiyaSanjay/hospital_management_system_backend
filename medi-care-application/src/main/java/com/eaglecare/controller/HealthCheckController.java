package com.eaglecare.controller;


import com.eaglecare.api.HealthApi;
import com.eaglecare.model.GetHealth200Response;
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
