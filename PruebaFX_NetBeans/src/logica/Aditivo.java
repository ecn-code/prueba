/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Aditivo {

    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private final SimpleDoubleProperty cantidad = new SimpleDoubleProperty(0.0);

    public Aditivo() {
        this("");
    }

    public Aditivo(String nombre) {
        setNombre(nombre);
       
    }

    /**
     *
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     *
     * @param fName
     */
    public void setNombre(String fName) {
        nombre.set(fName);
    }

    public Double getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(Double fName) {
        cantidad.set(fName);
    }
    @Override public String toString() {
     return nombre.get();
     }
}