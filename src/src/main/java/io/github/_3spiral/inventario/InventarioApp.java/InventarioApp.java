package io.github._3spiral.inventario;

import java.util.HashMap;
import java.util.Map;

public class InventarioApp {

    public enum TipoMovimiento {
        ENTRADA,
        SALIDA
    }

    public static class StockInsuficienteException
            extends RuntimeException {
        public StockInsuficienteException(String message) {
            super(message);
        }
    }

    public static class Movimiento {
        private final char productoId;
        private final TipoMovimiento tipo;
        private final int cantidad;

        public Movimiento(
                char productoId,
                TipoMovimiento tipo,
                int cantidad) {

            if (tipo == null) {
                throw new IllegalArgumentException("El tipo de movimiento es obligatorio.");
            }

            if (cantidad <= 0) {
                throw new IllegalArgumentException("Cantidad inválida.");
            }

            this.productoId = productoId;
            this.tipo = tipo;
            this.cantidad = cantidad;
        }
    }

    public static Map<Character, Integer> calcularInventario(Movimiento[] movimientos) {
        Map<Character, Integer> inventario = new HashMap<>();

        for (Movimiento movimiento : movimientos) {

            char productoActual = movimiento.productoId;
            int stockActual = inventario.getOrDefault(productoActual, 0);

            if (movimiento.tipo == TipoMovimiento.ENTRADA) {
                inventario.put(productoActual, stockActual + movimiento.cantidad);
            } else {
                if (stockActual < movimiento.cantidad) {
                    throw new StockInsuficienteException("No hay suficiente stock para el producto " 
                    + productoActual+ "\nStock actual: " + stockActual + "\nCantidad solicitada: " + movimiento.cantidad);

                } 
                inventario.put(productoActual, stockActual - movimiento.cantidad);
            }
        }
        return inventario;
    }

    public static void main(String[] args) {
        try {
            Movimiento[] movimientos = {
                    new Movimiento(
                            'A',
                            TipoMovimiento.ENTRADA,
                            10),
                    new Movimiento(
                            'B',
                            TipoMovimiento.ENTRADA,
                            5),
                    new Movimiento(
                            'A',
                            TipoMovimiento.SALIDA,
                            3)
            };

            Map<Character, Integer> inventario = calcularInventario(movimientos);

            System.out.println("Inventario final:");

            for (Map.Entry<Character, Integer> entry : inventario.entrySet()) {
                System.out.println(
                        "Producto " + entry.getKey()
                                + ": " + entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Datos inválidos: " + e.getMessage());
        } catch (StockInsuficienteException e) {
            System.out.println(
                    "Operación rechazada: " + e.getMessage());
        }

        try {
            //Prueba error de stock insuficiente
            Movimiento[] movimientos = {
                    new Movimiento(
                            'B',
                            TipoMovimiento.SALIDA,
                            8)
            };

            Map<Character, Integer> inventario2 = calcularInventario(movimientos);

            System.out.println("Inventario final 2:");

            for (Map.Entry<Character, Integer> entry : inventario2.entrySet()) {
                System.out.println(
                        "Producto " + entry.getKey()
                                + ": " + entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Datos inválidos: " + e.getMessage());
        } catch (StockInsuficienteException e) {
            System.out.println(
                    "Operación rechazada: " + e.getMessage());
        }



        try {
            //Prueba error de cantidad inválida
            Movimiento[] movimientos = {
                    new Movimiento(
                            'C',
                            TipoMovimiento.ENTRADA,
                            0)
            };

            Map<Character, Integer> inventario3 = calcularInventario(movimientos);

            System.out.println("Inventario final 3:");

            for (Map.Entry<Character, Integer> entry : inventario3.entrySet()) {
                System.out.println(
                        "Producto " + entry.getKey()
                                + ": " + entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Datos inválidos: " + e.getMessage());
        } catch (StockInsuficienteException e) {
            System.out.println(
                    "Operación rechazada: " + e.getMessage());
        }
    }
}