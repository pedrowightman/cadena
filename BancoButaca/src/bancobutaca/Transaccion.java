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
public class Transaccion {
    TipoTransaccion tipo;
    boolean estado;

    public Transaccion(TipoTransaccion tipo) {
        this.tipo = tipo;
        estado=false;
    }
    
    public TipoTransaccion getTipo(){
        return tipo;
    }
    
    public void setEstado(boolean s){
        estado = s;
    }
    
    
}
