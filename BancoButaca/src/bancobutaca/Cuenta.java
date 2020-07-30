/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancobutaca;

/**
 *
 * @author pwightman
 */
public class Cuenta {
    
    private final String titular;
    private float saldo;
    private boolean activa;

    public Cuenta(String titular) {
        this.titular = titular;
        activa = true;
        saldo = 0;
    }
    
    private String getTitular(){
        return titular;
    }
    
    public boolean isTitular(String value){
        return this.titular.equals(value);
    }
    
    public boolean validarTitular(String value){
        return Bloque.calculateHash(value).equals(titular);
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    public boolean deposito(float valor){
        if(activa){
            saldo+=valor;
            return true;
        }
        return false;
    }
    
    public boolean retiro(float valor){
        if(activa && valor <= saldo){
            saldo-=valor;
            return true;
        }
        return false;
    }
    
    public boolean isActiva(){
        return activa;
    }
    
    public boolean equals(Object o){
        if(o instanceof Cuenta){
            Cuenta c = (Cuenta)o;
            return c.getTitular().equals(this.getTitular());
        }
        return false;
    }
    
    public boolean cerrarCuenta(){
        if(activa){
            this.activa = false;      
            return true;
        }
        return false;
    }
}
