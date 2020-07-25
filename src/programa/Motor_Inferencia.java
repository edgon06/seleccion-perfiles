package programa;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.lang.Integer;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
	
	private String base_conocimiento = System.getProperty("user.dir") + "\\base_conocimiento.pl";

	/* Instancias para conectar a la base de datos: */
	private ConectorSQL conector;
	private Cargo c;

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
		JOptionPane.showMessageDialog(null, base_conocimiento);
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para introducir los registros de la base de datos como hechos en la base de conocimiento del motor de reglas */
	
	public void SincronizarBD()
	{
		ResultSet cargos = null;
		ResultSet empleados = null;
		Cargo c = new Cargo();
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
			System.out.println("Probando conexion con Prolog...");
			Query q1 = 
				    new Query( 
					"consult", 
					new Term[] {new Atom(base_conocimiento)} 
				    );
			
			System.out.println( "Conection with Prolog Knowledge Base " + (q1.hasSolution() ? "succeeded" : "failed"));
			Variable X = new Variable("X");
			
			Query q2 = 
					  new Query( 
					      "empleado", 
					      new Term[] {X	,new Atom("Edwin"),new Atom("Gonzalez"),
					      new Atom("62006000"),new Atom("ABOGADO"),new Atom("m"),new Atom("1999-02-06"),
					      new Atom("Lic. Ing. de Sistemas y Computacion"),new Atom("2 años - ABOGADO"),
					      new Atom("63000000 - Sr. Paz"),new Atom("Panama Oeste"),new Atom("Administrador de Soporte Tecnico")} 
					  );
			System.out.println( 
					  "Existe un Edwin?: " + 
					  ( q2.hasSolution() ? "puede ser" : "nope" ) 
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
	
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para obtener un perfil con la cedula dada */
	
	public Empleado getPerfil(String Cedula)
	{
		Empleado e = new Empleado();
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		
		//Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");
		Variable Telefono = new Variable("Telefono");
		Variable Cargo = new Variable("Cargo");
		Variable Sexo = new Variable("Sexo");
		Variable Fecha_nacimiento = new Variable("Fecha_nacimiento");
		Variable Formacion_academica = new Variable("Formacion_academica");
		Variable Experiencia = new Variable("Experiencia");
		Variable Referencias = new Variable("Referencias");
		Variable Centro_Regional = new Variable("Centro_Regional");
		Variable Pruebas_psicotecnicas = new Variable("Pruebas_psicotecnicas");
		
		Query q2 = 
				  new Query( 
				      "empleado", 
				      new Term[] {new Atom(Cedula)	,Nombre ,Apellido,
				    		  Telefono,Cargo,Sexo, Fecha_nacimiento,
				    		  Formacion_academica, Experiencia,
				    		  Referencias,Centro_Regional ,Pruebas_psicotecnicas} 
				  );
		if(q2.hasSolution())
		{
			e.setCedula(Cedula);
			e.setNombre(q2.oneSolution().get("Nombre").toString());
			e.setApellido(q2.oneSolution().get("Apellido").toString());
			e.setTelefono(q2.oneSolution().get("Telefono").toString());
			e.setCargo_actual(q2.oneSolution().get("Cargo").toString());
			e.setSexo(q2.oneSolution().get("Sexo").toString());
			e.setF_nacimiento(q2.oneSolution().get("Fecha_nacimiento").toString());
			e.setFormacion_academica(q2.oneSolution().get("Formacion_academica").toString());
			e.setExperiencia(q2.oneSolution().get("Experiencia").toString());
			e.setReferencias_laborales(q2.oneSolution().get("Referencias").toString());
			e.setCentro_regional(q2.oneSolution().get("Centro_Regional").toString());
			e.setPruebas_psicotecnicas(q2.oneSolution().get("Pruebas_psicotecnicas").toString());
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Error al encontrar empleado por cedula ");
		}
		return e;
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para obtener todos los perfiles */
	
	public void getPerfiles(DefaultTableModel modelo_tabla)
	{	
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		
		Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");
		Variable Telefono = new Variable("Telefono");
		Variable Cargo = new Variable("Cargo");
		Variable Sexo = new Variable("Sexo");
		Variable Fecha_nacimiento = new Variable("Fecha_nacimiento");
		Variable Formacion_academica = new Variable("Formacion_academica");
		Variable Experiencia = new Variable("Experiencia");
		Variable Referencias = new Variable("Referencias");
		Variable Centro_Regional = new Variable("Centro_Regional");
		Variable Pruebas_psicotecnicas = new Variable("Pruebas_psicotecnicas");
	
		Query q2 = 
				  new Query( 
				      "empleado", 
				      new Term[] { CIP, Nombre , Apellido, Telefono , Cargo, Sexo, Fecha_nacimiento, Formacion_academica, Experiencia, Referencias, Centro_Regional, Pruebas_psicotecnicas } 
				  );
		modelo_tabla.addColumn("Nombre");
		modelo_tabla.addColumn("CIP");
		String[] datos = new String[2];
		
		if(q2.hasSolution())
		{			
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			for ( int i=0 ; i < solutions.length ; i++ ) 
			{ 
				datos[0] = solutions[i].get("Nombre").toString() + " " + solutions[i].get("Apellido").toString();
				datos[1] = solutions[i].get("CIP").toString();
				modelo_tabla.addRow(datos);
			}
		}
	}
	
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para obtener perfiles con un cargo dado */
	
	public void FiltrarPerfilesCargo(String cargo, DefaultTableModel modelo_tabla) 
	{
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
		
		Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");
		Variable Telefono = new Variable("Telefono");
		//Variable Cargo = new Variable("Cargo");
		Variable Sexo = new Variable("Sexo");
		Variable Fecha_nacimiento = new Variable("Fecha_nacimiento");
		Variable Formacion_academica = new Variable("Formacion_academica");
		Variable Experiencia = new Variable("Experiencia");
		Variable Referencias = new Variable("Referencias");
		Variable Centro_Regional = new Variable("Centro_Regional");
		Variable Pruebas_psicotecnicas = new Variable("Pruebas_psicotecnicas");
		Query q2 = 
				  new Query( 
				      "empleado", 
				      new Term[] { CIP, Nombre , Apellido, Telefono ,new Atom(cargo), Sexo, Fecha_nacimiento, Formacion_academica, Experiencia, Referencias, Centro_Regional, Pruebas_psicotecnicas } 
				     
				  );
		
		
		JOptionPane.showMessageDialog(null, "Esta a punto de filtrar perfiles por cargo actual ");
			
		modelo_tabla.addColumn("Nombre");
		modelo_tabla.addColumn("CIP");
	
		String[] datos = new String[2];
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			for ( int i=0 ; i < solutions.length ; i++ ) 
			{ 
				datos[0] = solutions[i].get("Nombre").toString() + " " + solutions[i].get("Apellido").toString();
				datos[1] = solutions[i].get("CIP").toString();
				modelo_tabla.addRow(datos);
			}		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Filtrar perfiles por edad */
	
	public void FiltrarPerfilesEdad(int Edad, DefaultTableModel modelo_tabla)
	{
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		
		Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");
		Variable Telefono = new Variable("Telefono");
		Variable Cargo = new Variable("Cargo");
		Variable Sexo = new Variable("Sexo");
		Variable Fecha_nacimiento = new Variable("Fecha_nacimiento");
		Variable Formacion_academica = new Variable("Formacion_academica");
		Variable Experiencia = new Variable("Experiencia");
		Variable Referencias = new Variable("Referencias");
		Variable Centro_Regional = new Variable("Centro_Regional");
		Variable Pruebas_psicotecnicas = new Variable("Pruebas_psicotecnicas");
	
		Query q2 = 
				  new Query( 
				      "empleado", 
				      new Term[] { CIP, Nombre , Apellido, Telefono , Cargo, Sexo, Fecha_nacimiento, Formacion_academica, Experiencia, Referencias, Centro_Regional, Pruebas_psicotecnicas } 
				  );
		
		modelo_tabla.addColumn("Nombre");
		modelo_tabla.addColumn("CIP");
		String[] datos = new String[2];
		
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			ArrayList<Empleado> p = new ArrayList<Empleado>();
			for ( int i=0 ; i < solutions.length ; i++ ) 
			{ 
				if(CalcularEdad(solutions[i].get("Fecha_nacimiento").toString())==Edad)
				{	
					datos[0] = solutions[i].get("Nombre").toString() + " " + solutions[i].get("Apellido").toString();
					datos[1] = solutions[i].get("CIP").toString();
					modelo_tabla.addRow(datos);
				}
			}	
	
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Filtrar perfiles por centro Regional */
	
	public void FiltrarPerfilesCentroRegional(String Centro, DefaultTableModel modelo_tabla)
	{
		
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
		
		Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");
		Variable Telefono = new Variable("Telefono");
		Variable Cargo = new Variable("Cargo");
		Variable Sexo = new Variable("Sexo");
		Variable Fecha_nacimiento = new Variable("Fecha_nacimiento");
		Variable Formacion_academica = new Variable("Formacion_academica");
		Variable Experiencia = new Variable("Experiencia");
		Variable Referencias = new Variable("Referencias");
		//Variable Centro_Regional = new Variable("Centro_Regional");
		Variable Pruebas_psicotecnicas = new Variable("Pruebas_psicotecnicas");
		Query q2 = 
				  new Query( 
				      "empleado", 
				      new Term[] { CIP, Nombre , Apellido, Telefono , Cargo, Sexo, Fecha_nacimiento, Formacion_academica, Experiencia, Referencias, new Atom(Centro), Pruebas_psicotecnicas } 
				     
				  );
		
		
		JOptionPane.showMessageDialog(null, "Esta a punto de filtrar perfiles por centro regional ");
			
		modelo_tabla.addColumn("Nombre");
		modelo_tabla.addColumn("CIP");
	
		String[] datos = new String[2];
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			//ArrayList<Empleado> p = new ArrayList<Empleado>();
			for ( int i=0 ; i < solutions.length ; i++ ) 
			{ 
				datos[0] = solutions[i].get("Nombre").toString() + " " + solutions[i].get("Apellido").toString();
				datos[1] = solutions[i].get("CIP").toString();
				modelo_tabla.addRow(datos);
					//p.add(i,new Empleado(i,solutions[i].get("CIP").toString(),solutions[i].get("Nombre").toString(),solutions[i].get("Apellido").toString()));
			}
			
		//return p;
		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Filtrar perfiles por area laboral */
	
	public void FiltrarPerfilesAreaLaboral(String grupo_laboral, DefaultTableModel modelo_tabla)
	{		
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		
		Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");

		Query q2 = 
				  new Query( 
				      "buscar_grupo", 
				      new Term[] { new Atom(grupo_laboral),CIP, Nombre , Apellido,} 
				  );
		JOptionPane.showMessageDialog(null, "Esta a punto de filtrar perfiles por su area laboral ");
		
		modelo_tabla.addColumn("Nombre");
		modelo_tabla.addColumn("CIP");
		String[] datos = new String[2];
		
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			for ( int i=0 ; i < solutions.length ; i++ ) 
			{ 
				datos[0] = solutions[i].get("Nombre").toString() + " " + solutions[i].get("Apellido").toString();
				datos[1] = solutions[i].get("CIP").toString();
				modelo_tabla.addRow(datos);					
			}			
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Filtrar perfiles por area laboral */
	
	public void FiltrarTextoBruto(String texto, DefaultTableModel modelo_tabla) 
	{
		Query q1 = 
			    new Query( 
				"consult", 
				new Term[] {new Atom(base_conocimiento)} 
			    );
		
		Variable CIP = new Variable("CIP");
		Variable Nombre = new Variable("Nombre");
		Variable Apellido = new Variable("Apellido");
		Variable Telefono = new Variable("Telefono");
		Variable Cargo = new Variable("Cargo");
		Variable Sexo = new Variable("Sexo");
		Variable Fecha_nacimiento = new Variable("Fecha_nacimiento");
		Variable Formacion_academica = new Variable("Formacion_academica");
		Variable Experiencia = new Variable("Experiencia");
		Variable Referencias = new Variable("Referencias");
		Variable Centro_Regional = new Variable("Centro_Regional");
		Variable Pruebas_psicotecnicas = new Variable("Pruebas_psicotecnicas");
		
		Query q2 = 
				  new Query( 
				      "empleado", 
				      new Term[] { CIP, Nombre, Apellido, Telefono, Cargo, Sexo, Fecha_nacimiento, Formacion_academica, Experiencia, Referencias, Centro_Regional, Pruebas_psicotecnicas} 
				  );
		JOptionPane.showMessageDialog(null, "Esta a punto de filtrar perfiles por un texto introducido ");
		if(q2.hasSolution())
		{
			modelo_tabla.addColumn("Nombre");
			modelo_tabla.addColumn("CIP");
			String[] datos = new String[2];			
			java.util.Map<String,Term>[] solutions = q2.allSolutions();
			for ( int i=0 ; i < solutions.length ; i++ ) 
			{ 
				if( 	solutions[i].get("CIP").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Nombre").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Apellido").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Telefono").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Cargo").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Sexo").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Fecha_nacimiento").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Formacion_academica").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Experiencia").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Referencias").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Centro_Regional").toString().toLowerCase().contains(texto.toLowerCase())||
						solutions[i].get("Pruebas_psicotecnicas").toString().toLowerCase().contains(texto.toLowerCase())	
						)
				{
					datos[0] = solutions[i].get("Nombre").toString() + " " + solutions[i].get("Apellido").toString();
					datos[1] = solutions[i].get("CIP").toString();
					modelo_tabla.addRow(datos);	
				}					
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No se pudo encontrar al encontrar empleado con caracteristicas solicitadas ");
		}
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Cargar las reglas a */
	
	public void CargarReglas()
	{
		escribir("buscar_grupo(Grupo_ocupacional,C,N,A):- cargo(Cargo_actual,_,Grupo_ocupacional,_,_), empleado(C,N,A,_,Cargo_actual,_,_,_,_,_,_,_).");
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodos para insertar hechos */
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
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para escribiren el archivo temporal*/
	
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
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Eliminar el archivo temporal */
	
	public void EliminarArchivo() {
		
		File fichero = new File(base_conocimiento);

		if (fichero.delete())
		   System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
		   System.out.println("El fichero no puede ser borrado");
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Calcular edad */
	
	public int CalcularEdad(String nacimiento)
	{
		int ano =DateFormat.getDateTimeInstance().getCalendar().get(DateFormat.YEAR_FIELD)+80;
		int ano_nacimiento = Integer.parseInt(nacimiento.substring(1, 5));
		int edad = ano-ano_nacimiento;
		System.out.println("Nacido: "+ano_nacimiento);
		System.out.println("Edad: "+edad);
		System.out.println("Año: "+ano);
		
		return edad;
	}



	
	
		
}
