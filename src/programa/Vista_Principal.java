package programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JCheckBox;

public class Vista_Principal extends JFrame implements ActionListener {
	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	private Vista_Filtrado ventanaFiltrado; 
	private Vista_Manuales ventanaManuales;
	private Controlador c = new Controlador();

	/* Controles para la barra de menu */
	private JMenuBar menuBar;
	private JMenuItem filtrar;
	private JMenuItem manuales;
	
	/* Paneles */
	private JPanel contentPane;
	private JPanel panel_lista_perfiles;
	private JScrollPane scrollpanel_izquierdo;
	private JPanel panel_filtrado;
	private JPanel panel_contenido;

	private JPanel panel_filtrado_norte;
	private JPanel panel_filtrado_sur;
	private JPanel panel_perfil;
	private JPanel panel_filtrado_sur_arriba;
	private JPanel panel_filtrado_sur_abajo;
	
	/* Controles para listas de perfiles */
	private JTable lista_perfiles;
	
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
	
	private JCheckBox chckbx_grupo;
	private JCheckBox chckbx_Cargo;
	private JCheckBox chckbx_Centro;
	private JCheckBox checkBox_Edad ;
	private JCheckBox chckbx_busquedaFiltrado;
	
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
	//	Crear Barra de Menu 
		menuBar = new JMenuBar();
	//	Añadir Barra de menu en la ventana
		setJMenuBar(menuBar);
			
	//	Crear boton de 'Filtrado Avanzado'
		filtrar = new JMenuItem("Filtrado Avanzado");
		filtrar.setHorizontalAlignment(SwingConstants.LEFT);
		filtrar.addActionListener(this);
		menuBar.add(filtrar);
		
	//	Crear boton de 'Manuales de Cargo'
		manuales = new JMenuItem("Manuales de Cargo");
		manuales.setHorizontalAlignment(SwingConstants.LEFT);
		manuales.addActionListener(this);
		menuBar.add(manuales);
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para incializar los parametros de los paneles en la ventana principal */
	
	private void InicializarPaneles(JPanel panelPrincipal) 
	{
		crearVentanaFiltradoAvanzado();	
		crearVentanaManuales();
	/*	Inicializar panel de lista de perfiles */
		panel_lista_perfiles = new JPanel();
		panel_lista_perfiles.setToolTipText("Lista de perfiles");
		panel_lista_perfiles.setPreferredSize(new Dimension(200, 300));// Controlar el grosor del panel
		panel_lista_perfiles.setBorder(new LineBorder(new Color(0, 0, 0)));
	
		/*	Añadir panel de lista de perfiles al panel principal (contentPane)	 */
		scrollpanel_izquierdo = new JScrollPane(panel_lista_perfiles);
		scrollpanel_izquierdo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel_izquierdo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelPrincipal.add(scrollpanel_izquierdo, BorderLayout.WEST);
		
	/*	Inicializar panel que tendra el perfil seleccionado y las opciones de filtrado  */
		panel_contenido = new JPanel();
		panel_contenido.setLayout(new BorderLayout(0,0));
		
	/*	Inicializar panel de opciones de filtrado  */
		panel_filtrado = new JPanel();
		panel_filtrado.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_filtrado.setPreferredSize(new Dimension(200, 130));// Controlar el grosor del panel Dimension(anchura, altura)
		
	/*	Cargar panel con opciones de filtrado */
		CargarOpcionesFiltrado(panel_filtrado);
		
	/*	Inicializar panel del perfil seleccionado  */
		panel_perfil = new JPanel();
		panel_perfil.setToolTipText("Lista de Perfiles Filtrados");
		panel_perfil.setBorder(new LineBorder(new Color(0, 0, 0)));
		
	/*	Añadir panel con opciones de filtrado al panel derecho */
		panel_contenido.add(panel_filtrado,BorderLayout.NORTH);
		panel_filtrado.setLayout(new BoxLayout(panel_filtrado, BoxLayout.Y_AXIS));
		
	/*	Añadir panel donde se muestra el perfil seleccionado en el panel derecho */
		panel_contenido.add(panel_perfil,BorderLayout.CENTER);
		CargarPanelPerfil();
		
	/*	Añadir el panel derecho al panel principal  */		
		panelPrincipal.add(panel_contenido,BorderLayout.CENTER);
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para cargar el panel con las opciones de filtrado */
	
	private void CargarOpcionesFiltrado(JPanel panel)
	{
		panel_filtrado_norte = new JPanel();
		panel_filtrado_norte.setPreferredSize(new Dimension(200, 32));
		panel_filtrado.add(panel_filtrado_norte);
		
		
		Label_busqueda = new JLabel("B\u00FAsqueda (Filtrado de Informaci\u00F3n)");
		panel_filtrado_norte.add(Label_busqueda);
		
		 chckbx_busquedaFiltrado = new JCheckBox("",false);
		 
		panel_filtrado_norte.add(chckbx_busquedaFiltrado);
		chckbx_busquedaFiltrado.addActionListener(this);
		
		textField_busqueda = new JTextField();
		panel_filtrado_norte.add(textField_busqueda);
		textField_busqueda.setColumns(35);
		textField_busqueda.setEditable(false);
		
		btn_busqueda = new JButton("Buscar");
		btn_busqueda.addActionListener(this);
		panel_filtrado_norte.add(btn_busqueda);
		
		panel_filtrado_sur = new JPanel();
		panel_filtrado.add(panel_filtrado_sur);
		panel_filtrado_sur.setLayout(new BoxLayout(panel_filtrado_sur, BoxLayout.Y_AXIS));
		

		
		panel_filtrado_sur_arriba= new JPanel();
		panel_filtrado_sur_abajo= new JPanel();
	
		panel_filtrado_sur_arriba.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_filtrado_sur_abajo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_filtrado_sur.add(panel_filtrado_sur_arriba);
		panel_filtrado_sur.add(panel_filtrado_sur_abajo);
		 
		// *********************************************************************************************************************************************************************************
		lblCentroRegional = new JLabel("Centro Regional");
		panel_filtrado_sur_arriba.add(lblCentroRegional);
		 
		chckbx_Centro = new JCheckBox("",false);
		panel_filtrado_sur_arriba.add(chckbx_Centro);
		chckbx_Centro.addActionListener(this); 
		
		comboBox_Centro = new JComboBox();
		comboBox_Centro.setEnabled(false);
		comboBox_Centro.addActionListener(this);
		 
		panel_filtrado_sur_arriba.add(comboBox_Centro);
		
		Label_CargoActual = new JLabel("Cargo Actual");
		panel_filtrado_sur_arriba.add(Label_CargoActual);
		 
		chckbx_Cargo = new JCheckBox("",false);
		panel_filtrado_sur_arriba.add(chckbx_Cargo);
		chckbx_Cargo.addActionListener(this);
		
		comboBox_CargoActual = new JComboBox();
		comboBox_CargoActual.setEnabled(false);
		panel_filtrado_sur_arriba.add(comboBox_CargoActual);
		
		lblEdad = new JLabel("Edad");
		panel_filtrado_sur_abajo.add(lblEdad);
		
		checkBox_Edad = new JCheckBox("",false);
		panel_filtrado_sur_abajo.add(checkBox_Edad);
		checkBox_Edad.addActionListener(this);
		textField_Edad = new JTextField();
		textField_Edad.setEditable(false);
		panel_filtrado_sur_abajo.add(textField_Edad);
		textField_Edad.setColumns(4);
		
		Label_Ocupacional = new JLabel("Grupo Ocupacional");
		panel_filtrado_sur_abajo.add(Label_Ocupacional);
		 
		chckbx_grupo = new JCheckBox("",false);
		panel_filtrado_sur_abajo.add(chckbx_grupo);
		chckbx_grupo.addActionListener(this);
		comboBox_GrupoOcupacional = new JComboBox();
		comboBox_GrupoOcupacional.setEnabled(false);
		panel_filtrado_sur_abajo.add(comboBox_GrupoOcupacional);
		
		//	Llenar combo boxes con valores en la base de datos
		c.LlenarComboBoxes(comboBox_Centro, comboBox_CargoActual, comboBox_GrupoOcupacional);
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para llenar el panel de perfiles con la lista de perfiles */
	
	private void CargarPerfiles(DefaultTableModel modelo_tabla) 
	{
	/*	Limpiar el panel */
		panel_lista_perfiles.removeAll();
	/*	Establecer el Layout del panel con la lista de perfiles: */
		panel_lista_perfiles.setLayout(new BoxLayout(panel_lista_perfiles, BoxLayout.Y_AXIS));
	/*	Crear tabla */
		lista_perfiles = new JTable();
	
	/*	Establecer modelo recibido en la tabla */	
		lista_perfiles.setModel(modelo_tabla);

		panel_lista_perfiles.add(lista_perfiles);
		lista_perfiles.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent event) 
			{
				try {
					MostrarPerfil(c.PerfilSeleccionado(lista_perfiles.getValueAt(lista_perfiles.getSelectedRow(),1).toString().replaceAll("'", "")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Algo anda mal... "+ lista_perfiles.getValueAt(lista_perfiles.getSelectedRow(),1).toString().replaceAll("'", ""));
				}	
			}
		});
		
		/* Actualizar el panel */
		panel_lista_perfiles.repaint();
		panel_lista_perfiles.revalidate();
		panel_lista_perfiles.validate();
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
		textField_nombre.setBounds(475, 121, 287, 20);
		panel_perfil.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_apellido = new JTextField();
		textField_apellido.setEditable(false);
		textField_apellido.setColumns(10);
		textField_apellido.setBounds(475, 159, 287, 20);
		panel_perfil.add(textField_apellido);
		
		textField_cedula = new JTextField();
		textField_cedula.setEditable(false);
		textField_cedula.setColumns(10);
		textField_cedula.setBounds(475, 76, 287, 20);
		panel_perfil.add(textField_cedula);
		
		textField_telefono = new JTextField();
		textField_telefono.setEditable(false);
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(475, 200, 287, 20);
		panel_perfil.add(textField_telefono);
		
		textField_experiencia = new JTextField();
		textField_experiencia.setEditable(false);
		textField_experiencia.setColumns(10);
		textField_experiencia.setBounds(475, 320, 287, 20);
		panel_perfil.add(textField_experiencia);
		
		textField_formacion = new JTextField();
		textField_formacion.setEditable(false);
		textField_formacion.setColumns(10);
		textField_formacion.setBounds(475, 283, 287, 20);
		panel_perfil.add(textField_formacion);
		
		textField_referencias = new JTextField();
		textField_referencias.setEditable(false);
		textField_referencias.setColumns(10);
		textField_referencias.setBounds(475, 371, 287, 20);
		panel_perfil.add(textField_referencias);
		
		lblCargo = new JLabel("Cargo Actual");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCargo.setBounds(31, 239, 156, 17);
		panel_perfil.add(lblCargo);
		
		textField_cargo = new JTextField();
		textField_cargo.setEditable(false);
		textField_cargo.setColumns(10);
		textField_cargo.setBounds(475, 239, 287, 20);
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
		
		
		textField_nombre.setText(empleado.getNombre().replaceAll("'", ""));
		textField_apellido.setText(empleado.getApellido().replaceAll("'", ""));
		textField_cedula.setText(empleado.getCedula().replaceAll("'", ""));
		textField_telefono.setText(empleado.getTelefono().replaceAll("'", ""));
		textField_cargo.setText(empleado.getCargo_actual().replaceAll("'", ""));
		textField_formacion.setText(empleado.getFormacion_academica().replaceAll("'", ""));
		textField_experiencia.setText(empleado.getExperiencia().replaceAll("'", ""));
		textField_referencias.setText(empleado.getReferencias_laborales().replaceAll("'", ""));
		
		panel_perfil.revalidate();
		panel_perfil.validate();
	}
	
	/* ******************************************************************************************************************************************** */
	/* Metodo para crear  la ventana de manuales de cargo */
	public void crearVentanaManuales()
	{
		try {
			ventanaManuales = new Vista_Manuales();
			} catch (Exception ex) 
			{
				ex.printStackTrace();
			}
	}
	/* ******************************************************************************************************************************************** */
	/* Metodo para crear  la ventana de filtrado avanzado */
	
	public void crearVentanaFiltradoAvanzado()
	{
		try {
			 	ventanaFiltrado = new Vista_Filtrado();
			} catch (Exception ex) 
			{
				ex.printStackTrace();
			}
	}
	
	/* ******************************************************************************************************************************************** */
	/*  Metodo para mostrar la ventana de manuales de cargo */
	
	public void abrirManuales()
	{
		ventanaManuales.setVisible(true);
	}	

	
	/* ******************************************************************************************************************************************** */
	/*  Metodo para mostrar la ventana de filtrado avanzado */
	
	public void abrirFiltradoAvanzado()
	{
		ventanaFiltrado.setVisible(true);
	}
	
	/* ******************************************************************************************************************************************** */
	/*  Metodo para poner despejar los comboboxes del apartado de filtrado */
	public void limpiarcomponentes() 
	{
		comboBox_Centro.setSelectedIndex(-1);
		comboBox_CargoActual.setSelectedIndex(-1);
		comboBox_GrupoOcupacional.setSelectedIndex(-1);
		
		comboBox_Centro.setEnabled(false);
		comboBox_CargoActual.setEnabled(false);
		comboBox_GrupoOcupacional.setEnabled(false);
		
		textField_busqueda.setEditable(false);
		textField_Edad.setEditable(false);
		
		textField_busqueda.setText("");
		textField_Edad.setText("");

	}


	/* ******************************************************************************************************************************************** */
	/*                                                      Constructor de la ventana                                                               */
	/* ******************************************************************************************************************************************** */
	/**
	 * Construir la ventana.
	 */
	
	public Vista_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) 
            {
            //Acciones al cerrar la ventana	
               c.EliminarBC();
            }
            
            @Override
            public void windowOpened(java.awt.event.WindowEvent e)
            {
            //Acciones al cargar la ventana
            	c.IniciarMI();
            	c.PruebaConexion();
    			//MostrarPerfil(c.PerfilSeleccionado("8-333-3333"));
    			DefaultTableModel modelo_tabla = new DefaultTableModel();
    			c.ObtenerPerfiles(modelo_tabla);
    			CargarPerfiles(modelo_tabla);
    			
            }
        });
		
		setBounds(100, 100, 802, 431);
		setMinimumSize(new Dimension (1040,600));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		InicializarBarraMenu(contentPane);
		InicializarPaneles(contentPane);	
	}
	
	
	
	/* ******************************************************************************************************************************************** */
	/*                                                       Eventos de los botones                                                                 */
	/* ******************************************************************************************************************************************** */
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		/* ********************************************************************************************************************************************************************************** */
		/* controlar checkbox simultaneos */
        if(chckbx_Centro.isSelected()){
        	
            chckbx_Cargo.setEnabled(false);
            chckbx_grupo.setEnabled(false);
            checkBox_Edad.setEnabled(false);
            chckbx_busquedaFiltrado.setEnabled(false);
            
            comboBox_Centro.setEnabled(true);
        	comboBox_CargoActual.setEnabled(false);
        	comboBox_GrupoOcupacional.setEnabled(false);
        	textField_busqueda.setEditable(false);
        	textField_Edad.setEditable(false);
        }
        else if(chckbx_Cargo.isSelected()){
 
            chckbx_Centro.setEnabled(false);
            chckbx_grupo.setEnabled(false);
            checkBox_Edad.setEnabled(false);
            chckbx_busquedaFiltrado.setEnabled(false);
            
            comboBox_Centro.setEnabled(false);
        	comboBox_CargoActual.setEnabled(true);
        	comboBox_GrupoOcupacional.setEnabled(false);
        	textField_busqueda.setEditable(false);
        	textField_Edad.setEditable(false);
        }
        else if(checkBox_Edad.isSelected()){
            
            chckbx_Centro.setEnabled(false);
            chckbx_Cargo.setEnabled(false);
            chckbx_grupo.setEnabled(false);
            chckbx_busquedaFiltrado.setEnabled(false);
            
            comboBox_Centro.setEnabled(false);
        	comboBox_CargoActual.setEnabled(false);
        	comboBox_GrupoOcupacional.setEnabled(false);
        	textField_busqueda.setEditable(false);
        	textField_Edad.setEditable(true);
        }
        else if(chckbx_grupo.isSelected()){
            
            chckbx_Centro.setEnabled(false);
            chckbx_Cargo.setEnabled(false);
            checkBox_Edad.setEnabled(false);
            chckbx_busquedaFiltrado.setEnabled(false);
            
            comboBox_Centro.setEnabled(false);
        	comboBox_CargoActual.setEnabled(false);
        	comboBox_GrupoOcupacional.setEnabled(true);
        	textField_busqueda.setEditable(false);
        	textField_Edad.setEditable(false);
        }
        else if(chckbx_busquedaFiltrado.isSelected()){
            
            chckbx_Centro.setEnabled(false);
            chckbx_Cargo.setEnabled(false);
            checkBox_Edad.setEnabled(false);
            chckbx_grupo.setEnabled(false);
            
            
            comboBox_Centro.setEnabled(false);
        	comboBox_CargoActual.setEnabled(false);
        	comboBox_GrupoOcupacional.setEnabled(false);
        	textField_busqueda.setEditable(true);
        	textField_Edad.setEditable(false);
        }
        else {
     
        	
            chckbx_Centro.setEnabled(true);
            chckbx_Cargo.setEnabled(true);
            chckbx_grupo.setEnabled(true);
            checkBox_Edad.setEnabled(true); 
            chckbx_busquedaFiltrado.setEnabled(true);
  
            limpiarcomponentes();
    
         }
		
		if(e.getSource()==filtrar)
		{
			abrirFiltradoAvanzado();
		}
		if(e.getSource()==manuales)
		{
			abrirManuales();
		}
		
		/* ********************************************************************************************************************************************************************************** */
		/* Filtrar por centro regional */
		if(e.getSource()==btn_busqueda && chckbx_Centro.isSelected() &&!comboBox_Centro.getSelectedItem().toString().equals(null))
		{
			
			System.out.println(comboBox_Centro.getSelectedItem().toString());
			chckbx_Centro.setSelected(false);
			chckbx_Cargo.setSelected(false);
			chckbx_grupo.setSelected(false);
			checkBox_Edad.setSelected(false);
			chckbx_busquedaFiltrado.setSelected(false);
			
            chckbx_Centro.setEnabled(true);
            chckbx_Cargo.setEnabled(true);
            chckbx_grupo.setEnabled(true);
            checkBox_Edad.setEnabled(true);
            chckbx_busquedaFiltrado.setEnabled(true);
            
			DefaultTableModel modelo_tabla = new DefaultTableModel();
			c.ObtenerPerfilesCentroRegional(modelo_tabla, comboBox_Centro.getSelectedItem().toString());
			CargarPerfiles(modelo_tabla);
			limpiarcomponentes();
		}
		
		/* ********************************************************************************************************************************************************************************** */
		/* Filtrar por area laboral */
		if(e.getSource()==btn_busqueda && chckbx_grupo.isSelected() &&!comboBox_GrupoOcupacional.getSelectedItem().toString().equals(null))
		{
			System.out.println(comboBox_GrupoOcupacional.getSelectedItem().toString());
			chckbx_Centro.setSelected(false);
			chckbx_Cargo.setSelected(false);
			chckbx_grupo.setSelected(false);
			checkBox_Edad.setSelected(false);
			chckbx_busquedaFiltrado.setSelected(false);
			
            chckbx_Centro.setEnabled(true);
            chckbx_Cargo.setEnabled(true);
            chckbx_grupo.setEnabled(true);
            checkBox_Edad.setEnabled(true);
            chckbx_busquedaFiltrado.setEnabled(true);
            
			DefaultTableModel modelo_tabla = new DefaultTableModel();
			c.ObtenerPerfilesAreaLaboral(modelo_tabla, comboBox_GrupoOcupacional.getSelectedItem().toString());
			System.out.println(comboBox_GrupoOcupacional.getSelectedItem().toString());
			CargarPerfiles(modelo_tabla);
			limpiarcomponentes();
		}
		
		/* ********************************************************************************************************************************************************************************** */
		/* Filtrar por edad */
			if((e.getSource()==btn_busqueda && !textField_Edad.getText().equals("")) && checkBox_Edad.isSelected() )// radiobutton_edad.esta seleccionados
			{
				chckbx_Centro.setSelected(false);
				chckbx_Cargo.setSelected(false);
				chckbx_grupo.setSelected(false);
				checkBox_Edad.setSelected(false);
				chckbx_busquedaFiltrado.setSelected(false);
				
			
				
	            chckbx_Centro.setEnabled(true);
	            chckbx_Cargo.setEnabled(true);
	            chckbx_grupo.setEnabled(true);
	            checkBox_Edad.setEnabled(true);
	            chckbx_busquedaFiltrado.setEnabled(true);

	            
				DefaultTableModel modelo_tabla = new DefaultTableModel();
	    		c.ObtenerPerfilesEdad(modelo_tabla, Integer.parseInt(textField_Edad.getText().toString()));
	    		CargarPerfiles(modelo_tabla);
	    		limpiarcomponentes();	
			}
			
			/* ********************************************************************************************************************************************************************************** */
			/* Filtrar por cargo */
			if(e.getSource()==btn_busqueda && chckbx_Cargo.isSelected() &&!comboBox_CargoActual.getSelectedItem().toString().equals(null))
			{
				System.out.println(comboBox_CargoActual.getSelectedItem().toString());
				chckbx_Centro.setSelected(false);
				chckbx_Cargo.setSelected(false);
				chckbx_grupo.setSelected(false);
				checkBox_Edad.setSelected(false);
				chckbx_busquedaFiltrado.setSelected(false);
				
	            chckbx_Centro.setEnabled(true);
	            chckbx_Cargo.setEnabled(true);
	            chckbx_grupo.setEnabled(true);
	            checkBox_Edad.setEnabled(true);
	            chckbx_busquedaFiltrado.setEnabled(true);
	            
				DefaultTableModel modelo_tabla = new DefaultTableModel();
				c.ObtenerPerfilesCargo(modelo_tabla, comboBox_CargoActual.getSelectedItem().toString());
				System.out.println(comboBox_CargoActual.getSelectedItem().toString());
				CargarPerfiles(modelo_tabla);
				limpiarcomponentes();
			}
			
			/* ********************************************************************************************************************************************************************************** */
			/* Filtrar por texto introducido */
			if((e.getSource()==btn_busqueda && !textField_busqueda.getText().equals("")) && chckbx_busquedaFiltrado.isSelected() )// radiobutton_edad.esta seleccionados
			{
				chckbx_Centro.setSelected(false);
				chckbx_Cargo.setSelected(false);
				chckbx_grupo.setSelected(false);
				checkBox_Edad.setSelected(false);
				chckbx_busquedaFiltrado.setSelected(false);
				
	            chckbx_Centro.setEnabled(true);
	            chckbx_Cargo.setEnabled(true);
	            chckbx_grupo.setEnabled(true);
	            checkBox_Edad.setEnabled(true);
	            chckbx_busquedaFiltrado.setEnabled(true);
	            
				DefaultTableModel modelo_tabla = new DefaultTableModel();
	    		c.ObtenerPerfilesEspecificos(modelo_tabla, textField_busqueda.getText().toString());
	    		CargarPerfiles(modelo_tabla);
	    		limpiarcomponentes();	
			}
			
	}
}