
package com.tarea.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.tarea.mock.entidades.Perro;
import com.tarea.mock.excepciones.PerroNoEncontradoException;
import com.tarea.mock.repositorios.PerroRepository;
import com.tarea.mock.servicios.PerroComunitarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PerroComunitarioServiceTest {

    PerroRepository mockRepository;
    PerroComunitarioService service;

    @BeforeEach
    public void inicializarPrueba(){
        // Mock del repositorio
        mockRepository = Mockito.mock(PerroRepository.class);
        // Servicio a probar
        service = new PerroComunitarioService(mockRepository);
    }

    @Test
    public void deberiaDevolverPerroCuandoElPerroExiste() {        
        // Verificación

        //Simula que el repositorio devuele un perro con nombre "Fido" y edad 4
        when(mockRepository.buscarPorNombre("Fido")).thenReturn(new Perro("Fido", 4));
        //Se guarda el resultado de buscar el nombre de el perro en PerroComunitarioService
        Perro resultado = service.obtenerPerroPorNombre("Fido");
        //Se verifica el nombre y la edad de el perro
        assertEquals("Fido", resultado.getNombre());//Se evalua si es igual el nombre devuelto a "Fido"
        assertEquals(4, resultado.getEdad());//Se evalua si es igual la edad devuelta a 4

    }
    
    @Test
    public void deberiaLanzarPerroNoEncontradoExceptioCuandoElPerroNoExiste() {        
        // Ejecución que lanza excepción
        //Simula que el repositorio no encuentra un perro con el nombre "Rex"
        when(mockRepository.buscarPorNombre("Rex")).thenReturn(null);
        //Se verifica la excepción devuelta "PerroNoEncontradoException"
        assertThrows(PerroNoEncontradoException.class,()->{
            //Se llama el metodo de service para buscar el nombre de "Rex"
            service.obtenerPerroPorNombre("Rex");    
        });
    }
    @Test
    public void deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsNull() {
        // Ejecución que lanza excepción
        //Se verifica la excepción devuelta "IllegalArgumentException"
        assertThrows(IllegalArgumentException.class, ()->{
            //Se pasa un argumento NULL a servicio
            service.obtenerPerroPorNombre(null);    
        });
    }
    @Test
    public void deberiaLanzarIllegalArgumentExceptionCuandoElNombreEsVacio() {
        // Ejecución que lanza excepción
        //Se verifica la excepción devuelta "IllegalArgumentException"
        assertThrows(IllegalArgumentException.class,()->{
            //Se pasa una cadena vacía como nombre a servicio
            service.obtenerPerroPorNombre("");
        });
    }
    @Test
    public void deberiaConsultarRepositorioUnaSolaVezCuandoElPerroExiste() {
        // Verificación
        //Simula que el repositorio devuelve un perro con nombre "Fido"
        when(mockRepository.buscarPorNombre("Fido")).thenReturn(new Perro("Fido", 4));
        //Se invoca a servicio y su metodo para buscar el nombre de el perro "Fido"
        service.obtenerPerroPorNombre("Fido");
        //Se verifica una UNICA consulta a el repositrio por parte de servicio
        verify(mockRepository, times(1)).buscarPorNombre("Fido");
    }
}
