package app.vercel.joaoiacillo.alurahotel;

import java.awt.Image;
import java.awt.Toolkit;

import app.vercel.joaoiacillo.alurahotel.view.LoginView;

public class Constants {
	public final static Image WINDOW_ICON = Toolkit.getDefaultToolkit().getImage(Constants.class.getResource("/com/alura/hotel/images/aH-40px.png"));
	
	public final static String VERSION = "1.0.0-dev"; 
}
