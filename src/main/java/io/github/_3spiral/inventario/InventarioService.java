package io.github._3spiral.inventario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class InventarioService {

    public Map<Character, Integer> calcularInventario(List<Movimiento> movimientos) {

        if (movimientos == null) {
            throw new IllegalArgumentException("La lista de movimientos es obligatoria.");
        }
        
        Map<Character, Integer> inventario = new HashMap<>();
        
        for (Movimiento movimiento : movimientos) {

            if (movimiento == null) {
                throw new IllegalArgumentException("La lista no puede contener movimientos nulos.");
            }

            char productoActual = movimiento.getProductoId();
            int stockActual = inventario.getOrDefault(productoActual, 0);

            if (movimiento.getTipo() == TipoMovimiento.ENTRADA) {
                inventario.put(productoActual, stockActual + movimiento.getCantidad());
            } else {
                if (stockActual < movimiento.getCantidad()) {
                    throw new StockInsuficienteException("No hay suficiente stock para el producto " 
                    + productoActual+ "\nStock actual: " + stockActual + "\nCantidad solicitada: " + movimiento.getCantidad());

                } 
                inventario.put(productoActual, stockActual - movimiento.getCantidad());
            }
        }
        return inventario;
    }
}