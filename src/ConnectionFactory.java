import java.sql.Connection;    
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;



public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory () {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/informacoes_pessoais?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("root");
		this.dataSource = comboPooledDataSource;
		
		comboPooledDataSource.setMaxPoolSize(15);
	}
	
	
	public Connection recuperandoConexao () throws SQLException {
		return this.dataSource.getConnection();
	}
}


