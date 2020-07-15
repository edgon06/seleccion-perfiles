package programa;

import javax.swing.JOptionPane;

import jess.Deftemplate;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

public class Motor_Inferencia 
{
	private Rete MI; 
	private String reglas;
	private String funciones;
	private String plantillas;
	
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
	
	public static void DefinirReglas(Rete MI, String script)
	{
		// Usar metodo addDefrule para definir reglas
		// MI.addDefrule(arg0);
		
		/* O correr un codigo Jess para definir las reglas*/
		EjecutarScript(MI, script);
		
	}
	
	public static void DefinirFunciones(Rete MI, String script)
	{
		
		/* O correr un codigo Jess para definir las reglas*/
		EjecutarScript(MI, script);
				
	}
	
	public static void DefinirPlantillas(Rete MI, String script) 
	{
		// Usar metodo addDeftemplates para definir plantillas
		
		/*
		try {
			// Parametros del objeto Deftemplate("Nombre de la plantilla", "Texto de documentacion de la plantilla", objeto tipo Rete donde se creara la plantilla)
			Deftemplate dt;
			dt = new Deftemplate("point", "A 2D point", MI);
			Value zero = new Value(0, RU.INTEGER);
			// Parametros del metodo addSlot("nombre del slot", valor del slot, "tipo de dato del slot (INTEGER, FLOAT, ANY, etc)")
			 dt.addSlot("x", zero, "NUMBER");
			 dt.addSlot("y", zero, "NUMBER");
			 MI.addDeftemplate(dt);
		} catch (JessException e) {
			JOptionPane.showMessageDialog(null, "Error al crear template");
			e.printStackTrace();
		}
		*/
		
		/* O  correr codigo Jess para definir las plantillas*/
		EjecutarScript(MI, script);		
	}
	
	public void Inicializar()
	{
		MI = new Rete();
		DefinirReglas(MI, reglas);
		DefinirFunciones(MI, funciones);
		DefinirPlantillas(MI, plantillas);
		
	}

}
