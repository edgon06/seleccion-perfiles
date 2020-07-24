package programa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConectorSQL 
{
	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	private String url=null;
	
	/* ******************************************************************************************************************************************** */
	/* Metodos para establecer las propiedades de la clase  */
	
	public void setURL(String direccion)
	{
		url = direccion;
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo que devuelve una conexion con la base de datos dada */
	
	public Connection getConexion(String bd, String usuario, String contrasena)
	{
		Connection conexion = null;
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			if(url==null)
			{	
				// url por defecto es "jdbc:mysql://localhost/"+ nombre de la base de datos
				url = "jdbc:mysql://localhost/"+bd;
			}else
			{
				url = url+bd;
			}
				
			conexion = DriverManager.getConnection(url, usuario, contrasena); 
			
			
			
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error al cargar el Driver");
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos");
				e.printStackTrace();
			}
	
		return conexion;
	}
	/* ******************************************************************************************************************************************** */
	
	/* Nota: verificar que pedo con que la conexion no se cierra? :v */
}
