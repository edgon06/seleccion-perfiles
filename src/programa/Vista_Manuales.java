package programa;

import java.awt.BorderLayout;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Vista_Manuales extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox<String> comboBox_busqueda;
	private String direccion_manuales = System.getProperty("user.dir") + "\\manuales de cargo\\";
	private String filePath;
	private SwingController controller;
	private JButton abrir;

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
	    controller = new SwingController();
	    

	    SwingViewBuilder factory = new SwingViewBuilder(controller);
		JPanel busqueda_archivo= new JPanel();
		JPanel viewerComponentPanel = factory.buildViewerPanel();
		contentPane.add(viewerComponentPanel, BorderLayout.CENTER);
		
		
		controller.getDocumentViewController().setAnnotationCallback(
	                new org.icepdf.ri.common.MyAnnotationCallback(
	                        controller.getDocumentViewController()));
		
		this.add(viewerComponentPanel);
		
		   
		comboBox_busqueda = new JComboBox();
		comboBox_busqueda.setEditable(false);
		abrir = new JButton("Abrir Archivo");
		abrir.addActionListener(this);
		busqueda_archivo.add(comboBox_busqueda);
		busqueda_archivo.add(abrir);
		
		contentPane.add(busqueda_archivo,BorderLayout.NORTH);
		
		ListarManuales();
        this.pack();
        this.setVisible(false);
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
	
	public void AbrirManual(String filePath) {
		
		controller.openDocument(filePath);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==abrir)
		{
			
			 AbrirManual(System.getProperty("user.dir") + "\\manuales de cargo\\"+comboBox_busqueda.getSelectedItem().toString());
		}
		
	}
}
