package mx.uam.azc.Modelo;

/**
 *
 * @author Victor
 */
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items = new ArrayList<>();
    private List<CarritoObservador> observers = new ArrayList<>();

    public void addObserver(CarritoObservador observer) {
        observers.add(observer);
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                int cantidadAnterior = item.getCantidad();
                item.setCantidad(cantidadAnterior + cantidad);
                notificarActualizacion(producto, cantidadAnterior, item.getCantidad());
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
        notificarAgregado(producto, cantidad);
    }

    public void eliminarProducto(int idProducto) {
        items.removeIf(item -> {
            if (item.getProducto().getId() == idProducto) {
                notificarEliminado(item.getProducto(), item.getCantidad());
                return true;
            }
            return false;
        });
    }

    public void actualizarCantidad(int idProducto, int cantidadNueva) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == idProducto) {
                int cantidadAnterior = item.getCantidad();
                item.setCantidad(cantidadNueva);
                notificarActualizacion(item.getProducto(), cantidadAnterior, cantidadNueva);
                return;
            }
        }
    }

    private void notificarAgregado(Producto producto, int cantidad) {
        for (CarritoObservador o : observers) {
            o.onProductoAgregado(producto, cantidad);
        }
    }

    private void notificarEliminado(Producto producto, int cantidad) {
        for (CarritoObservador o : observers) {
            o.onProductoEliminado(producto, cantidad);
        }
    }

    private void notificarActualizacion(Producto producto, int cantidadAnterior, int cantidadNueva) {
        for (CarritoObservador o : observers) {
            o.onProductoActualizado(producto, cantidadAnterior, cantidadNueva);
        }
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}


