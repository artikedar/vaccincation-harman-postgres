package com.harman.ebook.vaccination.covid.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * cdar-data-ingestion-services-v2
 *
 * @author Amarnath Pant
 * Created on 10/31/2019
 */
@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponseBean {
    /**
     * {
     * "timestamp": "2019-09-20T10:23:55.991+0000",
     * "status": http status code,
     * "error": "error code",
     * "message": "error message",
     * "path": "/nielsen/data-ingestion/supplier/step/xxx"
     * }
     */
    private String error;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
}