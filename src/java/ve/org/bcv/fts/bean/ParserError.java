/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.bean;

import java.io.Serializable;

/**
 * 
 * @author furibe
 */
public class ParserError implements Serializable{
    private int linea;
    private String fieldname;
    private int indicefield;
    private String dato;

    public ParserError() {
        linea=0;
        fieldname="";
        indicefield=0;
        dato="";
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public int getIndicefield() {
        return indicefield;
    }

    public void setIndicefield(int indicefield) {
        this.indicefield = indicefield;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "ParserError{" + "linea=" + linea + ", fieldname=" + fieldname + ", indicefield=" + indicefield + ", dato=" + dato + '}';
    }
    
    
    
}

