package Server;// -- download MySQL from: http://dev.mysql.com/downloads/
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
    private String url = "jdbc:mysql://localhost:3306/new_schema";
    
    // -- this is the username/password, created during installation and in MySQL Workbench
    //    When you add a user make sure you give them the appropriate Administrative Roles
    //    (DBA sets all which works fine)
    private static String username = "<<Your MySQL username>>";
    private static String password = "<<Your MySQL password>>";

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
	public DBaseConnection(String username1, String password2) {
		try {
			this.username = username1;
			this.password = password2;
			// -- make the connection to the database
			conn = DriverManager.getConnection(url, username, password);

			// -- These will be used to send queries to the database
			stmt = conn.createStatement();
			rset = stmt.executeQuery("SELECT VERSION()");

			if (rset.next()) {
				//System.out.println("MySQL version: " + rset.getString(1) + "\n=====================\n");
			}
		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	public void accessDatabase() {
		try {
			String uname = "hello";
			String pword = "world";
			String email = "helloworld@gmail.com";
			int lockcount = 3;

            
            // -- delete this record in case it exists
            stmt.executeUpdate("DELETE FROM new_table WHERE username='ccreinhart';");

            System.out.println("Original Contents");
            rset = stmt.executeQuery("SELECT * FROM new_table;");
            printResultSet(rset);

			//Find username code
            rset = stmt.executeQuery("SELECT COUNT(1) FROM new_table WHERE 'ben Sottile' = username");
            printResultSet(rset);


           
            // -- a query will return a ResultSet
            // -- city is a table within the world database
//            rset = stmt.executeQuery("SELECT * FROM city;");
            System.out.println("Inserted Contents");
            stmt.executeUpdate("INSERT INTO new_table VALUE('ben Sottile', 'cHerberg1234', 'death@yahoo.com', 0);");
            rset = stmt.executeQuery("SELECT * FROM new_table;");
            printResultSet(rset);
            
            System.out.println("Updated Contents");
            stmt.executeUpdate("UPDATE new_table SET lockcount=1 WHERE username='ccreinhart';");
            rset = stmt.executeQuery("SELECT * FROM new_table;");
            printResultSet(rset);            
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
//	        System.out.println("columns: " + numberOfColumns);
	        
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

	
	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		System.out.print("MySQL username: ");
		username = kb.next();
		System.out.print("MySQL password: ");
		password = kb.next();
		
		DBaseConnection dbc = new DBaseConnection();
		dbc.accessDatabase();
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
			stmt.executeUpdate("INSERT INTO new_table VALUE('"+username+"','"+password+"','"+email+"', 0,0);");
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

	public void incrementLockCount(String username) {
		try {
			int original = getLockCount(username);
			if(original >= 3){

			}else {
				int newCount = original + 1;
				stmt.executeUpdate("UPDATE `new_schema`.`new_table` SET `lockcount` = '"+newCount+"' WHERE (`username` = '"+username+"')");
			}


		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		//UPDATE `new_schema`.`new_table` SET `lockcount` = '387' WHERE (`username` = 'Ben');
	}
	public void resetLockCount(String username) {
		try {
			int newCount = 0;
			stmt.executeUpdate("UPDATE `new_schema`.`new_table` SET `lockcount` = '"+newCount+"' WHERE (`username` = '"+username+"')");

		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
    }
		public void login(String username) {
			try {
				int newCount = 1;
				stmt.executeUpdate("UPDATE `new_schema`.`new_table` SET `loggedin` = '"+newCount+"' WHERE (`username` = '"+username+"')");

			}catch (SQLException ex){
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		//UPDATE `new_schema`.`new_table` SET `lockcount` = '387' WHERE (`username` = 'Ben');
			}
		public void logout(String username) {
			try {
				int newCount = 0;
				stmt.executeUpdate("UPDATE `new_schema`.`new_table` SET `loggedin` = '"+newCount+"' WHERE (`username` = '"+username+"')");
			}catch (SQLException ex){
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
			//UPDATE `new_schema`.`new_table` SET `lockcount` = '387' WHERE (`username` = 'Ben');
		}

		public int numberOfRegisteredUsers(){
		try {
			rset = stmt.executeQuery("SELECT COUNT(*) FROM new_table");
			rset.next();
			String registeredUsers = rset.getString(1);
			return Integer.parseInt(registeredUsers);

		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return -1;
	}
	public String[] usersLockedOut(){
		try {
			int count = 0;
			String[] usersLocked;
			String usernameTBA = "";
			rset = stmt.executeQuery("SELECT COUNT(*) FROM new_table where '3' <= lockcount");
			rset.next();
			int numberOfUsersLockedOut = Integer.parseInt(rset.getString(1));
			if (numberOfUsersLockedOut == 0){
				usersLocked = new String[]{"NO USERS ARE LOCKED OUT HORRAY"};
				return usersLocked;
			}
			usersLocked = new String[numberOfUsersLockedOut];
			rset = stmt.executeQuery("SELECT * FROM new_table where '3' <= lockcount");
			while (rset.next()) {
				// -- loop through the columns of the ResultSet
				usernameTBA = rset.getString(1);
				usersLocked[count] = usernameTBA;
				count++;
			}
			return usersLocked;



		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			String[] usersLocked = new String[]{"Error"};
			return usersLocked;
		}


	}


	public int numberOfLoggedInUsers() {
		try {
			rset = stmt.executeQuery("SELECT COUNT(*) FROM new_table where '1' = loggedin");
			rset.next();
			String registeredUsers = rset.getString(1);
			return Integer.parseInt(registeredUsers);

		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return -1;
	}
	public int numberOfLoggedOutUsers() {
		try {
			rset = stmt.executeQuery("SELECT COUNT(*) FROM new_table where '0' = loggedin");
			rset.next();
			String registeredUsers = rset.getString(1);
			return Integer.parseInt(registeredUsers);

		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return -1;
	}
	public String[] usersLoggedIn(){
		try {
			int count = 0;
			String[] usersLoggedIn;
			String usernameTBA = "";
			rset = stmt.executeQuery("SELECT COUNT(*) FROM new_table where '1' = loggedin");
			rset.next();
			int numberOfUsersLoggedIn = Integer.parseInt(rset.getString(1));
			if (numberOfUsersLoggedIn == 0){
				usersLoggedIn = new String[]{"NO USERS ARE LOGGED IN"};
				return usersLoggedIn;
			}
			usersLoggedIn = new String[numberOfUsersLoggedIn];
			rset = stmt.executeQuery("SELECT * FROM new_table where '1' = loggedin");
			while (rset.next()) {
				// -- loop through the columns of the ResultSet
				usernameTBA = rset.getString(1);
				usersLoggedIn[count] = usernameTBA;
				count++;
			}
			return usersLoggedIn;



		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			String[] usersLocked = new String[]{"Error"};
			return usersLocked;
		}


	}

	public String[] getUsersLoggedOut() {
		try {
			int count = 0;
			String[] usersLoggedOut;
			String usernameTBA = "";
			rset = stmt.executeQuery("SELECT COUNT(*) FROM new_table where '0' = loggedin");
			rset.next();
			int numberOfUsersLoggedOut = Integer.parseInt(rset.getString(1));
			if (numberOfUsersLoggedOut == 0){
				usersLoggedOut = new String[]{"NO USERS ARE LOGGED IN"};
				return usersLoggedOut;
			}
			usersLoggedOut = new String[numberOfUsersLoggedOut];
			rset = stmt.executeQuery("SELECT * FROM new_table where '0' = loggedin");
			while (rset.next()) {
				// -- loop through the columns of the ResultSet
				usernameTBA = rset.getString(1);
				usersLoggedOut[count] = usernameTBA;
				count++;
			}
			return usersLoggedOut;



		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			String[] usersLocked = new String[]{"Error"};
			return usersLocked;
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
}







