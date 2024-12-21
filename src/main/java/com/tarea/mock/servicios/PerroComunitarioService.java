package com.tarea.mock.servicios;

import com.tarea.mock.entidades.Perro;
import com.tarea.mock.excepciones.PerroNoEncontradoException;
import com.tarea.mock.repositorios.PerroRepository;

public class PerroComunitarioService {
    private final PerroRepository perroRepository;

    // Constructor para inyectar el repositorio
    public PerroComunitarioService(PerroRepository perroRepository) {
        this.perroRepository = perroRepository;
    }

    /**
     * Obtiene un perro por su nombre.
     *
     * @param nombre el nombre del perro a buscar
     * @return un objeto Perro si se encuentra en el repositorio
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     * @throws PerroNoEncontradoException si no se encuentra un perro con el nombre dado
     */
    public Perro obtenerPerroPorNombre(String nombre) {
        // Validación del nombre
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }

        // Consulta al repositorio
        Perro perro = perroRepository.buscarPorNombre(nombre);

        // Manejo si no se encuentra el perro
        if (perro == null) {
            throw new PerroNoEncontradoException("El perro con nombre " + nombre + " no fue encontrado.");
        }

        return perro;
    }
}
