/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancobutaca;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author pwightman
 */
public class Bloque {
    
    LocalDateTime timestamp;
    Transacciones data;
    String previousHash;
    String hash;
   
    public Bloque(int idCadena, LocalDateTime timestamp, Transacciones data, String previousHash) {
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHash(this.toString4Hash());
    }
    
    public boolean isValid(String previousHash){
        
        return (this.getPreviousHash().equals(previousHash) && 
                this.getHash().equals(this.calculateHash(this.toString4Hash())));
    }
    
    //Taken from:
    //https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
    public static String calculateHash(String base) {
        
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
        
    }

    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Transacciones getData() {
        return data;
    }

    public void setData(Transacciones data) {
        this.data = data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    
    
    public String toString(){
        StringBuffer s = new StringBuffer();
        
        s.append(timestamp); s.append("\t");
        s.append(data); s.append("\t");
        s.append(this.previousHash); s.append("\t");
        s.append(hash);
        
        return s.toString();
    }
    
    public String toString4Hash(){
        StringBuffer s = new StringBuffer();
        s.append(timestamp);
        s.append(data);
        s.append(previousHash);
        return s.toString();
    }
    
}
