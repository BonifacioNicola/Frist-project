/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Codigos.Lavandaria;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Francy
 */
public class Ficheiro {
    

    /**
     * Nome do ficheiro 
     */
    private String ficheiro;
    
    
    /**
     * Contrutor do contralador onde eh feita a abertura ou cricao do ficheiro
     * @param ficheiro 
     */
    public Ficheiro(String ficheiro) {
        this.ficheiro = ficheiro;  
            
        if(!this.verificarFicheiro(ficheiro)){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(ficheiro);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
            
    }
    
    
    /**
     * Busca todos os registros existentes no ficheiro
     * @return lista de todos items
     */
    public ArrayList<Lavandaria> listarTodos(){
       
        ArrayList<Lavandaria> lavandarias = new ArrayList<>();
        
        try {
            FileInputStream fileInputStream  = new FileInputStream(ficheiro);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
          
             lavandarias = (ArrayList<Lavandaria>) objectInputStream.readObject();
            
        }catch(EOFException ex){
            return lavandarias;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Litsar peca" + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Litsar peca" + ex);
        }
        return lavandarias;
    }

    
    /**
     * Salva um determinado item no ficheiro.
     * Para tal eh necessario buscar todos os items existentes no ficheiro
     * e voltar a salvar novamente todos.
     * @param item
     * @return true se o item for gravado e false caso contrario
     */
    public boolean salvar(Lavandaria lavandaria){
        ArrayList<Lavandaria> lavandarias = new ArrayList<Lavandaria>();
        lavandarias.addAll(this.listarTodos());
        lavandarias.add(lavandaria);
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(ficheiro);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(lavandarias);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "Peca Salvos com sucesso");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar item" + ex.getMessage());
            return false;
        }
    }  
    
    
    public boolean salvar(ArrayList<Lavandaria> lavandarias){
        try {
            
            FileOutputStream fileOutputStream = new FileOutputStream(ficheiro);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(lavandarias);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "Peca Salvos com sucesso");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar a peca" + ex.getMessage());
            return false;
        }
    }  
    
    
 
   /**
    * Metodo que Permite pesquisar um item em funcao do seu Codigo
    * @param id  codigo do Item
    * @return O item se for encontrado, caso contrario reorna null;
    */
    public Lavandaria pesquisar(int id){
        ArrayList<Lavandaria> lavandarias = listarTodos();
        
        boolean existe = false;

        for(Lavandaria lavandaria : lavandarias){
               if(id == lavandaria.getCodigo()){
                   existe = true;
                 return lavandaria;
               }    
        }
        
        if (existe != true){
            JOptionPane.showMessageDialog(null, "Peca Introduziao nao existe!");  
            
        }
        
        return null;
    
    }
   
    /**
     * Actualiza um determinado item 
     * @param itemUpdate
     * @return 
     */
    public ArrayList<Lavandaria> actualizar(Lavandaria itemUpdate){
        ArrayList<Lavandaria> lavandarias = listarTodos();
        Lavandaria lavandaria = pesquisar(itemUpdate.getCodigo());
        
        if(lavandaria != null){
        
            int index = this.buscarIndex(lavandaria.getCodigo());
            if(index >= 0){
                
                lavandarias.set(index, itemUpdate);
                this.salvar(lavandarias);
            }
            else
                JOptionPane.showMessageDialog(null, "peca nao existe na lista");
        }else
            JOptionPane.showMessageDialog(null, "A peca nao existe");
        return lavandarias;
        
    }
    
    
    
    public boolean remover(Lavandaria elemento){
        
        ArrayList<Lavandaria> lavandarias = listarTodos();
        Lavandaria guardarElemento = null;
        
        for (Lavandaria item : lavandarias) {
            if(elemento.getCodigo() == item.getCodigo()){
                guardarElemento = item;
                break;
            }
        }
        
        
        if(guardarElemento == null)
            return false;
       
        lavandarias.remove(guardarElemento);
        this.salvar(lavandarias);
        
        return true;
        
        
    }
    
    
    
  
    
    public int buscarIndex(int id){
        
        ArrayList<Lavandaria>  lavandarias = this.listarTodos();
        for (int i=0; i<lavandarias.size(); i++){
            if(id == lavandarias.get(i).getCodigo())
                return i;
        }
        
        return -1;
        
    }
    
    /**
     * Gera o proximo id para um determinado Item
     * @return id do item
     */
    public int gerarId(){
        ArrayList<Lavandaria> lavandarias = this.listarTodos();
        
        if(lavandarias.size() == 0)
            return 1;
        
        return lavandarias.get(lavandarias.size() - 1).getCodigo() + 1;
    }
    
    public boolean verificarFicheiro(String ficheior){
        return new File(ficheiro).exists();
    }
    
    
     public ArrayList<Lavandaria> Naolavar(){
        
        ArrayList<Lavandaria> lavandarias = new ArrayList<>();
        

        for (Lavandaria lavandaria : this.listarTodos()) {
            if(!((Lavandaria) lavandaria ).isEstado())
                lavandarias.add(lavandaria);
        }
        
    
        return lavandarias;
    }
     
    public ArrayList<Lavandaria> getLavar(){
        
        ArrayList<Lavandaria> lavandarias = new ArrayList<>();
        

        for (Lavandaria lavandaria : this.listarTodos()) {
            if(((Lavandaria) lavandaria ).isEstado())
                lavandarias.add(lavandaria);
        }
        
    
        return lavandarias;
    }
    
    
    
        
}


