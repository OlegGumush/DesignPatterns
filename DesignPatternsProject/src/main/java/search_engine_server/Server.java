package search_engine_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import url_list.UrlList;

public class Server extends Thread {

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	
    private ServerSocket serverSocket;
    private boolean start = true;
    private ObjectInputStream readFromClient = null;
	private ObjectOutputStream writeToClient = null;
    private static final int PORT_NUMBER = 6789;

    
    public void run(){ 	
    	System.out.println("Server starts ...");
    	
    	try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			
			while(start){
				
				// accept socket
				Socket clientSocket = this.serverSocket.accept();
				
				// read query from client
				readFromClient = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("Client's request: " + (String)readFromClient.readObject());
	            
				// write url list to client
				writeToClient = new ObjectOutputStream(clientSocket.getOutputStream());
				UrlList urls = new UrlList();
				urls.addUrl("www.check_connection.com");
				urls.addUrl("www.check_connection1.com");
	            writeToClient.writeObject(urls.toString());
	            writeToClient.flush();
			}	
		} catch (IOException e) {
			System.out.println("Server class : Function run (IOException)");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}








