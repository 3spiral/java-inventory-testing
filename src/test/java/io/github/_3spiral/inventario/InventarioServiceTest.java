package io.github._3spiral.inventario;

import org.junit.jupiter.api.Test;  

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class InventarioServiceTest {


    private final InventarioService inventarioService = new InventarioService();

    @Test
    void debeCalcularInventarioCuandoLosElementosSonValidos() {
        // Arrange
        List<Movimiento> movimientos = List.of (
                new Movimiento('A', TipoMovimiento.ENTRADA, 10),
                new Movimiento('B', TipoMovimiento.ENTRADA, 5),
                new Movimiento('A', TipoMovimiento.SALIDA, 3),
                new Movimiento('C', TipoMovimiento.ENTRADA, 12)
        );

        Map<Character, Integer> inventarioEsperado = Map.of(
                'A', 7,
                'B', 5,
                'C', 12
        );

        // Act
        Map<Character, Integer> inventarioCalculado = inventarioService.calcularInventario(movimientos);

        // Assert
        assertEquals(inventarioEsperado, inventarioCalculado);

    }

    @Test
    void debePermitirRetirarExactamenteTodoElStockDisponible() {
        // Arrange
        List<Movimiento> movimientos = List.of(
                new Movimiento('A', TipoMovimiento.ENTRADA, 5),
                new Movimiento('A', TipoMovimiento.SALIDA, 5)
        );

        Map<Character,Integer> inventarioEsperado = Map.of( 'A', 0);
        // Act
        Map<Character, Integer> inventarioCalculado = inventarioService.calcularInventario(movimientos);
        
        // Assert
        assertEquals(inventarioEsperado, inventarioCalculado);

    }

    @Test 
    void debeLanzarExcepcionCuandoElStockEsInsuficiente(){

        // Arrange
        List<Movimiento> movimientos = List.of(
            new  Movimiento('A', TipoMovimiento.ENTRADA, 5),
            new Movimiento('A', TipoMovimiento.SALIDA, 8)
        );

        // Act
        StockInsuficienteException exception = assertThrows(StockInsuficienteException.class, () -> {
            inventarioService.calcularInventario(movimientos);
        });

        // Assert
        assertTrue(exception.getMessage().contains("A"));
        assertTrue(exception.getMessage().contains("5"));
        assertTrue(exception.getMessage().contains("8"));

    }

    @Test
    void debeRetornarInventarioVacioCuandoNoHayMovimientos(){

        // Arrange
        List <Movimiento> movimientos = List.of();

        Map<Character, Integer> inventarioEsperado = Map.of();
        
        // Act
        Map<Character, Integer> inventarioCalculado = inventarioService.calcularInventario(movimientos);

        // Assert
        assertEquals(inventarioEsperado, inventarioCalculado);
    }

    @Test
    void debeRechazarUnaListaNula() {

        //Arrange & Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventarioService.calcularInventario(null);
        });

        // Assert
        assertEquals("La lista de movimientos es obligatoria.", exception.getMessage());

    }

    @Test
    void debeRechazarUnMovimientoNulo() {
        //Arrange
        List<Movimiento> movimientos = new ArrayList<>();
        movimientos.add(null);

        //Act  
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventarioService.calcularInventario(movimientos);
        });

        // Assert
        assertEquals("La lista no puede contener movimientos nulos.", exception.getMessage());
    }
}