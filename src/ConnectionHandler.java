import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;
public class ConnectionHandler {
	static public Connection getConnection()
	{
		Properties prop=new Properties();
		prop.load(new FileReader("src/connection.properties"));
		return null;
	}
	

}
