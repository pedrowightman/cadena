/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancobutaca;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author pwightman
 */
public class Cadena {
    
    ArrayList<Bloque> chain;
    int id;
    Contrato contrato;

    public Cadena(int id) {
        this.id = id;
        chain = new ArrayList();
        addGenesisBlock();
    }
    
    public Cadena(int id, Contrato contrato) {
        this(id);
        this.contrato = contrato;
    }
    
    
    public void addGenesisBlock(){
        Transacciones data = new Transacciones();
        data.addTransaccion(new Transaccion(TipoTransaccion.BLOQUE_GENESIS));
        Bloque b = new Bloque(id, LocalDateTime.now(), data, "0");
        chain.add(b);
    }
    
    
    public void addBlock(Transacciones data){
        Bloque b = new Bloque(id, LocalDateTime.now(), data, this.getLastBlockHash());
        addBlock(b);
    }
    
    
    public void addBlock(Bloque b){
        if(b.isValid(this.getLastBlockHash())){
            chain.add(b);
            contrato.ejecutar(b.getData());
        }
    }
    
    public boolean isValid(){
        String tempHash="0";
        for(Bloque b: chain){
            if(!b.isValid(tempHash)){
                return false;
            }
            tempHash = b.getHash();
        }
        return true;
    }
    
    public Bloque getLastBlock(){
        return chain.get(chain.size()-1);
    }
    
    public String getLastBlockHash(){
        return chain.get(chain.size()-1).getHash();
    }
    
    public void listAllBlocks(){
        System.out.println("Lista de bloques - Blockchain"+id+":");
        for(Bloque b: chain){
            System.out.println(b);
        }
    }
    
}
