package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement pt = null;
		//inserindo dados no banco de dados JDBC
		
		try {
			conn = DB.getConnection();
			/* um objeto sendo inserido
			pt = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+"VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);//recebe o valor da ID do Banco de Dados
			//inserindo os dados
			pt.setString(1, "Carl Purple");
			pt.setString(2, "carl@gmail.com");
			pt.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			pt.setDouble(4, 3000.0);
			pt.setInt(5, 4);
			*/
			
			pt = conn.prepareStatement("insert into department (name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			//executando o comando sql
			int rowsAffected = pt.executeUpdate();//rows recebe um numero do tamanho das linhas
			
			if(rowsAffected > 0) {
				ResultSet rs = pt.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);//pega o valor do Id na primeira coluna
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rown affected!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		/*catch (ParseException e) {
			e.printStackTrace();
		}*/
		finally {
			DB.closeStatement(pt);
			DB.closeConnection();
		}
		
	}

}
