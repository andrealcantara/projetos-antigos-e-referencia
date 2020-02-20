/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author andre.alcantara
 */
public class ExceptionDiaInvalido extends Exception {
    
    public ExceptionDiaInvalido(String msg){
        super(msg);
    }
    
    public ExceptionDiaInvalido(){
        super("Dia invalido");
    }
    
}
