package com.stock.market.StockMarketTool.exceptions;

import com.stock.market.StockMarketTool.services.CSVUploadException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Provides handling for exceptions throughout this service.
     *
     * @return
     */
    @ExceptionHandler({CSVUploadException.class})
    public final ResponseEntity<String> handleUnauthorizedException(CSVUploadException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }


    /**
     * A single place to customize the response body of all Exception types.
     *
     * @return
     */
    private ResponseEntity<String> handleExceptionInternal(Exception ex, String body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
