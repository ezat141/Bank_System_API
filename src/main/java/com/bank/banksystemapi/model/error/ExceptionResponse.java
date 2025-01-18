package com.bank.banksystemapi.model.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;


import java.util.Map;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private HttpStatus httpStatus;
    private String exceptionDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String ,String> errors;
}
