package defaultpackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionHandler {
	public static Connection getConnection()
	{
		Connection conn=null;
		Properties prop=new Properties();
		try {
			prop.load(new FileReader("src/connection.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/truyum", prop);
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return conn;	
	}
	

}
