/*
 * Ejemplo de Socket Servidor
 * Tecnología Web - FICCT - UAGRM
 * Semestre 1-2018
 */
package sockets;
import java.io.*;
import java.net.*;
/**
 *
 * @author Dranser
 */
public class Servidor {
    static final int PUERTO = 5000;

    public Servidor( )
   {

      try{
          ServerSocket skServidor = new ServerSocket( PUERTO );
          System.out.println( " S : Escucho el puerto " + PUERTO );

          //Atiende a 3 clientes 
          for ( int numCli = 0; numCli < 3; numCli++ )
          {
              Socket skCliente = skServidor.accept(); //Crea objeto
              System.out.println( " S : Sirvo al cliente " + numCli );
              DataOutputStream salida = new DataOutputStream (skCliente.getOutputStream());
              salida.writeBytes( "Hola cliente " + numCli );
              skCliente.close();
          } 

         System.out.println( " S : Demasiados clientes por hoy" );

      } catch( Exception e ){
          System.out.println( e.getMessage() );
      }
   }


    public static void main(String[] args) {

        // TODO Auto-generated method stub

        Servidor s=new Servidor();
     }
}
