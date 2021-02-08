import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.PrintWriter; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.Date; 

public class Assignment2Server {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//The port, at which, the server is listening for requests         
		int port = 8080; 
		try { 
			//ServerSocket object is used to listen for requests             
			ServerSocket ss = new ServerSocket(port);             
			System.out.println("Server is ready to receive command!"); 
			while(true){ 
				//accepting connection requests                 
				Socket socket = ss.accept(); //get the input stream to read data
				
				InputStream is = socket.getInputStream(); //Read data as character                 
				InputStreamReader isr = new InputStreamReader(is); //Read data as lines 
				
				
				BufferedReader br = new BufferedReader(isr); //Read the string command from the user
				String command = br.readLine();                 
				String response = ""; 
				System.out.println(command);
				                 
					response = "This is " + socket.getLocalAddress() +  ":" + socket.getLocalPort() +  ", current time here is: " + new Date(); 
					//Access to the output stream, to write data back                 
					OutputStream os = socket.getOutputStream(); //Write lines                 

			        String data = 
			        		"HTTP/1.1 200 OK\r\n"
			        		+ "Content-Type: text/html\r\n"
			        		+ "X-Pad: avoid browser bug\r\n"
			        		+ "Accept-Ranges: bytes\r\n"
			        		+"\r\n"
			        		+ "<HTML>"
			        		+ "<TABLE border=\"1\" " 
			        		+ " summary=\"This table gives some statistics about fruit"
			        		+ " flies: average height and weight, and percentage"
			        		+ " with red eyes (for both males and females).\">"
			        		+ "<CAPTION><EM>A test table with merged cells</EM></CAPTION>"
			        		+ "<TR><TH rowspan=\"2\"><TH colspan=\"2\">Average"
			        		+ " <TH rowspan=\"2\">Red<BR>eyes"
			        		+ "<TR><TH>height<TH>weight"
			        		+ "<TR><TH>Males<TD>1.9<TD>0.003<TD>40%"
			        		+ "<TR><TH>Females<TD>1.7<TD>0.002<TD>43%"
			        		+ "</TABLE>"
			        		+ "</HTML>";
			        os.write(data.getBytes());
			        
			        os.flush();
			        socket.close();
				}
				
		}
					
				catch (IOException ex) {             
					ex.printStackTrace(); 
			}
		}
}
