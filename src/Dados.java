
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dados
 */
@WebServlet("/dados")
public class Dados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Endereco endereco = new Endereco();
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String sexo = request.getParameter("sexo");

		RequestDispatcher rd = request.getRequestDispatcher("ExibiDados.jsp");
		request.setAttribute("nome", nome);
		request.setAttribute("idade", idade);
		request.setAttribute("sexo", sexo);

		rd.forward(request, response);

		ConnectionFactory connectionfactory = new ConnectionFactory();
		try (Connection connection = connectionfactory.recuperandoConexao()) {
			connection.setAutoCommit(false);

			try (PreparedStatement stm = connection.prepareStatement(
					"INSERT INTO dados (nome,idade,sexo) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
				stm.setString(1, nome);
				stm.setString(2, idade);
				stm.setString(3, sexo);
				stm.execute();
				
				connection.commit();
				
				connection.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
