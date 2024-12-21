package com.tarea.mock.excepciones;

public class PerroNoEncontradoException extends RuntimeException {
    public PerroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}