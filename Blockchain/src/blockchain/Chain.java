/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pwightman
 */
public class Chain {
    
    private static int idCount=0;
    private static Semaphore mutex = new Semaphore(1);
    ArrayList<Bloque> chain;
    int id;
    int difficulty;

    public Chain() {
        this(Blockchain.DIFFICULTY);
    }
    
    public Chain(int difficulty) {
        chain = new ArrayList();
        id = idCount++;
        this.difficulty = difficulty;
        addGenesisBlock();
        System.out.println("Blockchain "+id+" created successfuly!");
    }
    
    public void addGenesisBlock(){
        String data = "Genesis block "+id;
        Bloque b = new Bloque(0, LocalDateTime.now(), data, "0");
        b.validate(difficulty);
        chain.add(b);
    }
    
    public Bloque getLastBlock(){
        return chain.get(chain.size()-1);
    }
    
    public String getLastBlockHash(){
        return chain.get(chain.size()-1).getHash();
    }
    
    public synchronized boolean addBlock(Bloque b){
        
        try {
            mutex.acquire();
            if(b.verifyHash(difficulty)){
                Bloque ant = this.getLastBlock();
                b.setPreviousHash(ant.getHash());
                b.setIndex(chain.size());
                b.validate(difficulty);
                chain.add(b);
                mutex.release();
                return true;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Chain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Failed to add new block "+id);
        return false;
    }
    
    public boolean addBlock(String data){
        
        Bloque b = new Bloque(chain.size(), LocalDateTime.now(), data, this.getLastBlockHash());
        b.validate(difficulty);
        
       return this.addBlock(b);
    }
    
    public void listAllBlocks(){
        System.out.println("Lista de bloques - Blockchain"+id+":");
        for(Bloque b: chain){
            System.out.println(b);
        }
    }
    
}
