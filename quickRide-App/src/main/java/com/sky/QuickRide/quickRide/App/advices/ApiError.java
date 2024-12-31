package com.sky.QuickRide.quickRide.App.advices;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
@Builder
@Getter
@Setter

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
