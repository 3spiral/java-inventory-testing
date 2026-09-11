package io.github._3spiral.inventario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MovimientoTest {

    @Test
    void debeCrearMovimientoConDatosValidos() {
        // Arrange y Act
        Movimiento movimiento = new Movimiento('A', TipoMovimiento.ENTRADA, 10);


        // Assert
        assertEquals('A', movimiento.getProductoId());
        assertEquals(TipoMovimiento.ENTRADA, movimiento.getTipo());
        assertEquals(10, movimiento.getCantidad());

    }

    @Test void debeRechazarTipoNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Movimiento('A', null, 10);
        });

        assertEquals("El tipo de movimiento es obligatorio.", exception.getMessage());
    }

    @Test void debeRechazarCantidadCero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Movimiento('A', TipoMovimiento.ENTRADA, 0);
        });

        assertEquals("Cantidad inválida.", exception.getMessage());
    }

    @Test void debeRechazarCantidadNegativa() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Movimiento('A', TipoMovimiento.ENTRADA, -5);
        });

        assertEquals("Cantidad inválida.", exception.getMessage());
    }

}   