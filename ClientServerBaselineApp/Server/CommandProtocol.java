package Server;
//Do State machine Diagram, dicuss sequel and forward movement I can do sign in.
//Change Forgot digrams to recover password!!!!!!
//Tighten up frakinstein
//p5
//Essay
//Tuesday
//Wednesday Use bootcamp code switch

import java.util.HashMap;
import java.util.Set;

import Client.DBaseConnection;
import Common.NetworkAccess;

/**
 * @author reinhart
 *
 */
public class CommandProtocol {

	/**
	 * commands and their responses
	 */
	private static HashMap<String, String> commands;	
	static {
	    commands = new HashMap<>();
	    commands.put("disconnect", "");
	    commands.put("hello", "world!");
	}
	
	/**
	 * process commands sent to the server
	 * @param cmd: command to be processed
	 * @param na: NetworkAccess object for communication
	 * @param ch: ClientHandler object requesting the processing
	 * @return
	 */
	public static void processCommand(String cmd, NetworkAccess na, ClientHandler ch)



	{
		if (cmd.equals("disconnect")) {

			// -- no response to the client is necessary
			na.close();
			ch.getServer().removeID(ch.getID());
			ch.Stop();
		}
		else if (cmd.equals("hello")) {
				
			// -- client is expecting a response
			na.sendString("world!" + "\n", false);
			
		}
		else if (cmd.contains("register")){
			String username;
			String password;
			String email;
			String[] cmdList = cmd.split(";");
			int count = 0;
			for (String word : cmdList) {
				if(count == 0){
					count++;
					continue;
				}else if (count == 1){
					username = word;
					count++;
				}else if(count == 2){
					password = word;
					count++;
				}else if(count == 3){
					email = word;
				}
				DBaseConnection dbc = new DBaseConnection();
				dbc.exists();
			}

		}
		else {
			na.sendString(cmd + "\n", false);
			
		}		
	}
}
