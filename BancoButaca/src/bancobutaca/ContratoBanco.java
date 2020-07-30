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
public class ContratoBanco extends Contrato{
    
    ArrayList<Cuenta> cuentas;

    public ContratoBanco() {
        cuentas = new ArrayList();
    }
    
    
    
    public  boolean ejecutar(Transacciones transacciones){
    
        for(Transaccion t: transacciones.getTransacciones()){
            this.ejecutarTransaccion(t);
        }
        
        return false;
    }
    
    public boolean ejecutarTransaccion(Transaccion t){
    
        switch(t.getTipo()){
            
            case CREAR_CUENTA:
                return crearCuenta(t);
            
            case CERRAR_CUENTA:
                return cerrarCuenta(t);
            
            case DEPOSITAR_FONDOS:
                return depositoFondos(t);
            
            case RETIRAR_FONDOS:
                return retiroFondos(t);
            
            case TRANSFERENCIA_FONDOS:
                return transferenciaFondos(t);
                
        }
        
        return false;
    }

    private boolean crearCuenta(Transaccion t){
        CrearCuenta c = (CrearCuenta)t;
        if(!existeCuenta(c.getTitularID())){
            cuentas.add(new Cuenta(c.getTitularID()));
            System.out.println("Cuenta creada: "+c.getTitularID());
            t.setEstado(true);
            return true;
        }else{
            System.out.println("Cuenta ya existe");
            t.setEstado(false);
            return false;
        }
    }
    
    private boolean cerrarCuenta(Transaccion t){
        CerrarCuenta c1 = (CerrarCuenta)t;
        if(EliminarCuenta(c1.getTitularID())){
            System.out.println("Cuenta cerrada: "+c1.getTitularID());
            t.setEstado(true);
            return true;
        }else{
            System.out.println("Cuenta no existe");
            t.setEstado(false);
            return false;
        }
    }
    
    private boolean retiroFondos(Cuenta cTemp1, float valor){
                    if (cTemp1.retiro(valor)){
                        return true;
                    }else{
                        System.out.println("Fondos insuficientes");
                        return false;
                    }
    }
    
    private boolean depositoFondos(Transaccion t){
        DepositoCuenta d = (DepositoCuenta)t;
                Cuenta cTemp = getCuenta(d.getTitularID());
                if(cTemp != null){
                    boolean temp = depositoFondos(cTemp, d.getValor());
                    if (temp){
                        System.out.println("Depósito realizado: "+d.getTitularID()+", valor: "+d.getValor());
                    }
                    t.setEstado(temp);
                    return temp;
                }else{
                    System.out.println("Cuenta no existe");
                    t.setEstado(false);
                    return false;
                }
    }
    
    
    private boolean depositoFondos(Cuenta cTemp1, float valor){
        if (cTemp1.deposito(valor)){
            return true;
        }
        return false;
    }
    
    private boolean retiroFondos(Transaccion t){
        RetiroCuenta d1 = (RetiroCuenta)t;
                Cuenta cTemp1 = getCuenta(d1.getTitularID());
                if(cTemp1 != null){
                    boolean temp = retiroFondos(cTemp1, d1.getValor());
                    if (temp){
                        System.out.println("Retiro realizado: "+d1.getTitularID()+", valor: "+d1.getValor());
                    }
                    t.setEstado(temp);
                    return temp;
                    
                }else{
                    System.out.println("Cuenta no existe");
                    t.setEstado(false);
                    return false;
                }
    }
    
    private boolean transferenciaFondos(Transaccion t){
        TransferenciaCuentas tc = (TransferenciaCuentas)t;
        Cuenta cTempOrigen = getCuenta(tc.getTitularIDOrigen());
        Cuenta cTempDestino = getCuenta(tc.getTitularIDDestino());
        if(retiroFondos(cTempOrigen, tc.getValor())){
            if(depositoFondos(cTempDestino, tc.getValor())){
                System.out.println("Transferencia exitosa!");
                return true;
            }else{
                depositoFondos(cTempOrigen, tc.getValor());
            }
        }
        
        return false;
    }
    
    private boolean EliminarCuenta(String titularID) {
        for(Cuenta c: cuentas){
            if(c.isTitular(titularID)){
                c.cerrarCuenta();
                return true;
            }
        }
        return false;
    }
    
    private boolean existeCuenta(String titularID) {
        for(Cuenta c: cuentas){
            if(c.isTitular(titularID))
                return true;
        }
        return false;
    }
    
    private Cuenta getCuenta(String titularID) {
        for(Cuenta c: cuentas){
            if(c.isTitular(titularID))
                return c;
        }
        return null;
    }
    
}
