package programa;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import org.jpl7.*;
import org.jpl7.fli.*;

public class Motor_Inferencia 
{
	/* ******************************************************************************************************************************************** */
	/* Propiedades del motor de inferencia */
	
	/* Motor Rete de reglas: */
	
	/* Direcciones de archivo .pl :*/
	// Directorio Christian: C:\Users\\bob_0\eclipse-workspace\seleccion-perfiles\src\programa\base_conocimiento.pl
	// Directorio Edwin: D:\Archivos\Proyectos\eclipse-workspace\seleccion-perfiles\src\programa\base_conocimiento.pl
	
	private String base_conocimiento = "D:\\Archivos\\Proyectos\\eclipse-workspace\\seleccion-perfiles\\src\\programa\\base_conocimiento.pl";
	
	/* Instancias para conectar a la base de datos: */
	private ConectorSQL conector;

	/* Cadenas con las consultas en lenguaje SQL: */
	private String sql_query_cargos = "SELECT Nombre, Familia, Grupo_ocupacional, Nivel_funcional, Grupo_laboral FROM Cargos";
	private String sql_query_empleados = "SELECT * FROM Empleados";
	
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
		CargarReglas();
			
		// Introducir los registros de la base de datos como hechos en la base de conocimiento del MI
		SincronizarBD();
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para introducir los registros de la base de datos como hechos en la base de conocimiento del motor de reglas */
	
	public void SincronizarBD()
	{
		ResultSet cargos = null;
		ResultSet empleados = null;
		Cargo c = new Cargo();;
		Empleado e = new Empleado();
		
		conector = new ConectorSQL();
		Connection conexion= conector.getConexion("UTP_empleados", "root", "");
		Statement st; 
		Statement st2;
		try {
			st = conexion.createStatement();
			cargos = st.executeQuery(sql_query_cargos);
			try {
				
				while(cargos.next()) 
				{
					c.setNombre(cargos.getString(1));
					c.setFamilia(cargos.getString(2));
					c.setGupo_ocupacional(cargos.getString(3));
					c.setNivel_funcional(cargos.getString(4));
					c.setGrupo_laboral(cargos.getString(5));
					
					escribir(assertz(c));
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al insertar los hechos de cargos");
				ex.printStackTrace();
			}	
			
			st2 = conexion.createStatement();
			empleados = st2.executeQuery(sql_query_empleados);	
			try {	
				while(empleados.next()) 
				{
				  	e.setCedula(empleados.getString(1));
				 	e.setNombre(empleados.getString(2));
					e.setApellido(empleados.getString(3));
					e.setTelefono(empleados.getString(4));;
					e.setCargo_actual(empleados.getString(5));
					e.setSexo(empleados.getString(6));
					e.setF_nacimiento(empleados.getString(7));
					e.setFormacion_academica(empleados.getString(8));
					e.setExperiencia(empleados.getString(9));
					e.setReferencias_laborales(empleados.getString(10));
					e.setCentro_regional(empleados.getString(11));;
					e.setPruebas_psicotecnicas(empleados.getString(12));
		
					escribir(assertz(e));	
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al insertar los hechos de empleado");
				ex.printStackTrace();
			} 
			
		} catch (SQLException exc) {
			JOptionPane.showMessageDialog(null, "Error al conectarse con la Base de Datos");
			exc.printStackTrace();
		}
		finally
		{
			try {
				conexion.close();
				JOptionPane.showMessageDialog(null, "Base de Conocimiento cargada con exito");
			} catch (SQLException exc) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error al cerrar la conexion a la Base de Datos");
				exc.printStackTrace();
			}
		}				
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para probar conexion */
	public void ConsultaPrueba()
	{
		try 
		{
			
			System.out.println("Hola Mundo");
			
			
			// Directorio Christian: C:\\Users\\bob_0\\eclipse-workspace\\seleccion-perfiles\\src\\programa\\base_conocimiento.pl
			// Directorio Edwin: D:\Archivos\Proyectos\eclipse-workspace\seleccion-perfiles\src\programa\base_conocimiento.pl
			Query q1 = 
				    new Query( 
					"consult", 
					new Term[] {new Atom(base_conocimiento)} 
				    );
			
			System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
			
			
			Variable X = new Variable("X");
			Query q2 = 
					  new Query( 
					      "empleado", 
					      new Term[] {X	,new Atom("Edwin"),new Atom("Gonzalez"),
					      new Atom("62006000"),new Atom("ABOGADO"),new Atom("m"),new Atom("1999-02-06"),
					      new Atom("Lic. Ing. de Sistemas y Computacion"),new Atom("2 a�os - ABOGADO"),
					      new Atom("63000000 - Sr. Paz"),new Atom("Panama Oeste"),new Atom("Administrador de Soporte Tecnico")} 
					  );
			System.out.println( 
					  "hay solucion? " + 
					  ( q2.hasSolution() ? "provable" : "not provable" ) 
					);	
			
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			for ( int i=0 ; i < solutions.length ; i++ ) { 
			  System.out.println( "X = " + solutions[i].get("X"));
			}	  
		}catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"ERROR LPM");
		}
				
	}
	
	public void CargarReglas()
	{
		/*
		 * % Reglas
%filtrar_cargo(CIP, Cargo):-.

%filtrar_cargo_anterior(CIP):-.
%filtrar_experiencia(CIP):-.
%filtrar_grupo_ocupacional(CIP):-.

%filtrar_cargo_cargo_anterior(CIP):-.
%filtrar_cargo_experiencia(CIP):-.

%filtrar_cargo_grupo_ocupacional(CIP):-.
%filtrar_cargo_anterior_experiencia(CIP):-.
%filtrar_cargo_anterior_grupo_ocupacional(CIP):-.


%filtrar_experiencia_grupo_ocupacional(CIP):-.
%filtrar_centro_regional():-.
%filtrar_edad():-.
%filtrar_sexo():-.
		*/
	}
	
	public String assertz(Empleado e)
	{
		String hecho;
		hecho = "empleado('"+e.getCedula()+"', '"+e.getNombre()+"', '"+e.getApellido()+"', '"+e.getTelefono()+"', '"+e.getCargo_actual()+"', '"+e.getSexo()+"', '"+e.getF_nacimiento()+"', '"+e.getFormacion_academica()+"', '"+e.getExperiencia()+"', '"+e.getReferencias_laborales()+"', '"+e.getCentro_regional()+"', '"+e.getPruebas_psicotecnicas()+"').";
		return hecho;
	}
	
	public String assertz(Cargo c)
	{
		String hecho;
		hecho = "cargo('"+c.getNombre()+"','"+c.getFamilia()+"','"+c.getGupo_ocupacional()+"','"+c.getNivel_funcional()+"','"+c.getGrupo_laboral()+"').";
		return hecho;	
	}
	
	public void escribir(String hecho)
	{
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	// Se construye el fichero en la direccion contenida por la variable "base_conocimiento"
            fichero = new FileWriter(base_conocimiento,true);
            pw = new PrintWriter(fichero);
            pw.println(hecho);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Se aprovecha el finally para asegurar que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           		// 
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
	public void EliminarArchivo() {
		
		File fichero = new File(base_conocimiento);

		if (fichero.delete())
		   System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
		   System.out.println("El fichero no puede ser borrado");
	}
	
	
	
	
	
	
	
}
