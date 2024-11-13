package com.lista.tarefas.config.exceptions.handler;


import com.lista.tarefas.config.exceptions.BadRequestExecption;
import com.lista.tarefas.config.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> usuarionaoEncontrado(NotFoundException notFoundException, HttpServletRequest request, Locale locale){
        logger.error(notFoundException.getMessage(), notFoundException);
        final RestErrorMessage message= gerarRestErrorMessageDinamico(HttpStatus.NOT_FOUND, notFoundException, request, locale);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(BadRequestExecption.class)
    private ResponseEntity<RestErrorMessage> usuarionaoEncontrado(BadRequestExecption notFoundException, HttpServletRequest request, Locale locale){
        logger.error(notFoundException.getMessage(), notFoundException);
        final RestErrorMessage message= gerarRestErrorMessageDinamico(HttpStatus.BAD_REQUEST, notFoundException, request, locale);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }


    private RestErrorMessage gerarRestErrorMessageDinamico(HttpStatus httpStatus, Throwable ex, HttpServletRequest request, Locale locale) {
        return RestErrorMessage.builder()
                .status(httpStatus.value())
                .error(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .locale(locale)
                .timestamp(OffsetDateTime.now())
                .build();
    }

    private RestErrorMessage gerarRestErrorMessageErroInterno( Throwable ex, HttpServletRequest request, Locale locale) {
        return RestErrorMessage.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getClass().getSimpleName())
                .message("Houve um erro interno. Não se preocupe, já estamos solucionando o problema.")
                .path(request.getRequestURI())
                .locale(locale)
                .timestamp(OffsetDateTime.now())
                .build();
    }
}
