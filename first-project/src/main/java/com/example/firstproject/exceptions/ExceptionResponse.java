package com.example.firstproject.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExceptionResponse implements Serializable {
    private LocalDateTime date;
    private String title;
    private String details;

    public ExceptionResponse(LocalDateTime date, String title, String details) {
        this.date = date;
        this.title = title;
        this.details = details;
    }
}
