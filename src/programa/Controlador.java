package programa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Controlador 
{
	private Motor_Inferencia MI = new Motor_Inferencia();
	private Empleado seleccionado;
	
	/* ******************************************************************************************************************************************** */
	
	/* Debe solicitar los perfiles al motor de inferencia	 */
	public void ObtenerPerfiles()
	{
		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Debe recibir el indice del perfil seleccionado y retornar 
	 * estructura de datos personalizada que represente un perfil 
	 * para ser mostrada en el panel_perfil	*/
	
	public Empleado PerfilSeleccionado(String Cedula)
	{
		return MI.getPerfil(Cedula);
	}

	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	/*
	 * Debe tener un metodo para enviar los parametros de filtrado al motor de inferencia 
	 * que retorne una lista con los perfiles que cumplan dichos parametros */
	
	public void FiltrarPerfiles()
	{
		
	}
	
	public void LlenarComboBoxes(JComboBox centros, JComboBox cargos, JComboBox grupos_ocupacionales)
	{
		centros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Azuero", "Bocas del Toro","Chiriqui","Cocle","Colon","Panama","Panama Oeste","Tocumen","Veraguas" }));
		
		ConectorSQL conector = new ConectorSQL();
		Connection conexion= conector.getConexion("UTP_empleados", "root", "");
		
		/* Llenar ComboBox de Cargos */
		DefaultComboBoxModel<String> modelo_cargos = new javax.swing.DefaultComboBoxModel<String>();
		String cargo;
		try{
			Statement st2 = conexion.createStatement();
			ResultSet CAR = st2.executeQuery("SELECT DISTINCT Nombre FROM Cargos ORDER BY Nombre");
			try {
				while(CAR.next()) 
				{
					 cargo = CAR.getString(1);
					 modelo_cargos.addElement(cargo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargos.setModel(modelo_cargos);
		
		/* Llenar ComboBox de Grupo Ocupacional */
		DefaultComboBoxModel<String> modelo_grupos = new javax.swing.DefaultComboBoxModel<String>();
		String grupo;
		try {
			Statement st = conexion.createStatement();
			ResultSet GO = st.executeQuery("SELECT DISTINCT Grupo_ocupacional FROM Cargos ORDER BY Grupo_ocupacional");
			try {
				while(GO.next()) 
				{
					 grupo = GO.getString(1);
					 modelo_grupos.addElement(grupo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally 
		{
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		grupos_ocupacionales.setModel(modelo_grupos);
	}
	
	/*
	public void LlenarComboBoxes(JComboBox cargos)
	{
		ConectorSQL conector = new ConectorSQL();
		Connection conexion= conector.getConexion("UTP_empleados", "root", "");

		DefaultComboBoxModel<String> modelo_cargos = new javax.swing.DefaultComboBoxModel<String>();
		String cargo;
		try{
			Statement st2 = conexion.createStatement();
			ResultSet CAR = st2.executeQuery("SELECT DISTINCT Nombre FROM Cargos ORDER BY Nombre");
			try {
				while(CAR.next()) 
				{
					 cargo = CAR.getString(1);
					 modelo_cargos.addElement(cargo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally 
		{
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cargos.setModel(modelo_cargos);
	}
	*/
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para iniciar Motor de Inferencia */
	
	public void IniciarMI() 
	{
		MI.Inicializar();
	}
	
	public void EliminarBC() {
		MI.EliminarArchivo();
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para Probar si hay conexion con el Motor de Inferencia */
	
	public void PruebaConexion()
	{
		MI.ConsultaPrueba();
	}

}
