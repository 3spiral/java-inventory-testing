package io.github._3spiral.inventario;


import java.util.List;
import java.util.Map;

public class InventarioApp {

        public static void main(String[] args) {
                try {
                        List<Movimiento> movimientos = List.of(
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
                                                        3));

                        InventarioService inventarioService = new InventarioService();

                        Map<Character, Integer> inventario = inventarioService.calcularInventario(movimientos);

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

                
        }
}