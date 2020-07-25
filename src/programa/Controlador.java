package programa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador 
{
	private Motor_Inferencia MI = new Motor_Inferencia();
	
	/* ******************************************************************************************************************************************** */
	/* Metodos para solicitar los perfiles al motor de inferencia y añadirlos al panel de lista de perfiles */
	
	public void ObtenerPerfiles(DefaultTableModel modelo_tabla)
	{
		MI.getPerfiles(modelo_tabla);
	}
	
	public void ObtenerPerfilesEdad(DefaultTableModel modelo_tabla, int Edad )
	{
		MI.FiltrarPerfilesEdad(Edad, modelo_tabla);
	}
	
	public void ObtenerPerfilesCentroRegional(DefaultTableModel modelo_tabla, String Centro_regional )
	{
		MI.FiltrarPerfilesCentroRegional(Centro_regional, modelo_tabla);	
	}
	
	public void ObtenerPerfilesAreaLaboral(DefaultTableModel modelo_tabla, String Area_laboral )
	{
		MI.FiltrarPerfilesAreaLaboral(Area_laboral, modelo_tabla);
	}
	
	public void ObtenerPerfilesCargo(DefaultTableModel modelo_tabla, String Cargo)
	{
		MI.FiltrarPerfilesCargo(Cargo, modelo_tabla);
	}
	
	public void ObtenerPerfilesEspecificos(DefaultTableModel modelo_tabla, String texto) 
	{
		// TODO Auto-generated method stub
		MI.FiltrarTextoBruto(texto, modelo_tabla);
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
	/*                                                      Metodos de Inicializacion                                                               */
	/* ******************************************************************************************************************************************** */
	
	/* Metodo para lenar ComboBoxes*/
	public void LlenarComboBoxes(JComboBox centros, JComboBox cargos, JComboBox grupos_ocupacionales)
	{
	/* Llenar ComboBox de Centros Regionales */
		centros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {null, "Azuero", "Bocas del Toro","Chiriqui","Cocle","Colon","Panama","Panama Oeste","Tocumen","Veraguas" }));
		
		ConectorSQL conector = new ConectorSQL();
		Connection conexion= conector.getConexion("UTP_empleados", "root", "");
		
	/* Llenar ComboBox de Cargos */
		DefaultComboBoxModel<String> modelo_cargos = new javax.swing.DefaultComboBoxModel<String>();
		String cargo;
		try{
			Statement st2 = conexion.createStatement();
			ResultSet CAR = st2.executeQuery("SELECT DISTINCT Nombre FROM Cargos ORDER BY Nombre");
			try {
				modelo_cargos.addElement(null);
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
			modelo_grupos.addElement(null);
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
