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
    private int minutosExtrasDebito;
    private boolean diaValido;

    public Dia(String data, String mes) {
        this.dia = data;
        this.mes = mes;
        this.minutosExtrasDebito = 0;
        this.diaValido = true;
        this.ano = "2013";
        this.entradas = new ArrayList<Calendar>();
    }

    public Dia() {
        this.dia = "";
        this.mes = "";
        this.minutosExtrasDebito = 0;
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
        this.minutosExtrasDebito = -360;
        this.verificarValidade();
        for (int i = 0; this.diaValido && i < this.entradas.size() - 1; i = i + 2) {
            this.minutosExtrasDebito += this.diferencaHora(
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
     * @return the minutosExtrasDebito
     */
    public int mostrarMinutosExtrasOuDebitoEmMinutos(){
        return (int) this.minutosExtrasDebito;
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
    public String getMes() {
        return this.mes;
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
     * @param minutosExtrasDebito to minutosExtrasDebito set
     */
    private void setMinutosExtrasDebito(int minutosExtrasDebito) {
        this.minutosExtrasDebito = minutosExtrasDebito;
    }
    
    /**
     * @return the minutosExtrasDebito
     */
    private int getMinutosExtrasDebito() {
        return this.minutosExtrasDebito;
    }

    /**
     * @param ano the ano to set
     */
    private void setAno(String ano) {
        this.ano = ano;
    }

    
}
