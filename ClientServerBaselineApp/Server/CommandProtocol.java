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
import Client.SendEmailUsingGMailSMTP;
import Common.NetworkAccess;
import Common.User;

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
		String[] cmdArr = cmd.split(";");
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
		else if (cmdArr[0].equals("passwordRecovery")){ // passwordRecovery;USERNAME\n
			DBaseConnection dBaseConnection = new DBaseConnection();
			System.out.println("Server getting email");
			User user = dBaseConnection.getUser(cmdArr[1]);
			if(!user.isLocked()){
				System.out.println("Send Email "+user.getEmail());
				try {
					SendEmailUsingGMailSMTP.sendPasswordEmail(user.getEmail(), user.getPassword());
				}
				catch (InterruptedException ex){

				}
			}
			else{
				// new password

				// update

				// send
				System.out.println("Send Email "+user.getEmail());
			}
			na.sendString("If username is valid, your password has been sent to the email on record", false);
		}
		else {
			
			na.sendString(cmd + "\n", false);
			
		}		
	}
}
