/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import metricas.Dia;

/**
 *
 * @author andre.alcantara
 */
public class DiferencaDeHoras {

    private List<Dia> diasTrabalhados;
    private float horaDebitoTotal;
    private float horaExtraTotal;
    
    private DiferencaDeHoras(){
        this.horaDebitoTotal = -1;
        this.horaExtraTotal = -1;
    }
}
