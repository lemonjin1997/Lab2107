import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.PrintWriter; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.Date; 


public class AssignmentServer {

	
	public static boolean checkIsDigit(String value) {
		boolean validated = false;
		if ( value.chars().allMatch( Character::isDigit) ) {
			validated = true;
		}
		return validated;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//The port, at which, the server is listening for requests         
				int port = 12345; 
				try { 
					//ServerSocket object is used to listen for requests             
					ServerSocket ss = new ServerSocket(port);             
					System.out.println("Server is ready to receive command!"); 
					while(true){ 
						System.out.println("Please input command.");
						//accepting connection requests                 
						Socket socket = ss.accept(); //get the input stream to read data                 
						InputStream is = socket.getInputStream(); //Read data as character                 
						InputStreamReader isr = new InputStreamReader(is); //Read data as lines                 
						BufferedReader br = new BufferedReader(isr); //Read the string command from the user
						String command = br.readLine();                 
						String response = "";
						
						String[] values = command.split(" ");
						
						String[] validCommand = {"add", "divide", "multiply", "substract"};
						
						//checker
						boolean valid = false;
						
						if (values.length != 3) {
							response = "Result: invalid number of arguments";
							valid = false;
						}
						
						if ( checkIsDigit(values[1]) ) {
							response = "Error: \" + values[1] + \" is not a number ";
							valid = false;
						}
						if ( checkIsDigit(values[2]) ) {
							response = "Error: \" + values[2] + \" is not a number ";
							valid = false;
						}
						
						for(String commandType: validCommand){
							if (commandType.equals(values[0]) ) {
								valid = true;
								break;
							}
						}
						
						if (!valid) {
							response = "Error: Invalid command \" + values[0] + \" ";
						}
						else {
							response = "Error: '' ";
							
							if(values[0].equals("add")){
								
								int value1 = Integer.parseInt(values[1]);
								int value2 = Integer.parseInt(values[2]);
								int result = value1 + value2;
								response = "The add result is: " + result;                 
							}
							if(values[0].equals("substract")){
								
								int value1 = Integer.parseInt(values[1]);
								int value2 = Integer.parseInt(values[2]);
								int result = value1 - value2;
								response = "The substract result is: " + result;                 
							}
							if(values[0].equals("divide")){
								
								int value1 = Integer.parseInt(values[1]);
								int value2 = Integer.parseInt(values[2]);
								if (value2 == 0) {
									response = "Error: Divided by zero exception ";
								}
								else {
									int result = value1 / value2;
									response = "The divide result is: " + result;
								}
							}
							if(values[0].equals("multiply")){
								
								int value1 = Integer.parseInt(values[1]);
								int value2 = Integer.parseInt(values[2]);
								int result = value1 * value2;
								response = "The multiply result is: " + result;                 
							}

							
							
							
						}
						 
						//Access to the output stream, to write data back                 
						OutputStream os = socket.getOutputStream(); //Write lines                 
						PrintWriter pw = new PrintWriter(os);                 
						pw.println(response);                 
						pw.flush();                 
						pw.close();                 
						//socket.close(); 
						//ss.close();
						}
				}
							
						catch (IOException ex) {             
							ex.printStackTrace(); 
					}
	}

}
