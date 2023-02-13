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
	public static void processCommand(String cmd, NetworkAccess na, ClientHandler ch) throws SQLException {
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
		else if (cmd.contains("register")){
			DBaseConnection dbc = null;
			String username = null;
			String password = null;
			String email= null;
			String[] cmdList = cmd.split(";");
			int count = 0;
			for (String word : cmdList) {
				if (count == 0) {
					count++;
					continue;
				} else if (count == 1) {
					username = word;
					count++;
				} else if (count == 2) {
					password = word;
					count++;
				} else if (count == 3) {
					email = word;
				}
			}
				if(System.getProperty("user.name").equals("bjsot")){
					dbc = new DBaseConnection("root","?Vagus39");
				} else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
					dbc = new DBaseConnection("root", "ravenisdark32!");
				}
				int usernameExists = dbc.exists( "username", username);
				int emailExists = dbc.exists("email",email);
				if(usernameExists == 0 && emailExists == 0){
					dbc.addNewRecord(username,password,email);
					na.sendString("SUCCESS",false);
				}else {
					if(usernameExists == 1 && emailExists == 1){
						na.sendString("FAIL11",false);
					}else if(usernameExists == 0 && emailExists == 1){
						na.sendString("FAIL01",false);
					}else if(usernameExists == 1 && emailExists == 0){
						na.sendString("FAIL10",false);
					}
				}
		}
		else if(cmd.contains("logout")){
			DBaseConnection dbc = null;
			String username = null;
			String[] cmdList = cmd.split(";");
			int count = 0;
			for (String word : cmdList) {
				if (count == 0) {
					count++;
					continue;
				} else if (count == 1) {
					username = word;
					count++;
				}
			}
			if(System.getProperty("user.name").equals("bjsot")){
				dbc = new DBaseConnection("root","?Vagus39");
			} else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
				dbc = new DBaseConnection("root", "ravenisdark32!");
			}
			dbc.logout(username);
		}
		else if(cmd.contains("login")) {

			int lockcount = 0;
			DBaseConnection dbc = null;
			String username = null;
			String password = null;
			String[] cmdList = cmd.split(";");
			int count = 0;
			for (String word : cmdList) {
				if (count == 0) {
					count++;
					continue;
				} else if (count == 1) {
					username = word;
					count++;
				} else if (count == 2) {
					password = word;
					count++;
				}
			}
			if(System.getProperty("user.name").equals("bjsot")){
				dbc = new DBaseConnection("root","?Vagus39");
			} else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
				dbc = new DBaseConnection("root", "ravenisdark32!");
			}
			int usernameExists = dbc.exists( "username", username);
			if(usernameExists == 1){
				lockcount = dbc.getLockCount(username);
				if(lockcount >= 3){
					na.sendString("LockedOut",false);
				}else {
					if(dbc.passwordMatch(username,password)) {
						dbc.login(username);
						na.sendString("Success", false);
					}else {
						dbc.incrementLockCount(username);
						na.sendString("IncorrectPassword", false);
					}
				}
			}else {
				na.sendString("IncorrectPassword", false);
			}

		}else if (cmdArr[0].equals("passwordRecovery")) { // passwordRecovery;USERNAME\n
			DBaseConnection dbc = null;
			if(System.getProperty("user.name").equals("bjsot")){
				dbc = new DBaseConnection("root","?Vagus39");
			} else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
				dbc = new DBaseConnection("root", "ravenisdark32!");
			}

			if(0 == dbc.exists("username",cmdArr[1])){
				na.sendString("If username is valid, your password has been sent to the email on record\"",false);
				return;
			}else {

			}
			User user = dbc.getUser(cmdArr[1]);
			String tempPass = "tempPass12343";
			if (user.isLocked()) {
				try {
					// new password
					// update
					dbc.updateUserStringData(user.getUsername(), "password", tempPass);
					dbc.updateUserIntData(user.getUsername(), "lockcount", 0);
					// send
					SendEmailUsingGMailSMTP.sendPasswordEmail(user.getEmail(), tempPass);

				} catch (InterruptedException | SQLException ex) {

				}
			} else {
				try {
					dbc.updateUserStringData(user.getUsername(), "password", tempPass);
					SendEmailUsingGMailSMTP.sendPasswordEmail(user.getEmail(), user.getPassword());

				} catch (InterruptedException ex) {

				}
			}
			na.sendString("If username is valid, your password has been sent to the email on record", false);

		} else if (cmdArr[0].equals("passwordChange")) { // passwordRecovery;USERNAME;NEWPASSWORD\n
			DBaseConnection dbc = null;
			if(System.getProperty("user.name").equals("bjsot")){
				dbc = new DBaseConnection("root","?Vagus39");
			} else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
				dbc = new DBaseConnection("root", "ravenisdark32!");
			}
			System.out.println("Change pass server");
			dbc.updateUserStringData(cmdArr[1], "password", cmdArr[2]);
			na.sendString("password has been updated", false);

		}else {
			na.sendString(cmd + "\n", false);
			
		}		
	}}

