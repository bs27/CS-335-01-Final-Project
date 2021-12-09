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
    //    When you add a user make sure you give them the appropriate Administrative Roles
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
			rset = stmt.executeQuery("SELECT * FROM user WHERE username='"+username+"';");
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

	public void updateUserPassword(String username, String password){
		try{
			rset = stmt.executeQuery("UPDATE user SET user WHERE username='"+username+"';");
		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void printResultSet(ResultSet rset)
	{
		try {
			// -- the metadata tells us how many columns in the data
			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			System.out.println("columns: " + numberOfColumns);

			// -- loop through the ResultSet one row at a time
			//    Note that the ResultSet starts at index 1
			while (rset.next()) {
				// -- loop through the columns of the ResultSet
				for (int i = 1; i < numberOfColumns; ++i) {
					System.out.print(rset.getString(i) + "\t");
				}
				System.out.println(rset.getString(numberOfColumns));
			}
		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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
