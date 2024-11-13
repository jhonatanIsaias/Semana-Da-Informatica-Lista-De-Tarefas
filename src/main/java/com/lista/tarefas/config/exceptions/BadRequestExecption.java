package com.lista.tarefas.config.exceptions;

public class BadRequestExecption extends RuntimeException{
    public BadRequestExecption(String message) {
        super(message);
    }
}
