package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends  Thread{
	
	private int nombreClient;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Creer et démarer un Thread (Faire appel à la méthode run
		new ServerMT().start();
		
		System.out.println("Suite de l'application ...");

	}
	
	@Override
	public void run() {
		try {
			ServerSocket ss =new ServerSocket(1234);
			System.out.println("Démarrage du serveur ... ");
			while(true) {
				Socket socket= ss.accept();
				++nombreClient;
				new Conversation(socket,nombreClient).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Conversation extends Thread{
		private Socket socket;
		private int numClient;
		
		public  Conversation(Socket s, int nombreClient) {
			this.socket=s;
			this.numClient =nombreClient;
		}
		
		@Override
		public void run() {
			
			try {
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				
				String ip = socket.getRemoteSocketAddress().toString();
				
				System.out.println("Connexion du client n : " + numClient + " IP = " + ip) ;
				pw.println("BienVenu vous ete le client n : " + numClient);
				while(true) {
					String req = br.readLine();
					System.out.println("Le client avec IP " + ip + "a envoyé une requete : " + req) ;
					pw.println("nombre de caractere : " + req.length());
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
