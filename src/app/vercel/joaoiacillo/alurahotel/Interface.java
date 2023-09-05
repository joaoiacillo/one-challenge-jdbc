package app.vercel.joaoiacillo.alurahotel;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.alura.hotel.views.MenuPrincipal;

import app.vercel.joaoiacillo.alurahotel.view.LoginView;

public class Interface {

	public static void openMainMenu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginView frame = new LoginView();
				frame.setVisible(true);
			}
		});
	}
	
	public static void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
