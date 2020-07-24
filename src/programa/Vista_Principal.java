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

	private JPanel contentPane;
	private JPanel panel_lista_perfiles;
	private JPanel panel_filtrado;
	private JPanel panel_contenido;
	private JPanel panel_perfil;
	private JPanel panel_filtrado_norte;
	private JPanel panel_filtrado_sur;
	private JLabel Label_busqueda;
	
	private JButton btn_busqueda;
	private JComboBox comboBox_CargoAnterior;
	private JComboBox comboBox_CargoActual;
	private JComboBox comboBox_GrupoOcupacional;
	
	private JLabel Label_CargoActual;
	private JLabel lblEdad;
	private JLabel Label_Ocupacional; 
	private JLabel lblCentroRegional;

	private JTextField textField_busqueda;
	private JTextField textField_Experiencia;
	
	private JMenuItem filtrar;
	private JLabel lblFormacinAcadmica;
	private JLabel lblAosDeExperiencia;
	private JLabel lblInformacinDeContacto;
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para inicializar barra de menu*/
	private void InicializarBarraMenu(JPanel panel) 
	{
		//Crear Barra de Menu 
		JMenuBar menuBar = new JMenuBar();
		//A�adir Barra de menu en la ventana
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
	private void InicializarPaneles( JPanel panelPrincipal) 
	{
		
		crearVentanaFiltradoAvanzado();	
	//Inicializar panel de lista de perfiles
		panel_lista_perfiles = new JPanel();
		panel_lista_perfiles.setToolTipText("Lista de perfiles");
		panel_lista_perfiles.setPreferredSize(new Dimension(200, 300));// Controlar el grosor del panel
		panel_lista_perfiles.setBorder(new LineBorder(new Color(0, 0, 0)));
	
	//Inicializar panel que tendra el perfil seleccionado y las opciones de filtrado
		panel_contenido = new JPanel();
		panel_contenido.setLayout(new BorderLayout(0,0));
		
	//Inicializar panel de opciones de filtrado
		panel_filtrado = new JPanel();
		panel_filtrado.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_filtrado.setPreferredSize(new Dimension(200, 80));// Controlar el grosor del panel
				
	//Inicializar panel del perfil seleccionado
		panel_perfil = new JPanel();
		panel_perfil.setToolTipText("Lista de Perfiles Filtrados");
		panel_perfil.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
	//Cargar panel con lista de perfiles
		CargarPerfiles(panel_lista_perfiles);
	//Cargar panel con opciones de filtrado
		CargarOpcionesFiltrado(panel_filtrado);
	//Cargar panel con el perfil seleccionado
		MostrarPerfil(panel_perfil);
		
	//Aniadir panel de lista de perfiles al panel principal (contentPane)	
		panelPrincipal.add(panel_lista_perfiles, BorderLayout.WEST);
		
	//Aniadir panel con opciones de filtrado al panel derecho
		panel_contenido.add(panel_filtrado,BorderLayout.NORTH);
		panel_filtrado.setLayout(new BoxLayout(panel_filtrado, BoxLayout.Y_AXIS));
		
	
	//Aniadir panel donde se muestra el perfil seleccionado en el panel	derecho
		panel_contenido.add(panel_perfil,BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Datos Personales del Aspirante");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 323, 25);
		panel_perfil.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Primer Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(20, 47, 109, 14);
		panel_perfil.add(lblNewLabel_1);
		
		JLabel lblSegundoNombre = new JLabel("Segundo Nombre");
		lblSegundoNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSegundoNombre.setBounds(230, 47, 131, 14);
		panel_perfil.add(lblSegundoNombre);
		
		JLabel lblPrimerApellido = new JLabel("Primer Apellido");
		lblPrimerApellido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPrimerApellido.setBounds(20, 92, 109, 14);
		panel_perfil.add(lblPrimerApellido);
		
		JLabel lblSegundoApellido = new JLabel("Segundo Apellido");
		lblSegundoApellido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSegundoApellido.setBounds(230, 92, 131, 14);
		panel_perfil.add(lblSegundoApellido);
		
		JLabel lblCdula = new JLabel("C\u00E9dula");
		lblCdula.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCdula.setBounds(35, 137, 52, 14);
		panel_perfil.add(lblCdula);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTelfono.setBounds(35, 182, 67, 14);
		panel_perfil.add(lblTelfono);
		
		lblFormacinAcadmica = new JLabel("Formaci\u00F3n Acad\u00E9mica");
		lblFormacinAcadmica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFormacinAcadmica.setBounds(21, 275, 155, 14);
		panel_perfil.add(lblFormacinAcadmica);
		
		lblAosDeExperiencia = new JLabel("A\u00F1os de Experiencia");
		lblAosDeExperiencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAosDeExperiencia.setBounds(20, 227, 145, 14);
		panel_perfil.add(lblAosDeExperiencia);
		
		lblInformacinDeContacto = new JLabel("Informaci\u00F3n de Contacto de Referencias Laborales");
		lblInformacinDeContacto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblInformacinDeContacto.setBounds(20, 356, 358, 14);
		panel_perfil.add(lblInformacinDeContacto);
	//Aniadir el panel derecho al panel principal		
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
		
		 comboBox_CargoAnterior = new JComboBox();
		panel_filtrado_sur.add(comboBox_CargoAnterior);
		
		 Label_CargoActual = new JLabel("Cargo Actual");
		panel_filtrado_sur.add(Label_CargoActual);
		
		 comboBox_CargoActual = new JComboBox();
		panel_filtrado_sur.add(comboBox_CargoActual);
		
		 lblEdad = new JLabel("Edad");
		panel_filtrado_sur.add(lblEdad);
		
		textField_Experiencia = new JTextField();
		panel_filtrado_sur.add(textField_Experiencia);
		textField_Experiencia.setColumns(10);
		
		 Label_Ocupacional = new JLabel("Grupo Ocupacional");
		panel_filtrado_sur.add(Label_Ocupacional);
		
		 comboBox_GrupoOcupacional = new JComboBox();
		panel_filtrado_sur.add(comboBox_GrupoOcupacional);
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
	/* Metodo para mostrar el perfil seleccionado en el panel de contenido */
	private void MostrarPerfil(Empleado empleado)
	{
		//Establecer el Layout del panel con la lista de perfiles:
		panel_perfil.setLayout(null);
		
		/*
		 * Funciones de clase controlador para cargar el panel con datos del perfil seleccionado
		 * */
		
		panel_perfil.revalidate();
		panel_perfil.validate();
	}
	
	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	
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
