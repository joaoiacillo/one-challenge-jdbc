package app.vercel.joaoiacillo.alurahotel.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.vercel.joaoiacillo.alurahotel.Constants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class AboutDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Create the dialog.
	 */
	public AboutDialog(Window owner) {
		super(owner);
		setModal(true);
		
		setIconImage(Constants.WINDOW_ICON);
		setTitle("Sobre Gerenciador Hotel Alura");
		setBounds(100, 100, 576, 259);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AboutDialog.class.getResource("/com/alura/hotel/images/aH-150px.png")));
		lblNewLabel.setBounds(10, 11, 150, 150);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Gerenciador Hotel Alura");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(170, 11, 380, 27);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblVersion = new JLabel(Constants.VERSION);
		lblVersion.setBounds(170, 39, 371, 14);
		contentPanel.add(lblVersion);
		
		JLabel lblCopyrightc = new JLabel("Copyright (c) 2023 Hotel Alura");
		lblCopyrightc.setBounds(170, 64, 371, 14);
		contentPanel.add(lblCopyrightc);
		
		JLabel lblgerenciadorHotelAlura = new JLabel("<html>\r\n<strong><em>Gerenciador Hotel Alura</em></strong> foi desenvolvido para um desafio proposto pelo Oracle Next Education, um curso online e gratuito onde ensinar jovens e adultos sobre o mundo da tecnologia e entrar no mercado de trabalho.\r\n</html>");
		lblgerenciadorHotelAlura.setBounds(170, 89, 371, 50);
		contentPanel.add(lblgerenciadorHotelAlura);
		
		JLabel lblCriadoPorJoo = new JLabel("<html>\r\nCriado por João Pedro Iacillo Soares - &lt;<a href=\"emailto:joaopiacillo@outlook.com.br\">joaopiacillo@outlook.com.br</a>&gt;\r\n</html>");
		lblCriadoPorJoo.setBounds(20, 150, 530, 27);
		contentPanel.add(lblCriadoPorJoo);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		setLocationRelativeTo(owner);
	}

}
