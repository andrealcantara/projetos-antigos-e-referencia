/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diferencadehoras;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author andre.alcantara
 */
public class Dia {

    private String dia;
    private String mes;
    private String ano;
    private List<Calendar> entradas;
    private float horasExtrasDebito;
    private boolean diaValido;

    public Dia(String data, String mes) {
        this.dia = data;
        this.mes = mes;
        this.horasExtrasDebito = 0;
        this.diaValido = true;
        this.ano = "2013";
        this.entradas = new ArrayList<Calendar>();
    }

    public Dia() {
        this.dia = "";
        this.mes = "";
        this.horasExtrasDebito = 0;
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
        day.set(Integer.parseInt(this.ano),
                Integer.parseInt(this.mes)-1,
                Integer.parseInt(this.dia));
        return day;
    }

    public void verificarHoras() {
        this.horasExtrasDebito = -360;
        this.verificarValidade();
        for (int i = 0; this.diaValido && i < this.entradas.size() - 1; i = i + 2) {
            this.horasExtrasDebito += this.diferencaHora(
                    this.entradas.get(i),
                    this.entradas.get(i + 1));
        }
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
        return diaValido;
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
        return dia;
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
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @return the entradas
     */
    public List<Calendar> getEntradas() {
        return entradas;
    }

    /**
     * @param entradas the entradas to set
     */
    private void setEntradas(List<Calendar> entradas) {
        this.entradas = entradas;
    }

    public int mostrarHorasExtrasOuDebitoEmMinutos(){
        return (int) this.horasExtrasDebito;
    }
}
