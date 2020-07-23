package programa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.jpl7.Query;

public class Motor_Inferencia 
{
	/* ******************************************************************************************************************************************** */
	/* Propiedades del motor de inferencia */
	
	/* Motor Rete de reglas: */
	
	/* Direcciones de scripts .clp :*/
	private String base_conocimiento = "consult('base_conocimiento.pl')";
	
	/* Instancias para conectar a la base de datos: */
	private ConectorSQL conector;
	private Connection conexion;
	
	/* Instancias para almacenar datos de la base de datos: */
	private ResultSet cargos;
	private ResultSet empleados;
	
	/* Cadenas con las consultas en lenguaje SQL: */
	private String sql_query_cargos;
	private String sql_query_empleados;
	
	/* ******************************************************************************************************************************************** */
	/* Metodos para establecer las propiedades de la clase  */
	
	public void setBaseConocimiento(String script)
	{
		base_conocimiento = script;
	}

	public void setSQLCargos(String query)
	{
		sql_query_cargos = query;
	}
	
	public void setSQLEmpleados(String query)
	{
		sql_query_empleados = query;
	}
	
	/* ******************************************************************************************************************************************** */

	
	/* ******************************************************************************************************************************************** */
	/* Metodo para inicializar el Motor de reglas */
	
	public void Inicializar()
	{
		// Para hacer algo supongo?
		Query con = new Query(base_conocimiento);
				
		// Nota: Comprobar que estas consultas tienen bien la sintaxis :v
		setSQLCargos("SELECT (Nombre, Familia, Grupo_ocupacional, Nivel_funcional, Grupo_laboral) FROM Cargos");
		setSQLEmpleados("SELECT * FROM Empleados");
		// Introducir los registros de la base de datos como hechos en la base de conocimiento del MI
		// Nota: por la forma en que funciona Jess, puede que haya que resincronizar
		SincronizarBD(cargos, empleados);
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para conectar con la base de datos*/
	
	public void ConectarBD()
	{
		
		conector = new ConectorSQL();
		conexion = conector.getConexion("UTP_empleados", "root", "");
		
		try {
			Statement st = conexion.createStatement();
			cargos = st.executeQuery(sql_query_cargos);
			empleados = st.executeQuery(sql_query_empleados);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse con la Base de Datos");
			e.printStackTrace();
		}
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para introducir los registros de la base de datos como hechos en la base de conocimiento del motor de reglas */
	
	public void SincronizarBD(ResultSet cargos, ResultSet empleados)
	{
		ConectarBD();
		try {
			
			while(cargos.next()) 
			{
				/*
				"nombre", cargos.getString(1));
				"familia", cargos.getString(2));
				"grupo_ocupacional", cargos.getString(3);
				"nivel_funcional", cargos.getString(4));
				"grupo_laboral", cargos.getString(5));
				*/
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al insertar los hechos de cargos");
			e.printStackTrace();
		}/*catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al sincronizarse con la Base de Datos");
			e.printStackTrace();
		}*/
		
		try {
		
			while(empleados.next()) 
			{
			/*
			  	("cedula", empleados.getString(1));
			 	("nombre", empleados.getString(2));
				("apellido", empleados.getString(3));
				("telefono", empleados.getString(4));
				("cargo_actual", empleados.getString(5));
				("sexo", empleados.getString(6));
				("f_nacimiento", empleados.getString(7));
				("formacion_academica", empleados.getString(8));
				("experiencia", empleados.getString(9));
				("referencias_laborales",empleados.getString(10));
				("centro_regional",empleados.getString(11));
				("pruebas_psicotecnicas",empleados.getString(12));
			 */
			}
			
		
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al insertar los hechos de empleado");
			ex.printStackTrace();
		} /*catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al sincronizarse con la Base de Datos");
			e.printStackTrace();
		}*/		
	}
	/* ******************************************************************************************************************************************** */
	
	public void ConsultaPrueba()
	{
		try 
		{
			Query con = new Query(base_conocimiento);
			String consulta = "empleado(CIP, Nombre, Apellido, Telefono,'ABOGADO', Sexo, _, _, _, _, _, _)";
			Query ejecutar = new Query(consulta);
			if(ejecutar.hasSolution())
			{
				JOptionPane.showMessageDialog(null, "Cedula: "+ ejecutar.oneSolution().get("CIP").toString() + " Nombre: "+ ejecutar.oneSolution().get("Nombre").toString());
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"ERROR LPM");
		}
		
		
		
	}
}
