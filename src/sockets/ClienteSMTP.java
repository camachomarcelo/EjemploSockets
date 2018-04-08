/*
 * Ejemplo de Socket - Cliente SMTP
 * Tecnología Web - FICCT - UAGRM
 * Semestre 1-2018
 */
package sockets;
import java.net.*;
import java.io.*;

/**
 *
 * @author Dranser
 */
public class ClienteSMTP {
    
    public static void main(String[] args){
        String servidor = "virtual.fcet.uagrm.edu.bo";
        String user_receptor = "grupo01sa@virtual.fcet.uagrm.edu.bo";
        String user_emisor = "juan.perez@gmail.com";
        String line;
        String comando = "";
        int puerto = 25;
        
        try{
        Socket socket = new Socket(servidor, puerto);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        DataOutputStream salida = new DataOutputStream(
                socket.getOutputStream());
        if ( socket != null && entrada != null && salida != null )
        {
            System.out.println("S : " + entrada.readLine());
            
            comando = "EHLO " +servidor+ " \r\n";
            System.out.print("C : " + comando);
            salida.writeBytes(comando);
            System.out.println("S : " +entrada.readLine());
            
            comando = "QUIT\r\n";
            System.out.print("C : " +comando);
            salida.writeBytes(comando);
            System.out.println("S : " +entrada.readLine());
        }
        salida.close();
        entrada.close();
        socket.close();
        
        }catch (UnknownHostException e){
                e.printStackTrace();
                System.out.println(" S : No se pudo conectar con el servidor indicado");
        }catch (IOException e){ 
                 e.printStackTrace();
                 }       
    }
}