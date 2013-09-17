/*
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package logica;


import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Resultado {
    private final SimpleStringProperty productoSeleccionado = new SimpleStringProperty("");
    private final SimpleStringProperty base1 = new SimpleStringProperty("");
    private final SimpleStringProperty cant1 = new SimpleStringProperty("");
     private final SimpleStringProperty base2 = new SimpleStringProperty("");
    private final SimpleStringProperty cant2 = new SimpleStringProperty("");
    private final SimpleStringProperty base3 = new SimpleStringProperty("");
    private final SimpleStringProperty cant3 = new SimpleStringProperty("");
    private final SimpleStringProperty base4 = new SimpleStringProperty("");
    private final SimpleStringProperty cant4 = new SimpleStringProperty("");
    private final SimpleStringProperty total = new SimpleStringProperty("");
    public Resultado() {
        this("","");
    }

    public Resultado(String nombre,String cantidad) {
        setBase1(nombre);
        setCant1(cantidad);
    }

    public String getBase1() {
        return base1.get();
    }
     public void setCant1(String fName) {
        cant1.set(fName);
    }

    public String getCant1() {
        return cant1.get();
    }

    public void setBase1(String fName) {
        base1.set(fName);
    }
     @Override public String toString() {
     return base1.get();
     }
     public String getBase2() {
        return base2.get();
    }
     public void setCant2(String fName) {
        cant2.set(fName);
    }

    public String getCant2() {
        return cant2.get();
    }

    public void setBase2(String fName) {
        base2.set(fName);
    }
   public String getBase3() {
        return base3.get();
    }
     public void setCant3(String fName) {
        cant3.set(fName);
    }

    public String getCant3() {
        return cant3.get();
    }

    public void setBase3(String fName) {
        base3.set(fName);
    }
     public String getBase4() {
        return base4.get();
    }
     public void setCant4(String fName) {
        cant4.set(fName);
    }
 public void setProductoSeleccionado(String fName) {
        productoSeleccionado.set(fName);
    }
    public String getProductoSeleccionado() {
        return productoSeleccionado.get();
    }
    public String getCant4() {
        return cant4.get();
    }

    public void setBase4(String fName) {
        base4.set(fName);
    }
    public String getTotal() {
    return total.get();
    }

    public void setTotal(String fName) {
        total.set(fName);
    }
    
}