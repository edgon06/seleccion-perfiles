package programa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import jess.Fact;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

public class Motor_Inferencia 
{
	/* ******************************************************************************************************************************************** */
	/* Propiedades del motor de inferencia */
	
	/* Motor Rete de reglas: */
	private Rete MI; 
	
	/* Direcciones de scripts .clp :*/
	private String reglas;
	private String funciones;
	private String plantillas;
	private String consultas;
	
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
	
	public void setReglas(String script)
	{
		reglas = script;
	}
	
	public void setFunciones(String script)
	{
		funciones = script;
	}
	
	public void setPlantillas(String script)
	{
		plantillas = script;
	}
	
	public void setConsultas(String script)
	{
		consultas = script;
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
	/* Metodo para ejecutar Scripts .clp */
	
	public static void EjecutarScript(Rete MI, String script)
	{
		try 
		{
			// Ejecutar codigo en archivo en la direccion dentro de la variable 'script'
			MI.batch(script);
		} catch (JessException e) 
		{
			JOptionPane.showMessageDialog(null, "Error al ejecutar el codigo del archivo "+ script);
			e.printStackTrace();
		}
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para inicializar el Motor de reglas */
	
	public void Inicializar()
	{
		// Crear instancia del motor Rete
		MI = new Rete();
		
		
		// Ejecutar script en lenguaje Jess que definira las reglas del motor de inferencia
		EjecutarScript(MI, reglas);
		// Ejecutar script en lenguaje Jess que definira las funciones a utilizar por el motor de inferencias
		EjecutarScript(MI, funciones);
		// Ejecutar script en lenguaje Jess que definira las plantillas de los hechos de la base de conocimiento
		EjecutarScript(MI, plantillas);
		// Ejecutar script en lenguaje Jess que definira las reglas de consulta a la base de conocimientos
		EjecutarScript(MI, consultas);
		
		// Nota: Comprobar que estas consultas tienen bien la sintaxis :v
		setSQLCargos("SELECT (Nombre, Familia, Grupo_ocupacional, Nivel_funcional, Grupo_laboral) FROM Cargos");
		setSQLEmpleados("SELECT * FROM Empleados");
		// Introducir los registros de la base de datos como hechos en la base de conocimiento del MI
		SincronizarBD(MI,cargos, empleados);
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
	
	public void SincronizarBD(Rete MI, ResultSet cargos, ResultSet empleados)
	{
		ConectarBD();
		try {
			Fact cargo_hecho = new Fact("cargo",MI);
			while(cargos.next()) 
			{
				cargo_hecho.setSlotValue("nombre", new Value(cargos.getString(1), RU.STRING));
				cargo_hecho.setSlotValue("familia", new Value(cargos.getString(2), RU.STRING));
				cargo_hecho.setSlotValue("grupo_ocupacional", new Value(cargos.getString(3), RU.STRING));
				cargo_hecho.setSlotValue("nivel_funcional", new Value(cargos.getString(4), RU.STRING));
				cargo_hecho.setSlotValue("grupo_laboral", new Value(cargos.getString(5), RU.STRING));
				MI.assertFact(cargo_hecho);
			}
		} catch (JessException e) {
			JOptionPane.showMessageDialog(null, "Error al insertar los hechos de cargos");
			e.printStackTrace();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al sincronizarse con la Base de Datos");
			e.printStackTrace();
		}
		
		try {
			Fact empleado_hecho = new Fact("empleado",MI);
		
			while(empleados.next()) 
			{
				empleado_hecho.setSlotValue("cedula", new Value(empleados.getString(1), RU.STRING));
				empleado_hecho.setSlotValue("nombre", new Value(empleados.getString(2), RU.STRING));
				empleado_hecho.setSlotValue("apellido", new Value(empleados.getString(3), RU.STRING));
				empleado_hecho.setSlotValue("telefono", new Value(empleados.getString(4), RU.STRING));
				empleado_hecho.setSlotValue("cargo_actual", new Value(empleados.getString(5), RU.STRING));
				empleado_hecho.setSlotValue("sexo", new Value(empleados.getString(6), RU.STRING));
				empleado_hecho.setSlotValue("f_nacimiento", new Value(empleados.getString(7), RU.STRING));
				empleado_hecho.setSlotValue("formacion_academica", new Value(empleados.getString(8), RU.STRING));
				empleado_hecho.setSlotValue("experiencia", new Value(empleados.getString(9), RU.STRING));
				empleado_hecho.setSlotValue("referencias_laborales", new Value(empleados.getString(10), RU.STRING));
				empleado_hecho.setSlotValue("centro_regional", new Value(empleados.getString(11), RU.STRING));
				empleado_hecho.setSlotValue("pruebas_psicotecnicas", new Value(empleados.getString(12), RU.STRING));
				MI.assertFact(empleado_hecho);
			}
			
		
		} catch (JessException e) {
			JOptionPane.showMessageDialog(null, "Error al insertar los hechos de empleado");
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al sincronizarse con la Base de Datos");
			e.printStackTrace();
		}		
	}
	/* ******************************************************************************************************************************************** */
}
