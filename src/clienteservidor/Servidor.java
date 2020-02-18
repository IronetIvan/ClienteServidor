/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Usuario DAM 2
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int puerto = 5555;
               
                System.setProperty("javax.net.ssl.keyStore","src/AlmacenSrv");
                System.setProperty("javax.net.ssl.keyStorePassword","1234567");
       
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory
                .getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact
                .createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
        DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE
       
        for (int i = 1; i < 5; i++) {
            System.out.println("Esperando al cliente " + i);
            clienteConectado = (SSLSocket) servidorSSL.accept();       
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
 
            // EL CLIENTE ME ENVIA UN MENSAJE
            System.out.println("Recibiendo del CLIENTE: " + i + " \n\t"
                    + flujoEntrada.readUTF());
   
            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
 
            // ENVIO UN SALUDO AL CLIENTE
            flujoSalida.writeUTF("Saludos al cliente del servidor");
        }
        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();
 
    }// main
}