/*
 * Ejemplo de Socket - Cliente POP
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
public class ClientePOP {
    
    public static void main(String[] args) {
    //Definimos las cadenas con información del servidor, usuario receptor, emisor del email y puerto de conexion
        String servidor="mail.ficct.uagrm.edu.bo";
        //String servidor="172.20.172.254";
        String usuario="grupo01sa";
        String contrasena="grupo0101";
        String comando="";
        String linea="";
        int puerto=110;
    
        try{
            //Establecemos conexión abriendo un socket especificando el servidor y el puerto SMTP
            Socket socket=new Socket(servidor,puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream (socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if( socket != null && entrada != null && salida != null ){
                System.out.println("S : "+entrada.readLine()+"\r\n");
        
                comando="USER "+usuario+"\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );                
                System.out.println("S : "+entrada.readLine()+"\r\n");
                
                comando="PASS "+contrasena+"\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );                
                System.out.println("S : "+entrada.readLine()+"\r\n");
                
                comando="LIST \r\n";
                System.out.print("C : "+comando);    
                salida.writeBytes( comando );                            
                System.out.println("S : "+getMultiline(entrada)+"\r\n");                
                
                comando="RETR 5\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );                
                System.out.println("S : "+getMultiline(entrada)+"\r\n");    
                
                comando="QUIT\r\n";
                System.out.print("C : "+comando);
                salida.writeBytes( comando );                
                System.out.println("S : "+entrada.readLine()+"\r\n");
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        }catch(UnknownHostException e){
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

// Permite leer multiples lineas de respuesta del Protocolo POP

    static protected String getMultiline(BufferedReader in) throws IOException{
        String lines = "";
        while (true){
            String line = in.readLine();
            if (line == null){
               // Server closed connection
               throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")){
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')){
                // The line starts with a "." - strip it off.
                line = line.substring(1);
            }
            // Add read line to the list of lines
            lines=lines+"\n"+line;
        }        
        return lines;
    }
}
