/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Base {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
    private ArrayList<Aditivo> aditivos = new ArrayList<Aditivo>();

    public Base() {
        this(0, "");
    }

    public Base(int id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public int getId() {
        return id.get();
    }

    /**
     *
     * @param fName
     */
    public void setId(int fName) {
        id.set(fName);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String fName) {
        nombre.set(fName);
    }
    public void setAditivos(ArrayList<Aditivo> array){
        aditivos = array;
    }
     public ArrayList<Aditivo> getAditivos(){
        return aditivos ;
    }
}