package programa;

import java.awt.EventQueue;

public class Programa {
	
	/**
	 * Metodo Principal.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista_Principal frame = new Vista_Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
