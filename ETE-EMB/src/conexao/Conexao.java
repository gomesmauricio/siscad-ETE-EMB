package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/etemb?useSSL=false", "root", "1234");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	} 
	
}
