/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancobutaca;

import java.time.LocalDateTime;

/**
 *
 * @author pwightman
 */
public class BancoButaca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int chainId = 1234;
        
        ContratoBanco c = new ContratoBanco();
        Cadena cadena = new Cadena(chainId,c);
        
        Transacciones data = new Transacciones();
        data.addTransaccion(new CrearCuenta(TipoTransaccion.CREAR_CUENTA,"pedromario"));
        data.addTransaccion(new CrearCuenta(TipoTransaccion.CREAR_CUENTA,"pedromario"));
        cadena.addBlock(data);
        
        data.clear();
        data.addTransaccion(new RetiroCuenta(TipoTransaccion.RETIRAR_FONDOS, "2eb307b3dbe2bb6008b14c3341f377c29703694c31425202fa642b6b297aa46e", 1000));
        data.addTransaccion(new DepositoCuenta(TipoTransaccion.DEPOSITAR_FONDOS, "2eb307b3dbe2bb6008b14c3341f377c29703694c31425202fa642b6b297aa46e", 1000));
        data.addTransaccion(new RetiroCuenta(TipoTransaccion.RETIRAR_FONDOS, "2eb307b3dbe2bb6008b14c3341f377c29703694c31425202fa642b6b297aa46e", 1200));
        data.addTransaccion(new RetiroCuenta(TipoTransaccion.RETIRAR_FONDOS, "2eb307b3dbe2bb6008b14c3341f377c29703694c31425202fa642b6b297aa46e", 200));
        cadena.addBlock(data);
        
        data.clear();
        data.addTransaccion(new CrearCuenta(TipoTransaccion.CREAR_CUENTA,"carlosalberto"));
        cadena.addBlock(data);
        
        data.clear();
        data.addTransaccion(new TransferenciaCuentas(TipoTransaccion.TRANSFERENCIA_FONDOS, "2eb307b3dbe2bb6008b14c3341f377c29703694c31425202fa642b6b297aa46e","62d050ed0a0d024dc1a31d102138644d9fbab890dd1576ac6d40abcb1f6b3470", 1000));
        data.addTransaccion(new TransferenciaCuentas(TipoTransaccion.TRANSFERENCIA_FONDOS, "2eb307b3dbe2bb6008b14c3341f377c29703694c31425202fa642b6b297aa46e","62d050ed0a0d024dc1a31d102138644d9fbab890dd1576ac6d40abcb1f6b3470", 200));
        cadena.addBlock(data);
        
        cadena.listAllBlocks();
        
    }
    
}
