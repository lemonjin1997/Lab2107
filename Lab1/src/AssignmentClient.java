import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.PrintWriter;
import java.net.Socket;

import java.nio.charset.Charset;
import java.util.Scanner;

public class AssignmentClient {
	public static void main(String[] args) { 
		//Name of the host that we are going to conenct to         
		String hostName = null; 
		//Make sure that this port is the same as         
		//the server listening port         
		int port = 12345; 
		try { 
			//Use socket to connect to the server             
			Socket socket = new Socket(hostName, port); 
			Scanner in = new Scanner(System.in);
			String request = in.nextLine(); 
			System.out.println(request);
			OutputStream os = socket.getOutputStream(); //Write lie
			
			os.write(request.getBytes(Charset.forName("UTF-8")));
			PrintWriter pw = new PrintWriter(os);             
			pw.println();             
			pw.flush(); //Read response.             
			InputStream is = socket.getInputStream(); //Read characters             
			InputStreamReader isr = new InputStreamReader(is); //Read lines             
			BufferedReader br = new BufferedReader(isr);             
			String header = br.readLine();             
			System.out.println("Response: " + header); 
			//socket.close();
			
			} 
		catch (IOException ex) {             
			ex.printStackTrace();         
			}     
		} 
		}

