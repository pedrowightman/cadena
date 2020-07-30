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
public class TransferenciaCuentas extends Transaccion{
    
    private float valor;
    private String titularIDOrigen;
    private String titularIDDestino;
    
    public TransferenciaCuentas(TipoTransaccion tipo, String titularIDOrigen, String titularIDDestino, float valor) {
        super(tipo);
        this.titularIDOrigen = titularIDOrigen;
        this.titularIDDestino = titularIDDestino;
        this.valor = valor;
    }

    public String getTitularIDOrigen() {
        return titularIDOrigen;
    }

    public String getTitularIDDestino() {
        return titularIDDestino;
    }
    
    
    public float getValor(){
        return this.valor;
    }
    
}
