package com.im.modals;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
