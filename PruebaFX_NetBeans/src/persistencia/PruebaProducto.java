/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import excepciones.DAOExcepcion;
import java.util.ArrayList;
import logica.Producto;

/**
 *
 * @author Elias
 */
public class PruebaProducto {
    public static void main(String[] args) throws DAOExcepcion {
       IProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> productos = productoDAO.getProductos();
        for(Producto producto : productos) System.out.println(producto.getNombre());
    
    Producto producto = productoDAO.getProducto("M.E");
        System.out.println(producto.getFactor());
    
    Producto producto2 = new Producto(4,"Pepe",0.5);
    //productoDAO.insertarProducto(producto2);
    
    productoDAO.eliminarProducto(producto2);
    
    }
    
}
