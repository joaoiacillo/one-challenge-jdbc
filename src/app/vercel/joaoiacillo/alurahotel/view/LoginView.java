package app.vercel.joaoiacillo.alurahotel.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.alura.hotel.views.MenuUsuario;

import app.vercel.joaoiacillo.alurahotel.Constants;
import app.vercel.joaoiacillo.alurahotel.Interface;
import app.vercel.joaoiacillo.alurahotel.Main;
import app.vercel.joaoiacillo.alurahotel.auth.Authenticator;
import app.vercel.joaoiacillo.alurahotel.auth.AuthenticationResult;
import app.vercel.joaoiacillo.alurahotel.dialog.AboutDialog;
import app.vercel.joaoiacillo.alurahotel.utils.Images;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 6897760042505710415L;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	private JLabel lblConnection;

	private JButton btnLogIn;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		
		Interface.setNativeLookAndFeel();
		
		setIconImage(Constants.WINDOW_ICON);
		setTitle("Gerenciador Hotel Alura - Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Ajuda");
		mnNewMenu.setIcon(new ImageIcon(LoginView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/help.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mniAbout = new JMenuItem("Sobre");
		mniAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog aboutDialog = new AboutDialog(LoginView.this);
				aboutDialog.setVisible(true);
			}
		});
		mnNewMenu.add(mniAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnglCredentials = new JPanel();
		pnglCredentials.setBorder(new TitledBorder(null, "Credenciais", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pnglCredentials.setBounds(10, 162, 264, 80);
		contentPane.add(pnglCredentials);
		pnglCredentials.setLayout(null);
		
		JLabel lblUsername = new JLabel("Usuário:");
		lblUsername.setBounds(10, 13, 77, 32);
		pnglCredentials.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsername.setIcon(new ImageIcon(LoginView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/user.png")));
		
		txtUsername = new JTextField();
		lblUsername.setLabelFor(txtUsername);
		txtUsername.setBounds(97, 19, 153, 20);
		pnglCredentials.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Senha:");
		lblPassword.setBounds(10, 44, 77, 32);
		pnglCredentials.add(lblPassword);
		lblPassword.setIcon(new ImageIcon(LoginView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/password.png")));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtPassword = new JPasswordField();
		lblPassword.setLabelFor(txtPassword);
		txtPassword.setBounds(97, 50, 153, 20);
		pnglCredentials.add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Digite o usuário e senha de administrador abaixo para entrar no sistema de reservas do hotel.</html>");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 123, 264, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(LoginView.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		lblIcon.setBounds(91, 11, 100, 100);
		contentPane.add(lblIcon);
		
		btnLogIn = new JButton("Entrar");
		btnLogIn.setIcon(new ImageIcon(LoginView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/login.png")));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView.this.login();
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogIn.setBounds(172, 253, 100, 25);
		contentPane.add(btnLogIn);
		
		lblConnection = new JLabel("");
		lblConnection.setBounds(10, 153, 171, 14);
		contentPane.add(lblConnection);
		
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void login() {
		String username = txtUsername.getText().trim();
		String password = new String(txtPassword.getPassword()).trim();
		
		AuthenticationResult result = Authenticator.authenticate(username, password);
		
		String dialogBoxMessage = "";
		
		switch (result) {
		case CONNECTED: {
			ManagerView dashboard = new ManagerView();
			dashboard.setVisible(true);
			
            dispose();
            return;
		}
		case WRONG_USERNAME:
			dialogBoxMessage = "O usuário inserido está invalido.";
			break;
		case WRONG_PASSWORD:
			dialogBoxMessage = "A senha inserida está invalida.";
			break;
		case ERROR:
			dialogBoxMessage = "Um erro inesperado ocorreu.\nPor favor verifique o console.";
			break;
		}
		
		JOptionPane.showMessageDialog(this, dialogBoxMessage, "Erro na Entrada", JOptionPane.ERROR_MESSAGE);
	}
}
