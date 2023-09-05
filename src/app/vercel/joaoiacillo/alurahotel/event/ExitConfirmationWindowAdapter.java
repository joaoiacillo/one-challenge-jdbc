package app.vercel.joaoiacillo.alurahotel.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.vercel.joaoiacillo.alurahotel.auth.Authenticator;

public class ExitConfirmationWindowAdapter extends WindowAdapter {
	private JFrame window;

	public ExitConfirmationWindowAdapter(JFrame window) {
		super();
		this.window = window;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.exit();
	}
	
	private int showDialog() {
		return JOptionPane.showOptionDialog(
				this.window,
				"<html><body><p style='width: 200px'>Ao sair da conta você irá perder todo o progresso realizado até o momento. Tem certeza que quer prosseguir?</p></body></html>",
				"Tem certeza que quer sair?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new Object[] { "Sim", "Não" }, 
				JOptionPane.NO_OPTION);
	}
	
	private void exit() {
		int result = this.showDialog();
		
		if (result == JOptionPane.YES_OPTION) {
			Authenticator.logOut();
			System.exit(0);
		}
	}
}

