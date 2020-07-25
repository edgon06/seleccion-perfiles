package programa;

public class Empleado 
{
	private String cedula; 
	private String nombre; 
	private String apellido;
	private String telefono;
	private String cargo_actual;
	private String sexo;
	private String f_nacimiento;
	private String formacion_academica;
	private String experiencia;
	private String referencias_laborales;
	private String centro_regional;
	private String pruebas_psicotecnicas;
	
	private int indice_tabla;
	
	
	/* ******************************************************************************************************************************************** */
	/* Constructores */
	
	public Empleado()
	{
		
	}
	
	public Empleado(int indice, String cedula, String nombre, String apellido)
	{
		this.indice_tabla = indice;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Empleado(String cedula, String nombre, String apellido)
	{
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Empleado(String cedula, String nombre, String apellido,String telefono, String cargo_actual,String sexo,String f_nacimiento, String formacion_academica, String experiencia, String referencias_laborales, String centro_regional, String pruebas_psicotecnicas )
	{
		this.cedula = cedula; 
		this.nombre = nombre; 
		this.apellido = apellido;
		this.telefono = telefono;
		this.cargo_actual =cargo_actual;
		this.sexo = sexo;
		this.f_nacimiento = f_nacimiento;
		this.formacion_academica = formacion_academica;
		this.experiencia = experiencia;
		this.referencias_laborales = referencias_laborales;
		this.centro_regional = centro_regional;
		this.pruebas_psicotecnicas = pruebas_psicotecnicas;
	}
	
	/* ******************************************************************************************************************************************** */
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getCargo_actual() {
		return cargo_actual;
	}
	public void setCargo_actual(String cargo_actual) {
		this.cargo_actual = cargo_actual;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getF_nacimiento() {
		return f_nacimiento;
	}
	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getFormacion_academica() {
		return formacion_academica;
	}
	public void setFormacion_academica(String formacion_academica) {
		this.formacion_academica = formacion_academica;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getCentro_regional() {
		return centro_regional;
	}
	public void setCentro_regional(String centro_regional) {
		this.centro_regional = centro_regional;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getReferencias_laborales() {
		return referencias_laborales;
	}
	public void setReferencias_laborales(String referencias_laborales) {
		this.referencias_laborales = referencias_laborales;
	}
	
	/* ******************************************************************************************************************************************** */
	
	public String getPruebas_psicotecnicas() {
		return pruebas_psicotecnicas;
	}
	public void setPruebas_psicotecnicas(String pruebas_psicotecnicas) {
		this.pruebas_psicotecnicas = pruebas_psicotecnicas;
	}

	/* ******************************************************************************************************************************************** */
	
	public int getIndice_tabla() {
		return indice_tabla;
	}

	public void setIndice_tabla(int indice_tabla) {
		this.indice_tabla = indice_tabla;
	} 
}
