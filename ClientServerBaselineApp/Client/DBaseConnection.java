package Client;// -- download MySQL from: http://dev.mysql.com/downloads/
//    Community Server version
// -- Installation instructions are here: http://dev.mysql.com/doc/refman/5.7/en/installing.html
// -- open MySQL Workbench to see the contents of the database
import Common.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// -- MAKE SURE THE JDBC CONNECTOR JAR IS IN THE BUILD PATH
//    workspace -> properties -> Java Build Path -> Libraries -> Add External JARs...
public class DBaseConnection {
//One Based integers
	// -- objects to be used for database access
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rset = null;

    // -- connect to the world database
    // -- this is the connector to the database, default port is 3306
//    private String url = "jdbc:mysql://localhost:3306/world";
    private String url = "jdbc:mysql://localhost:3306/userdatabase";
    
    // -- this is the username/password, created during installation and in MySQL Workbench
    //    When you add a new_table make sure you give them the appropriate Administrative Roles
    //    (DBA sets all which works fine)
    private static String username = "root";
    private static String password = "ravenisdark32!";

    public DBaseConnection() {
    	try {
			// -- make the connection to the database
			conn = DriverManager.getConnection(url, username, password);
	        
			// -- These will be used to send queries to the database
	        stmt = conn.createStatement();
	        rset = stmt.executeQuery("SELECT VERSION()");
	
	        if (rset.next()) {
	            System.out.println("MySQL version: " + rset.getString(1) + "\n=====================\n");
	        }
		} 
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
    	
    }

    public User getUser(String username){
    	try{
			rset = stmt.executeQuery("SELECT * FROM new_table WHERE username='"+username+"';");
			rset.next();
			return new User(rset.getString("username"),rset.getString("password"),rset.getString("email"),rset.getInt("lockcount"));
    	}
    	catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
    	return new User("Nemo","Nihil","Null");
	}

	public void updateUserStringData(String username, String contentType, String content) throws SQLException {
    	try {
			stmt.executeUpdate("UPDATE new_table SET " + contentType + "='" + content + "' WHERE username='" + username + "';");
		}
		catch (SQLException ex){
			System.out.println("Failed updating UserStringData");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void updateUserIntData(String username, String contentType, int content){
    	try {
    		System.out.println("UPDATE new_table SET " + contentType + "=" + content + " WHERE username='" + username + "';");
			stmt.executeUpdate("UPDATE new_table SET " + contentType + "=" + content + " WHERE username='" + username + "';");
		}
    	catch (SQLException ex){
    		System.out.println("Failed updating UserIntData");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public int exists(String field, String search) throws SQLException {
		try {
			rset = stmt.executeQuery("SELECT COUNT(1) FROM new_table WHERE '" + search + "' = " + field);
			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			rset.next();

			if (Integer.parseInt(rset.getString(1)) == 0){
				return 0;
			}else if(Integer.parseInt(rset.getString(1)) == 1){
				return 1;
			}else {
				return 2;
			}
		}catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
	}

	public void addNewRecord(String username, String password, String email) throws SQLException {
		try {
			stmt.executeUpdate("INSERT INTO new_table VALUE('"+username+"','"+password+"','"+email+"', 0);");
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	//UPDATE `new_schema`.`new_table` SET `lockcount` = '387' WHERE (`username` = 'Ben');
	public int getLockCount(String username) throws SQLException {
		try {
			int lockcount = -1;
			rset = stmt.executeQuery("SELECT * FROM new_table WHERE '"+username+"' = username");
			rset.next();
			lockcount = Integer.parseInt(rset.getString(4));
			return lockcount;


		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return -2;
		}
	}

	public boolean passwordMatch(String username, String password) throws SQLException {
		try {
			int lockcount = -1;
			rset = stmt.executeQuery("SELECT * FROM new_table WHERE '" + username + "' = username");
			rset.next();
			String truePass = rset.getString(2);
			if(truePass.equals(password)){
				return true;
			}else {
				return false;
			}

		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}

	}

	public static void main(String[] args) throws SQLException {
		System.out.println("Test db");
		
		DBaseConnection dbc = new DBaseConnection();
		// dbc.accessDatabase();
		User test = dbc.getUser("cHerberg");
		System.out.println(test.toString());
	}

}
