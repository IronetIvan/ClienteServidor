/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Usuario DAM 2
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       String Host = "localhost";
        int puerto = 5555;//puerto remoto
 
        // Propiedades JSSE)
        System.setProperty("javax.net.ssl.trustStore","src/AlmacenSrv");
        System.setProperty("javax.net.ssl.trustStorePassword","1234567");
 
        System.out.println("PROGRAMA CLIENTE INICIADO....");
 
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket Cliente  = (SSLSocket) sfact.createSocket(Host, puerto);
 
        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
 
        // ENVIO UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");
 
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
 
        // EL servidor ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
 
        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }// main
}//..ClienteSSLv
