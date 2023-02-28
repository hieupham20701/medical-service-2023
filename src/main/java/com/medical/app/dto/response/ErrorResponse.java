package com.medical.app.dto.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private Date timeStamp;

    private String message;

    private String error;
}
