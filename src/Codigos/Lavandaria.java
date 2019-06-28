/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigos;

import java.io.Serializable;

/**
 *
 * @author Francy
 */
public class Lavandaria implements Serializable{
    private String tipo;
    private String lavandaria;
    private boolean estado;
    private int codigo;
    private String cor;

    public Lavandaria() {
    }

    public Lavandaria(String tipo, String lavandaria, int codigo, String cor) {
        this.tipo = tipo;
        this.lavandaria = lavandaria;
        this.codigo = codigo;
        this.cor = cor;
    }

   
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLavandaria() {
        return lavandaria;
    }

    public void setLavandaria(String lavandaria) {
        this.lavandaria = lavandaria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    
    
}
