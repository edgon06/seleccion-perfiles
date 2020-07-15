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
import javax.swing.DefaultListModel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
public class Vista_Principal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel_lista_perfiles;
	private JPanel panel_filtrado;
	private JPanel panel_contenido;
	private JPanel panel_perfil;
	private JButton boton_ejemplo;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	
	/* Metodo para inicializar barra de menu*/
	private void IncilizarBarraMenu(JPanel panel) 
	{
		//Crear Barra de Menu 
		JMenuBar menuBar = new JMenuBar();
		//Aniadir Barra de menu en la ventana
		setJMenuBar(menuBar);
		
		//Crear boton de 'Filtrado Avanzado'
		JMenuItem filtrar = new JMenuItem("Filtrado Avanzado");
		filtrar.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(filtrar);
		
		//Crear boton de 'Manuales de Cargo'
		JMenuItem manuales = new JMenuItem("Manuales de Cargo");
		manuales.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(manuales);
		
		
	}
	
	//Metodo para incializar los parametros de los paneles en la ventana principal
	private void InicializarPaneles( JPanel panelPrincipal) 
	{
				
	//Inicializar panel de lista de perfiles
		panel_lista_perfiles = new JPanel();
		panel_lista_perfiles.setPreferredSize(new Dimension(200, 300));// Controlar el grosor del panel
		panel_lista_perfiles.setBorder(new LineBorder(new Color(0, 0, 0)));
	
	//Inicializar panel que tendra el perfil seleccionado y las opciones de filtrado
		panel_contenido = new JPanel();
		panel_contenido.setLayout(new BorderLayout(0,0));
		
	//Inicializar panel de opciones de filtrado
		panel_filtrado = new JPanel();
		panel_filtrado.setBorder(new LineBorder(new Color(0, 0, 0)));
				
	//Inicializar panel del perfil seleccionado
		panel_perfil = new JPanel();
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
	//Aniadir panel donde se muestra el perfil seleccionado en el panel	derecho
		panel_contenido.add(panel_perfil,BorderLayout.CENTER);
	//Aniadir el panel derecho al panel principal		
		panelPrincipal.add(panel_contenido,BorderLayout.CENTER);
	}
	
	//Metodo para cargar el panel con las opciones de filtrado
	private void CargarOpcionesFiltrado(JPanel panel)
	{
		//Establecer el Layout del panel con las opciones de filtrado:
		panel_filtrado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Boton de ejemplo
		boton_ejemplo = new JButton("Hola Mundo");
		boton_ejemplo.addActionListener(this);
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		
		//Aniadir botones de ejemplo en el panel de filtrado
		panel.add(rdbtnNewRadioButton);
		panel.add(boton_ejemplo);
	}
	
	//Metodo para llenar el panel de perfiles con la lista de perfiles
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
		  //Le asignamos un texto
		  ejemplo2.setText("HOLA MUNDO!!");
		  
		  
		panel.add(ejemplo);
		panel.add(ejemplo2);
		
	}
	
	//Metodo para mostrar el perfil seleccionado en el panel de contenido
	private void MostrarPerfil(JPanel panel)
	{
		//Establecer el Layout del panel con la lista de perfiles:
		panel_perfil.setLayout(null);
		
		/*
		 * Funciones de clase controlador para cargar el panel con datos del perfil seleccionado
		 * */
		
		panel_perfil.revalidate();
		panel_perfil.validate();
	}
	
	/**
	 * Construir la ventana.
	 */
	public Vista_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 431);
	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		IncilizarBarraMenu(contentPane);
		InicializarPaneles(contentPane);
	}

	// Eventos de los botones
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Evento a ejecutarse al precionar boton 'boton_ejemplo'
		if(e.getSource()==boton_ejemplo) 
		{
			metodoBotonEjemplo();
		}
	}
	
	public void metodoBotonEjemplo()
	{
		//Ejemplo de como aniadir elemenots a un panel de forma dinamica
			panel_lista_perfiles.add(new JButton("Button"));
			panel_lista_perfiles.revalidate();
			panel_lista_perfiles.validate();
		
	}
}
