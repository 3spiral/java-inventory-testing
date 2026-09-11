package io.github._3spiral.inventario;

public final class Movimiento {
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

    public char getProductoId() {
        return productoId;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }
}
