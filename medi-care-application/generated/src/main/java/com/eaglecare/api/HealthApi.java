/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.medicare.api;

import com.medicare.model.GetHealth200Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-12T00:43:21.532621800+05:30[Asia/Calcutta]")
@Validated
@Tag(name = "Health", description = "Applicaiton health check")
public interface HealthApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /v1/api/health : Health check endpoint
     * Returns the health status of the API service.
     *
     * @return Health check successful (status code 200)
     *         or Internal server error (status code 500)
     */
    @Operation(
        operationId = "getHealth",
        summary = "Health check endpoint",
        tags = { "Health" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Health check successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = GetHealth200Response.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/v1/api/health",
        produces = { "application/json" }
    )
    default ResponseEntity<GetHealth200Response> _getHealth(
        
    ) {
        return getHealth();
    }

    // Override this method
    default  ResponseEntity<GetHealth200Response> getHealth() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"status\" : \"UP\", \"timestamp\" : \"2024-08-04T00:00:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}