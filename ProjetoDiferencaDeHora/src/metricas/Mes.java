/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metricas;

import exception.ExceptionDiaInvalido;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TipoMes;

/**
 *
 * @author andre
 */
public class Mes {
    private final int horaDeTrabalho = 6*22;
    private List<Dia> dias;
    private TipoMes mes;

    public Mes(TipoMes mes) {
        this.dias = new ArrayList<Dia>();
        this.mes = mes;
    }

    public Mes() {
        this.dias = new ArrayList<Dia>();
        int numMes = (int) Calendar.getInstance().get(Calendar.MONTH);
        this.mes = TipoMes.values()[numMes];
    }

    public float calcularHorasDoMes() {
        float horas = 0;
        if (this.dias != null) {
            for (Dia dia : dias) {
                try {
                    horas +=
                            dia.verificarHoras();
                } catch (ExceptionDiaInvalido ex) {
                   System.out.println(ex.getMessage());
                }
            }
        }
        return horas;
    }
    
    @Override
    public String toString(){
        StringBuffer strb = new StringBuffer();
        for(Dia dia : this.dias){
            strb.append(dia.toString());
            strb.append("\n");
        }
        strb.append("\n\n");
        strb.append("Mes de "+this.mes.name()+"\n"+"Horas trabalhadas:"+this.calcularHorasDoMes());
        return strb.toString();
    }
}
