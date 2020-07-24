package programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.DefaultListModel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
public class Vista_Principal extends JFrame implements ActionListener {
	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	private Vista_Filtrado ventanaFiltrado; 
	private Controlador c = new Controlador();

	private JMenuItem filtrar;
	
	/* Paneles */
	private JPanel contentPane;
	private JPanel panel_lista_perfiles;
	private JScrollPane scrollpanel_izquierdo;
	private JPanel panel_filtrado;
	private JPanel panel_contenido;
	private JPanel panel_perfil;
	private JPanel panel_filtrado_norte;
	private JPanel panel_filtrado_sur;
	
	
	/* Controles para Panel de Filtrado */
	private JButton btn_busqueda;
	private JComboBox comboBox_Centro;
	private JComboBox comboBox_CargoActual;
	private JComboBox comboBox_GrupoOcupacional;
	
	private JLabel Label_busqueda;
	private JLabel Label_CargoActual;
	private JLabel lblEdad;
	private JLabel Label_Ocupacional; 
	private JLabel lblCentroRegional;
	private JTextField textField_busqueda;
	private JTextField textField_Edad;
	
	/* Elementos de Panel  de Perfil */

	private JLabel lblFormacionAcademica;
	private JLabel lblExperiencia;
	private JLabel lblReferencias;
	
	
	private JLabel lblTitulo;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCedula;
	private JLabel lblTelefono;
	private JLabel lblCargo;
	
	private JTextField textField_nombre;
	private JTextField textField_apellido;
	private JTextField textField_cedula;
	private JTextField textField_telefono;
	private JTextField textField_experiencia;
	private JTextField textField_formacion;
	private JTextField textField_referencias;
	private JTextField textField_cargo;
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para inicializar barra de menu*/
	private void InicializarBarraMenu(JPanel panel) 
	{
		//Crear Barra de Menu 
		JMenuBar menuBar = new JMenuBar();
		//Añadir Barra de menu en la ventana
		setJMenuBar(menuBar);
			
		//Crear boton de 'Filtrado Avanzado'
		 filtrar = new JMenuItem("Filtrado Avanzado");
		filtrar.setHorizontalAlignment(SwingConstants.LEFT);
		filtrar.addActionListener(this);
		menuBar.add(filtrar);
		
		
		//Crear boton de 'Manuales de Cargo'
		JMenuItem manuales = new JMenuItem("Manuales de Cargo");
		manuales.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(manuales);
		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para incializar los parametros de los paneles en la ventana principal */
	
	private void InicializarPaneles(JPanel panelPrincipal) 
	{
		
		crearVentanaFiltradoAvanzado();	
	/* Inicializar panel de lista de perfiles */
		panel_lista_perfiles = new JPanel();
		panel_lista_perfiles.setToolTipText("Lista de perfiles");
		panel_lista_perfiles.setPreferredSize(new Dimension(200, 300));// Controlar el grosor del panel
		panel_lista_perfiles.setBorder(new LineBorder(new Color(0, 0, 0)));
	
	/*Inicializar panel que tendra el perfil seleccionado y las opciones de filtrado  */
		panel_contenido = new JPanel();
		panel_contenido.setLayout(new BorderLayout(0,0));
		
	/*	Inicializar panel de opciones de filtrado  */
		panel_filtrado = new JPanel();
		panel_filtrado.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_filtrado.setPreferredSize(new Dimension(200, 130));// Controlar el grosor del panel Dimension(anchura, altura)
				
	/*	Inicializar panel del perfil seleccionado  */
		panel_perfil = new JPanel();
		panel_perfil.setToolTipText("Lista de Perfiles Filtrados");
		panel_perfil.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
	/*Cargar panel con lista de perfiles */
		CargarPerfiles(panel_lista_perfiles);
	/*Cargar panel con opciones de filtrado */
		CargarOpcionesFiltrado(panel_filtrado);
	
		
		
	/*Añadir panel de lista de perfiles al panel principal (contentPane)	 */
		scrollpanel_izquierdo = new JScrollPane(panel_lista_perfiles);
		scrollpanel_izquierdo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel_izquierdo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelPrincipal.add(scrollpanel_izquierdo, BorderLayout.WEST);
		
	/*Añadir panel con opciones de filtrado al panel derecho */
		panel_contenido.add(panel_filtrado,BorderLayout.NORTH);
		panel_filtrado.setLayout(new BoxLayout(panel_filtrado, BoxLayout.Y_AXIS));
		
	
	/*Añadir panel donde se muestra el perfil seleccionado en el panel	derecho */
		panel_contenido.add(panel_perfil,BorderLayout.CENTER);
		
		CargarPanelPerfil();
		
	/* Añadir el panel derecho al panel principal  */		
		panelPrincipal.add(panel_contenido,BorderLayout.CENTER);
		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para cargar el panel con las opciones de filtrado */
	private void CargarOpcionesFiltrado(JPanel panel)
	{
		panel_filtrado_norte = new JPanel();
		panel_filtrado.add(panel_filtrado_norte);
		
		 Label_busqueda = new JLabel("B\u00FAsqueda (Filtrado de Informaci\u00F3n)");
		panel_filtrado_norte.add(Label_busqueda);
		
		textField_busqueda = new JTextField();
		panel_filtrado_norte.add(textField_busqueda);
		textField_busqueda.setColumns(35);
		
		 btn_busqueda = new JButton("Buscar");
		 btn_busqueda.addActionListener(this);
		panel_filtrado_norte.add(btn_busqueda);
		
		 panel_filtrado_sur = new JPanel();
		panel_filtrado.add(panel_filtrado_sur);
		panel_filtrado_sur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		 lblCentroRegional = new JLabel("Centro Regional");
		panel_filtrado_sur.add(lblCentroRegional);
		// *********************************************************************************************************************************************************************************
		 comboBox_Centro = new JComboBox();
		 
		panel_filtrado_sur.add(comboBox_Centro);
		
		 Label_CargoActual = new JLabel("Cargo Actual");
		panel_filtrado_sur.add(Label_CargoActual);
		
		 comboBox_CargoActual = new JComboBox();
		panel_filtrado_sur.add(comboBox_CargoActual);
		
		 lblEdad = new JLabel("Edad");
		panel_filtrado_sur.add(lblEdad);
		
		textField_Edad = new JTextField();
		panel_filtrado_sur.add(textField_Edad);
		textField_Edad.setColumns(4);
		
		 Label_Ocupacional = new JLabel("Grupo Ocupacional");
		panel_filtrado_sur.add(Label_Ocupacional);
		
		 comboBox_GrupoOcupacional = new JComboBox();
		panel_filtrado_sur.add(comboBox_GrupoOcupacional);
		c.LlenarComboBoxes(comboBox_Centro, comboBox_CargoActual, comboBox_GrupoOcupacional);
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para llenar el panel de perfiles con la lista de perfiles */
	private void CargarPerfiles(JPanel panel) 
	{
		//Establecer el Layout del panel con la lista de perfiles:
		panel_lista_perfiles.setLayout(new BoxLayout(panel_lista_perfiles, BoxLayout.Y_AXIS));
		
		//Se crea el objeto JLabel
		  JLabel ejemplo = new JLabel();
		  //Le asignamos un texto
		  ejemplo.setText("HOLA MUNDO!!");
		//Se crea el objeto JLabel
		  JLabel ejemplo2 = new JLabel();
		  
		panel.add(ejemplo);
		panel.add(ejemplo2);
		
	}
	/* ******************************************************************************************************************************************** */
	/* Metodo para mostrar el panel de contenido del Perfil */
	private void CargarPanelPerfil()
	{
		/* Panel de Perfil seleccionado */
		panel_perfil.setLayout(null);
		lblTitulo = new JLabel("Datos Personales");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(15, 6, 172, 25);
		panel_perfil.add(lblTitulo);
		
		lblNombre = new JLabel("Primer Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNombre.setBounds(31, 121, 118, 17);
		panel_perfil.add(lblNombre);
		
		lblApellido = new JLabel("Primer Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblApellido.setBounds(31, 159, 118, 17);
		panel_perfil.add(lblApellido);
		
		lblCedula = new JLabel("C\u00E9dula");
		lblCedula.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCedula.setBounds(31, 76, 78, 17);
		panel_perfil.add(lblCedula);
		
		lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTelefono.setBounds(31, 200, 78, 17);
		panel_perfil.add(lblTelefono);
		
		lblFormacionAcademica = new JLabel("Formaci\u00F3n Acad\u00E9mica");
		lblFormacionAcademica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFormacionAcademica.setBounds(31, 283, 156, 17);
		panel_perfil.add(lblFormacionAcademica);
		
		lblExperiencia = new JLabel("Experiencia");
		lblExperiencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblExperiencia.setBounds(31, 320, 92, 17);
		panel_perfil.add(lblExperiencia);
		
		lblReferencias = new JLabel("Informaci\u00F3n de Contacto de Referencias Laborales");
		lblReferencias.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblReferencias.setBounds(31, 371, 359, 17);
		panel_perfil.add(lblReferencias);
		
		textField_nombre = new JTextField();
		textField_nombre.setEditable(false);
		textField_nombre.setBounds(475, 121, 232, 20);
		panel_perfil.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_apellido = new JTextField();
		textField_apellido.setEditable(false);
		textField_apellido.setColumns(10);
		textField_apellido.setBounds(475, 159, 232, 20);
		panel_perfil.add(textField_apellido);
		
		textField_cedula = new JTextField();
		textField_cedula.setEditable(false);
		textField_cedula.setColumns(10);
		textField_cedula.setBounds(475, 76, 232, 20);
		panel_perfil.add(textField_cedula);
		
		textField_telefono = new JTextField();
		textField_telefono.setEditable(false);
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(475, 200, 232, 20);
		panel_perfil.add(textField_telefono);
		
		textField_experiencia = new JTextField();
		textField_experiencia.setEditable(false);
		textField_experiencia.setColumns(10);
		textField_experiencia.setBounds(475, 320, 232, 20);
		panel_perfil.add(textField_experiencia);
		
		textField_formacion = new JTextField();
		textField_formacion.setEditable(false);
		textField_formacion.setColumns(10);
		textField_formacion.setBounds(475, 283, 232, 20);
		panel_perfil.add(textField_formacion);
		
		textField_referencias = new JTextField();
		textField_referencias.setEditable(false);
		textField_referencias.setColumns(10);
		textField_referencias.setBounds(475, 371, 232, 20);
		panel_perfil.add(textField_referencias);
		
		lblCargo = new JLabel("Cargo Actual");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCargo.setBounds(31, 239, 156, 17);
		panel_perfil.add(lblCargo);
		
		textField_cargo = new JTextField();
		textField_cargo.setEditable(false);
		textField_cargo.setColumns(10);
		textField_cargo.setBounds(475, 239, 232, 20);
		panel_perfil.add(textField_cargo);
		
		panel_perfil.revalidate();
		panel_perfil.validate();
	}
	
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para mostrar el perfil seleccionado en el panel de contenido */
	private void MostrarPerfil(Empleado empleado)
	{
		//Establecer el Layout del panel con la lista de perfiles:
		panel_perfil.setLayout(null);
		
		
		textField_nombre.setText(empleado.getNombre());
		textField_apellido.setText(empleado.getApellido());
		textField_cedula.setText(empleado.getCedula());
		textField_telefono.setText(empleado.getTelefono());
		textField_cargo.setText(empleado.getCargo_actual());
		textField_formacion.setText(empleado.getFormacion_academica());
		textField_experiencia.setText(empleado.getExperiencia());
		textField_referencias.setText(empleado.getReferencias_laborales());
		
		panel_perfil.revalidate();
		panel_perfil.validate();
	}
	
	/* ******************************************************************************************************************************************** */
	
	/**
	 * Construir la ventana.
	 */
	
	public Vista_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
               c.EliminarBC();
            }
        });
		
		setBounds(100, 100, 802, 431);
		setMinimumSize(new Dimension (1000,600));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		InicializarBarraMenu(contentPane);
		InicializarPaneles(contentPane);
		
		c.IniciarMI();
		
		
	}
	
	/* ******************************************************************************************************************************************** */
	/* Eventos de los botones */
 
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==filtrar)
		{
			abrirFiltradoAvanzado();
		}
		if(e.getSource()==btn_busqueda)
		{
			c.PruebaConexion();
			MostrarPerfil(c.PerfilSeleccionado("8-333-3333"));
		}
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para crear  la ventana de filtrado avanzado */
	public void crearVentanaFiltradoAvanzado()
	{
		try {
			 ventanaFiltrado = new Vista_Filtrado();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* ******************************************************************************************************************************************** */
	/*  Metodo para mostrar la ventana de filtrado avanzado */
	public void abrirFiltradoAvanzado()
	{
		ventanaFiltrado.setVisible(true);
	}
}
