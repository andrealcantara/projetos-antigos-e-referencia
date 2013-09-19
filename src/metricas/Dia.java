/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metricas;

import exception.ExceptionDiaInvalido;
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
        this.dia = "" + cal.get(Calendar.DAY_OF_MONTH);
        this.mes = TipoMes.values()[cal.get(Calendar.MONTH)];
        this.diaValido = true;
        this.ano = "2013";
        this.entradas = new ArrayList<Calendar>();
    }

    private void verificarValidade() {
        this.diaValido = (!this.entradas.isEmpty() && this.entradas.size() % 2 == 0);
    }

    private int diferencaHora(Calendar oneDay, Calendar twoDay) {
        int retorno;
        int diffHoras = (int) (oneDay.getTime().getTime()
                - twoDay.getTime().getTime()) / 1000;
        retorno = (int)(diffHoras / 60);
        return retorno;
    }

    private Calendar makeDay() throws NumberFormatException {
        Calendar day = Calendar.getInstance();
        day.set(Integer.parseInt(this.ano), this.mes.ordinal(),
                Integer.parseInt(this.dia));
        return day;
    }

    /**
     * Retorna a quantidade de horas trabalhadas em minutos
     *
     * @return int referente a tempo
     */
    public int verificarMinutos() throws ExceptionDiaInvalido {
        this.verificarValidade();
        if(!this.diaValido){
            throw new ExceptionDiaInvalido("Dia Invalido\n"+this);
        }
        int minutosExtrasDebito = 0;
        for (int i = 0;i < this.entradas.size() - 1; i = i + 2) {
            minutosExtrasDebito += this.diferencaHora(
                    this.entradas.get(i),
                    this.entradas.get(i + 1));
        }
        return minutosExtrasDebito;
    }

    /**
     * Retorna a quantidade de horas trabalhadas em horas.
     *
     * @return float referente a tempo
     */
    public float verificarHoras() throws ExceptionDiaInvalido {
        return (float) (this.verificarMinutos() / 60);
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

    @Override
    public String toString() { 
        String str = "";
        try {
            str = "Dia:" + this.dia + " Mes:" + this.mes.name()
                    + "\n HorasExtras:" + this.verificarHoras();
        } catch (ExceptionDiaInvalido ex) {}
        return str;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.dia != null ? this.dia.hashCode() : 0);
        hash = 89 * hash + (this.mes != null ? this.mes.hashCode() : 0);
        hash = 89 * hash + (this.ano != null ? this.ano.hashCode() : 0);
        hash = 89 * hash + (this.diaValido ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dia other = (Dia) obj;
        if ((this.dia == null) ? (other.dia != null) : !this.dia.equals(other.dia)) {
            return false;
        }
        if (this.mes != other.mes) {
            return false;
        }
        if ((this.ano == null) ? (other.ano != null) : !this.ano.equals(other.ano)) {
            return false;
        }
        return true;
    }
}
