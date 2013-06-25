/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author roberto
 */
public class ObjetoResultado {
    private static ObjetoResultado objetoResultado;
    Resultado resultado;

    public static ObjetoResultado dameObjetoResultado(){
	if(objetoResultado == null)
		objetoResultado=new ObjetoResultado();
	return objetoResultado;
	}
    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    
}
