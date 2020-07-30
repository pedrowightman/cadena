/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author super
 */
public class Blockchain {

    public static final int DIFFICULTY = 3;
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Chain theChain = new Chain();
        
        String data = "data1";
        Bloque b = new Bloque(0, LocalDateTime.now(), data, "0");
        theChain.addBlock(b);
        
        data = "data2";
        theChain.addBlock(data);
        
        theChain.listAllBlocks();
        
        
    }
    
}
