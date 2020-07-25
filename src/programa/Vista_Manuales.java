package programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Vista_Manuales extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBox_busqueda;
	private JLabel lblBuscar;
	private String direccion_manuales = System.getProperty("user.dir") + "\\manuales de cargo\\";


	/**
	 * Constructor de la ventana
	 */
	public Vista_Manuales() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 732, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		comboBox_busqueda = new JComboBox();
		comboBox_busqueda.setEditable(false);
		panel.add(comboBox_busqueda);
		
		lblBuscar = new JLabel("Buscar");
		panel.add(lblBuscar);
		
		ListarManuales();
	}

	
	public void ListarManuales()
	{
		DefaultComboBoxModel<String> modelo_manuales = new javax.swing.DefaultComboBoxModel<String>();
		
		File carpeta = new File(direccion_manuales);
		String[] listado = carpeta.list();
		if (listado == null || listado.length == 0)
		{
		    System.out.println("No hay elementos dentro de la carpeta actual");
		}
		else 
		{
		    for (int i=0; i< listado.length; i++) 
		    {
		    	modelo_manuales.addElement(listado[i]);
		    }
		}
		comboBox_busqueda.setModel(modelo_manuales);	
	}
}
