import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Server {
	
	//An array list to hold all the connections
	public static ArrayList<Socket> ConnectionArray = new  ArrayList<Socket>();
	//An array list to hold the current users
	public static ArrayList<String> CurrentUsers = new ArrayList<String>();
	
	
	public static void main (String[] args) throws IOException {
		
		try {
			
			//add port details
			final int PORT = 444; 
			ServerSocket  SERVER = new ServerSocket(PORT);
			System.out.println("Waiting for Client");
			
			while(true) {
				
				
				Socket SOCK = Server.accept();
				ConnectionArray.add(SOCK);
				
				System.out.println("Client connected from : " + SOCK.getLocalAddress().getHostName());
				
				//Add users to the list of Current Users
				AddUserName(SOCK);
				
				Server_Return CHAT = new Server_Return(SOCK);
				Thread X = new Thread (CHAT);
				X.start();	
				
			}
			
			
		}
		
		catch (Exception X ) (System.out.print(X);)
	}
//-------------------------------------------------------------------------------
	
	public static void AddUserName (Socket X ) throws IOException {
		
		Scanner INPUT = new Scanner(X.getInputStream());
		String UserName = INPUT.nextLine();
		CurrentUsers.add(UserName);
		
		for(int i = 1; i < Server.ConnectionArray.size(); i++){
			Socket TEMP_SOCK = (Socket) Server.ConnectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
			OUT.println("#?!" + CurrentUsers);
			OUT.flush();
		}
	}
	
//------------------------------------------------------------------------------

	private static Socket accept() {
		// TODO Auto-generated method stub
		return null;
	}
	
}