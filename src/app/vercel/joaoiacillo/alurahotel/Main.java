package app.vercel.joaoiacillo.alurahotel;

import javax.swing.JOptionPane;

import app.vercel.joaoiacillo.alurahotel.sql.ConnectionFactory;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			ConnectionFactory.init();
			Interface.openMainMenu();
		} catch (Exception err) {
			err.printStackTrace();
			
			JOptionPane.showMessageDialog(null, err, "Erro no sistema Java", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
