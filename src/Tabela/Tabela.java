/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabela;

import Codigos.Lavandaria;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Francy
 */
public class Tabela extends AbstractTableModel {
 
    
    protected ArrayList<Lavandaria> linhas;
    protected String[] colunas;

    public Tabela(ArrayList<Lavandaria> linhas, String[] colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
    }
    
       public void setColunas(String [] colunas){
        this.colunas = colunas;
        fireTableStructureChanged();
    }

   
    public ArrayList<Lavandaria> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<Lavandaria> linhas) {
        this.linhas = linhas;
        this.fireTableDataChanged();
    }

    public void addItem(Lavandaria lavandaria){
        linhas.add(lavandaria);
        fireTableRowsInserted(linhas.size()-1, linhas.size()-1);
    }
    
    
    public String[] getColunas() {
        return colunas;
    }

   
    public void actualizar(int linha, Lavandaria lavandaria){
        linhas.set(linha, lavandaria);
        fireTableRowsUpdated(linha, linha);
    }

    public void setListaDeItem(ArrayList<Lavandaria> lavandarias){
        linhas = lavandarias;
        fireTableDataChanged();
    }
    
    public Lavandaria getItem(int linha){
        return linhas.get(linha);
    }
    
    public void remover(int linha){
        linhas.remove(linha);
        fireTableRowsDeleted(linha, linha);
    }
    
     @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lavandaria lavandaria = (Lavandaria) linhas.get(rowIndex);
        switch(columnIndex){
            case 0: return lavandaria.getCodigo();
            case 1: return lavandaria.getTipo();
            case 2: return lavandaria.getCor();
            case 3: return lavandaria.getLavandaria();
            case 4: return this.getEstadoValue(lavandaria.isEstado());
        }
        return null;
    }
     @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Lavandaria lavandaria = (Lavandaria) linhas.get(rowIndex);
        switch (rowIndex){
            case 0: lavandaria.setCodigo(Integer.parseInt(value.toString()));  break;
            case 1: lavandaria.setTipo(value.toString()); break;
            case 2: lavandaria.setCor((value.toString()));break;
            case 3: lavandaria.setLavandaria(value.toString());break;
            case 4: lavandaria.setEstado(Boolean.parseBoolean(value.toString())); break;
            
        }
        this.fireTableDataChanged();
    }
    private String getEstadoValue(boolean estado){
        return estado == false ? "Nao Lavado" : "Lavado";
    }
}


