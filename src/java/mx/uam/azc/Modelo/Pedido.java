/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author CASH
 */
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private int idUsuario;
    private Date fechaPedido;
    private double total;
    private List<ItemCarrito> detalles;

    public Pedido() {}

    public Pedido(int id, int idUsuario, Date fechaPedido, double total, List<ItemCarrito> detalles) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.detalles = detalles;
    }

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public int getIdUsuario() { 
        return idUsuario; 
    }
    public void setIdUsuario(int idUsuario) { 
        this.idUsuario = idUsuario; 
    }

    public Date getFechaPedido() { 
        return fechaPedido; 
    }
    public void setFechaPedido(Date fechaPedido) { 
        this.fechaPedido = fechaPedido; 
    }

    public double getTotal() { 
        return total; 
    }
    public void setTotal(double total) { 
        this.total = total; 
    }

    public List<ItemCarrito> getDetalles() { 
        return detalles; 
    }
    public void setDetalles(List<ItemCarrito> detalles) { 
        this.detalles = detalles; 
    }
}

