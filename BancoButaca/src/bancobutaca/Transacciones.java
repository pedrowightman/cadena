/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancobutaca;

import java.util.ArrayList;

/**
 *
 * @author pwightman
 */
public class Transacciones {
    
    ArrayList<Transaccion> transacciones;

    public Transacciones() {
        transacciones = new ArrayList();
    }
    
    public void addTransaccion(Transaccion t){
        this.transacciones.add(t);
    }
    
    public ArrayList<Transaccion> getTransacciones(){
        return transacciones;
    }
    
    public void clear(){
        transacciones.clear();
    }
    
}
