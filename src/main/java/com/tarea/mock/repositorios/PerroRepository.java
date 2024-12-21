package com.tarea.mock.repositorios;

import com.tarea.mock.entidades.Perro;

public interface PerroRepository {
    Perro buscarPorNombre(String nombre);
}
