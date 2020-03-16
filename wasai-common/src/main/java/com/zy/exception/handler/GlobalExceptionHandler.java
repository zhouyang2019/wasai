package com.zy.exception.handler;

import com.zy.exception.BaseException;
import com.zy.support.ApiReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity handleBaseException(BaseException e) {
        logger.error(e.getMessage(), e);
        ApiReturn apiReturn = new ApiReturn().buildFail(e.getCode(), e.getMessage());
        return new ResponseEntity<>(apiReturn, resolveAnnotatedResponseStatus(e));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity handleUnexpectedException(Exception e) {
        logger.error(e.getMessage(), e);
        ApiReturn apiReturn = new ApiReturn().buildFail("internal server error", e.getMessage());
        return new ResponseEntity<>(apiReturn, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus resolveAnnotatedResponseStatus(BaseException e) {
        ResponseStatus annotation = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
