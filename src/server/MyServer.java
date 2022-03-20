package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	public static void main (String[] args) throws IOException {
		
		
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("J'attend la connexion ...");
		Socket s = ss.accept();
		System.out.println("Connexion d'un client : " + s.getRemoteSocketAddress());
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		
		System.out.println(" J'attend que le client envoi un octet !");
		
		int nb = is.read(); // Lire un octet
		System.out.println("J'ai reçu un nombre => " + nb);
		
		int rep = nb*2;
		System.out.println(" J'envoi le réponse : " + rep +"....");
		os.write(rep);
		
		s.close();
		
		
	}

}
