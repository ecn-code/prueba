/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pigmento {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty nombre = new SimpleStringProperty("");

    public Pigmento() {
        this(0, "");
    }

    public Pigmento(int id, String pigmento) {
        setId(id);
        setNombre(pigmento);
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

}