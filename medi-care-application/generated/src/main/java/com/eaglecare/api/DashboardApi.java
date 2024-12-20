/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.eaglecare.api;

import com.eaglecare.model.Summary;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-12T00:33:25.588524200+05:30[Asia/Calcutta]")
@Validated
@Tag(name = "Dashboard", description = "Maganment")
public interface DashboardApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /v1/api/dashboard : Get summary details for logined user
     * Return the list values
     *
     * @param userId User Id (optional, default to available)
     * @return successful operation (status code 200)
     *         or Invalid user id (status code 400)
     */
    @Operation(
        operationId = "getSummaryForLoginedUser",
        summary = "Get summary details for logined user",
        tags = { "Dashboard" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Summary.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid user id")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/v1/api/dashboard",
        produces = { "application/json" }
    )
    default ResponseEntity<Summary> _getSummaryForLoginedUser(
        @Parameter(name = "userId", description = "User Id") @Valid @RequestParam(value = "userId", required = false, defaultValue = "available") String userId
    ) {
        return getSummaryForLoginedUser(userId);
    }

    // Override this method
    default  ResponseEntity<Summary> getSummaryForLoginedUser(String userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"current_month_cxpense\" : 15963.5, \"total_doctors\" : 10, \"total_patients\" : 10, \"pending_payaments\" : 10 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
