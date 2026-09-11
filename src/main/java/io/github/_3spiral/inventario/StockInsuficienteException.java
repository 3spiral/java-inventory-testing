package io.github._3spiral.inventario;

public class StockInsuficienteException
        extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
