/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uam.azc.Modelo;

/**
 *
 * @author lopez
 */
public class Usuario {
    private int idUsr;
    private String nombre;
    private String correo;
    private String password;
    private String tipoUsr;

    public Usuario(int idUsr, String nombre, String correo, String password, String tipoUsr) {
        this.idUsr = idUsr;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.tipoUsr = tipoUsr;
    }

    public int getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsr() {
        return tipoUsr;
    }

    public void setTipoUsr(String tipoUsr) {
        this.tipoUsr = tipoUsr;
    }

    @Override
    public String toString() {
        return  idUsr + ", " + nombre + ", " + correo + ", " + password + ", " + tipoUsr;
    }
    
    
    
}
