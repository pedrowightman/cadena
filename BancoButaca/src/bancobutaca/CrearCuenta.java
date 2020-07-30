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
public class CrearCuenta extends Transaccion{

    private String titular;
    private String titularID;
    
    
    public CrearCuenta(TipoTransaccion tipo, String titular) {
        super(tipo);
        this.titular = titular;
        titularID = Bloque.calculateHash(titular);
    }
    
    public String getTitularID(){
        return this.titularID;
    }
    
    
    
}
