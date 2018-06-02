package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client extends Thread {
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
	

	private Socket ServerSocket = null;
	private String query = null;
	private Scanner reader = new Scanner(System.in);
	private ObjectOutputStream writeToServer = null          ;
    private ObjectInputStream readFromServer = null;
	private static final int SERVER_PORT = 6789;
	
	
	public Client(){

	}

	public void run(){


		try {
			System.out.println("Client start");
			ServerSocket = new Socket("localhost" , SERVER_PORT);
			
			while(true){
				//System.out.println("Please enter a query: ");
				//query = reader.nextLine();	
	            writeToServer = new ObjectOutputStream(ServerSocket.getOutputStream());
	            this.writeToServer.writeObject(new String("Check connection"));
	            this.writeToServer.flush();
			    readFromServer = new ObjectInputStream(ServerSocket.getInputStream());
			    System.out.println("Answer from server:\n" + readFromServer.readObject());
			}
		} catch (IOException e) {
			System.out.println("Filed to connect to the server");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
