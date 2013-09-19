/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metricas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import util.TipoMes;

/**
 *
 * @author andre
 */
public class Mes {
    private float horasDebitoCredito;
    private List<Dia> dias;
    private TipoMes mes;
    
    public Mes(TipoMes mes){
        this.horasDebitoCredito = -1*6*22;
        this.dias = new ArrayList<Dia>();
        this.mes = mes;
    }
    
    public Mes(){
        this.horasDebitoCredito = -1*6*22;
        this.dias = new ArrayList<Dia>();
        int numMes = (int) Calendar.getInstance().get(Calendar.MONTH);
        this.mes = TipoMes.values()[numMes];
    }
    
    
    public void calcularHorasDoMes(){
        for(Dia dia : dias){
            this.horasDebitoCredito += 
                    dia.verificarHoras();
        }
    }
}
