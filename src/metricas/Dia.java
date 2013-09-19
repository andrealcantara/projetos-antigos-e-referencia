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
 * @author andre.alcantara
 */
public class Dia {

    private String dia;
    private TipoMes mes;
    private String ano;
    private List<Calendar> entradas;
    private boolean diaValido;

    public Dia(String data, TipoMes mes) {
        this.dia = data;
        this.mes = mes;
        this.diaValido = true;
        this.ano = "2013";
        this.entradas = new ArrayList<Calendar>();
    }

    public Dia() {
        Calendar cal = Calendar.getInstance();
        this.dia = ""+cal.get(Calendar.DAY_OF_MONTH);
        this.mes = TipoMes.values()[cal.get(Calendar.MONTH)];
        this.diaValido = true;
        this.ano = "2013";
        this.entradas = new ArrayList<Calendar>();
    }

    private void verificarValidade() {
        this.diaValido = (!this.entradas.isEmpty() && this.entradas.size() % 2 == 0);
    }

    private int diferencaHora(Calendar oneDay, Calendar twoDay) {
        int retorno = 0;
        int diffHoras = (int) 
                (oneDay.getTime().getTime() - 
                 twoDay.getTime().getTime()) / 1000;
//        float diferencaHoras = oneDay.get(Calendar.MINUTE) - twoDay.get(Calendar.MINUTE);
        retorno = diffHoras / 60;
        return retorno;
    }

    private Calendar makeDay() throws NumberFormatException {
        Calendar day = Calendar.getInstance();
        day.set(Integer.parseInt(this.ano),this.mes.ordinal(),
                Integer.parseInt(this.dia));
        return day;
    }
    /**
     * Retorna a quantidade de horas trabalhadas em minutos
     * @return int referente a tempo
     */
    public int verificarMinutos() {
        int minutosExtrasDebito = 0;
        this.verificarValidade();
        for (int i = 0; this.diaValido && i < this.entradas.size() - 1; i = i + 2) {
            minutosExtrasDebito += this.diferencaHora(
                    this.entradas.get(i),
                    this.entradas.get(i + 1));
        }
        return minutosExtrasDebito;
    }
    
    /**
     * Retorna a quantidade de horas trabalhadas em horas.
     * @return float referente a tempo
     */
    public float verificarHoras(){
        return (float)(this.verificarMinutos()/60);
    }

    /**
     * O mes começa em zero.
     *
     * @param hora - hora da entrada.
     * @param min - minuto da entrada;
     */
    public void addEntradasHoras(int hora, int min) {
        Calendar day = makeDay();
        day.set(Calendar.HOUR, hora);
        day.set(Calendar.MINUTE, min);
        this.entradas.add(day);
        this.verificarValidade();
    }
    
    /**
     * @return the diaValido
     */
    public boolean isDiaValido() {
        return this.diaValido;
    }

    /**
     * @param diaValido
     */
    public void setDiaValido(boolean diaValido) {
        this.diaValido = diaValido;
    }

    /**
     * @return the Dia
     */
    public String getDia() {
        return this.dia;
    }

    /**
     * @param data the data to set
     */
    public void setDia(String Dia) {
        this.dia = Dia;
    }

    /**
     * @return the mes
     */
    public TipoMes getMes() {
        return this.mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(TipoMes mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return this.ano;
    }

    /**
     * @return the entradas
     */
    public List<Calendar> getEntradas() {
        return this.entradas;
    }

    /**
     * @param entradas the entradas to set
     */
    private void setEntradas(List<Calendar> entradas) {
        this.entradas = entradas;
    }

    /**
     * @param ano the ano to set
     */
    private void setAno(String ano) {
        this.ano = ano;
    }

    
}
