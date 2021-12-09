package Server;
//Do State machine Diagram, dicuss sequel and forward movement I can do sign in.
//Change Forgot digrams to recover password!!!!!!
//Tighten up frakinstein
//p5
//Essay
//Tuesday
//Wednesday Use bootcamp code switch

import java.sql.SQLException;
import java.util.HashMap;

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
		else if (cmdArr[0].equals("passwordRecovery")){ // passwordRecovery;USERNAME\n
			DBaseConnection dBaseConnection = new DBaseConnection();
			User user = dBaseConnection.getUser(cmdArr[1]);
			System.out.println(user.getLockCount());
			if(user.isLocked()){
				try {
					// new password
					String tempPass = "tempPass12343";
					// update
					dBaseConnection.updateUserStringData(user.getUsername(), "password", tempPass);
					// send
					SendEmailUsingGMailSMTP.sendPasswordEmail(user.getEmail(), tempPass);
				}
				catch (InterruptedException | SQLException ex){

				}
			}
			else{
				try {
					SendEmailUsingGMailSMTP.sendPasswordEmail(user.getEmail(), user.getPassword());
				}
				catch (InterruptedException ex){

				}
			}
			na.sendString("If username is valid, your password has been sent to the email on record", false);
		}
		else {
			
			na.sendString(cmd + "\n", false);
			
		}		
	}
}
