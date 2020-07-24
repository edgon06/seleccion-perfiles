package programa;

public class Controlador 
{
	private Motor_Inferencia MI = new Motor_Inferencia();
	
	/* ******************************************************************************************************************************************** */
	
	/* Debe solicitar los perfiles al motor de inferencia	 */
	public void ObtenerPerfiles()
	{
		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Debe recibir el indice del perfil seleccionado y retornar 
	 * estructura de datos personalizada que represente un perfil 
	 * para ser mostrada en el panel_perfil	*/
	public void PerfilSeleccionado(int indice_perfil)
	{
		
		
		//return perfil;
	}

	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	/*
	 * Debe tener un metodo para enviar los parametros de filtrado al motor de inferencia 
	 * que retorne una lista con los perfiles que cumplan dichos parametros */
	public void FiltrarPerfiles()
	{
		
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
