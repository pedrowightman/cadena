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
public class DepositoCuenta extends Transaccion{
    
    private float valor;
    private String titularID;
    
    public DepositoCuenta(TipoTransaccion tipo, String titularID, float valor) {
        super(tipo);
        this.titularID = titularID;
        this.valor = valor;
    }
    
    public String getTitularID(){
        return this.titularID;
    }
    
    public float getValor(){
        return this.valor;
    }
    
}
