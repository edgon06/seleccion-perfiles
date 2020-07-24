package programa;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Vista_Filtrado extends JFrame implements ActionListener{

	/* ******************************************************************************************************************************************** */
	/* Propiedades de la clase */
	private JPanel contentPane;

	private JButton boton_filtrar;
	private JButton boton_limpiar;
	private JTextField textField_Experiencia;

	/* ******************************************************************************************************************************************** */

	/**
	 * Construir la ventana.
	 */
	public Vista_Filtrado() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Controlador c = new Controlador();
		
		JLabel lblCentroRegional = new JLabel("Centro Regional:");
		lblCentroRegional.setBounds(38, 70, 116, 14);
		contentPane.add(lblCentroRegional);
		
		JLabel lblRangoDeEdad = new JLabel("Rango de Edad:");
		lblRangoDeEdad.setBounds(38, 110, 116, 14);
		contentPane.add(lblRangoDeEdad);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(38, 147, 116, 14);
		contentPane.add(lblSexo);
		
		JLabel label_area_laboral = new JLabel("Area Laboral:");
		label_area_laboral.setBounds(38, 225, 101, 14);
		contentPane.add(label_area_laboral);
		
		JLabel lblExperiencia = new JLabel("A\u00F1os de Experiencia:");
		lblExperiencia.setBounds(38, 194, 137, 14);
		contentPane.add(lblExperiencia);
		
		JLabel label_Cargos = new JLabel("Cargos Anteriores:");
		label_Cargos.setBounds(38, 256, 116, 14);
		contentPane.add(label_Cargos);
		
		JLabel label_comparar_texto = new JLabel("Comparar Texto:");
		label_comparar_texto.setBounds(359, 70, 123, 14);
		contentPane.add(label_comparar_texto);
		
		JComboBox comboBox_centro_regional = new JComboBox();
		comboBox_centro_regional.setBounds(164, 67, 116, 20);
		contentPane.add(comboBox_centro_regional);
		
		JComboBox comboBox_edad_min = new JComboBox();
		comboBox_edad_min.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "18"}));// Hay que crear y llenar lista de numeros
		comboBox_edad_min.setBounds(164, 107, 54, 20);
		contentPane.add(comboBox_edad_min);
		
		JComboBox comboBox_edad_max = new JComboBox();
		comboBox_edad_max.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "90"})); // Hay que crear y llenar lista de numeros
		comboBox_edad_max.setBounds(228, 107, 52, 20);
		contentPane.add(comboBox_edad_max);
		
		JComboBox comboBox_sexo = new JComboBox();
		comboBox_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "m", "f"}));
		comboBox_sexo.setBounds(164, 144, 116, 20);
		contentPane.add(comboBox_sexo);
		
		//El area laboral será el Grupo Ocupacional
		JComboBox comboBox_area_laboral = new JComboBox();
		comboBox_area_laboral.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  })); // Hay que crear y llenar esta lista 
		comboBox_area_laboral.setBounds(164, 222, 447, 20);
		contentPane.add(comboBox_area_laboral);
		
		JComboBox comboBox_puestos = new JComboBox();
		comboBox_puestos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));// Hay que crear y llenar esta lista
		comboBox_puestos.setBounds(164, 253, 447, 20);
		contentPane.add(comboBox_puestos);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(359, 95, 252, 93);
		contentPane.add(textPane);
		
		c.LlenarComboBoxes(comboBox_centro_regional, comboBox_puestos, comboBox_area_laboral);
		
		boton_filtrar = new JButton("Filtrar");
		boton_filtrar.addActionListener(this);
		boton_filtrar.setBounds(212, 284, 89, 23);
		contentPane.add(boton_filtrar);
		
		boton_limpiar = new JButton("Limpiar");
		boton_limpiar.addActionListener(this);
		boton_limpiar.setBounds(324, 284, 89, 23);
		contentPane.add(boton_limpiar);
		
		textField_Experiencia = new JTextField();
		textField_Experiencia.setBounds(164, 191, 116, 20);
		contentPane.add(textField_Experiencia);
		textField_Experiencia.setColumns(10);
	}

	/* ******************************************************************************************************************************************** */
	/* Eventos */
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		
	}
}
