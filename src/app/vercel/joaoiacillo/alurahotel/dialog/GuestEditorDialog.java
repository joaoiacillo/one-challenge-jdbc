package app.vercel.joaoiacillo.alurahotel.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import app.vercel.joaoiacillo.alurahotel.model.GuestModel;

import java.awt.Dialog.ModalityType;

public class GuestEditorDialog extends JDialog {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhoneNumber;
	private JComboBox<String> cmbNationality;
	private JDateChooser dtcBirthday;

	private GuestModel typedModel = null;
	
	public GuestEditorDialog(JFrame owner, GuestModel guestData) {
		this(owner);
		
		setTitle("Gerenciador Hotel Alura - Editar Hóspede");

		this.txtFirstName.setText(guestData.getFirstName());
		this.txtLastName.setText(guestData.getLastName());
		this.txtPhoneNumber.setText(guestData.getPhoneNumber());
		this.cmbNationality.setSelectedIndex(getCmbNationalityValueIndex(guestData.getNationality()));
		this.dtcBirthday.setDate(guestData.getBirthday());
	}

	private int getCmbNationalityValueIndex(String value) {
		for (int index = 0; index < this.cmbNationality.getItemCount() - 1; index++) {
			String itemValue = this.cmbNationality.getItemAt(index);
			if (value.equals(itemValue)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public GuestEditorDialog(JFrame owner) {
		super(owner);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		
		setTitle("Gerenciador Hotel Alura - Novo Hóspede");
		setBounds(100, 100, 451, 403);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 415, 121);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 100, 100);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setIcon(new ImageIcon(GuestEditorDialog.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblNewLabel_2 = new JLabel("Editor de Hóspede");
		lblNewLabel_2.setBounds(120, 25, 285, 28);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_3 = new JLabel("<html>\r\nUtilize as caixas de inserção abaixo para inserir as novas informações sobre o hóspede.\r\n</html>");
		lblNewLabel_3.setBounds(120, 56, 285, 36);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Identidade", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 143, 201, 89);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(40, 26, 33, 14);
		panel_1.add(lblNewLabel);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(83, 23, 108, 20);
		panel_1.add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(14, 54, 59, 14);
		panel_1.add(lblSobrenome);

		txtLastName = new JTextField();
		txtLastName.setBounds(83, 51, 108, 20);
		panel_1.add(txtLastName);
		txtLastName.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Pessoal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 238, 415, 89);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		lblNacionalidade.setBounds(10, 27, 70, 14);
		panel_2.add(lblNacionalidade);

		cmbNationality = new JComboBox<>();
		cmbNationality.setBounds(90, 23, 315, 22);
		panel_2.add(cmbNationality);
		cmbNationality.setModel(new DefaultComboBoxModel<>(new String[] {
			    "Alemão", "Andorrano", "Angolano", "Antiguano", "Saudita", "Argelino", "Argentino", "Armênio", "Australiano", "Austríaco",
			    "Azerbaijano", "Bahamense", "Bangladês, Bangladense", "Barbadiano", "Bahreinita", "Belga", "Belizenho", "Beninês",
			    "Belarusso", "Boliviano", "Bósnio", "Botsuanês", "Brasileiro", "Bruneíno", "Búlgaro", "Burkineonse, Burkinabé",
			    "Burundês", "Butanês", "Cabo-verdiano", "Camerounês", "Cambojano", "Catariano", "Canadense", "Cazaque", "Chadiano",
			    "Chileno", "Chinês", "Cipriota", "Colombiano", "Comoriano", "Congolês", "Congolês", "Sul-coreano", "Norte-coreano",
			    "Costa-marfinense, Marfinense", "Costa-ricense", "Croata", "Cubano", "Dinamarquês", "Djiboutiano", "Dominiquense",
			    "Egípcio", "Salvadorenho", "Emiradense, Emirático", "Equatoriano", "Eritreu", "Eslovaco", "Esloveno", "Espanhol",
			    "Estadunidense, Norte-Americano", "Estoniano", "Etíope", "Fijiano", "Filipino", "Finlandês", "Francês", "Gabonês",
			    "Gambiano", "Ganês ou Ganense", "Georgiano", "Granadino", "Grego", "Guatemalteco", "Guianês", "Guineense", "Guineense, Bissau-guineense",
			    "Equato-guineense", "Haitiano", "Hondurenho", "Húngaro", "Iemenita", "Cookiano", "Marshallês", "Salomonense", "Indian",
			    "Indonésio", "Iraniano", "Iraquiano", "Irlandês", "Islandês", "34", "Jamaicano", "Japonês", "Jordaniano", "Kiribatiano",
			    "Kuwaitiano", "Laosiano", "Lesotiano", "Letão", "Libanês", "Liberiano", "Líbio", "Liechtensteiniano", "Lituano",
			    "Luxemburguês", "Macedônio", "Madagascarense", "Malásio37", "Malawiano", "Maldivo", "Maliano", "Maltês", "Marroquino",
			    "Mauriciano", "Mauritano", "Mexicano", "Myanmarense", "Micronésio", "Moçambicano", "Moldovo", "Monegasco", "Mongol",
			    "Montenegrino", "Namibiano", "Nauruano", "Nepalês", "Nicaraguense", "Nigerino", "Nigeriano", "Niuiano", "Norueguês",
			    "Neozelandês", "Omani", "Neerlandês", "Palauano", "Palestino", "Panamenho", "Papua, Papuásio", "Paquistanês", "Paraguaio",
			    "Peruano", "Polonês, Polaco", "Português", "Queniano", "Quirguiz", "Britânico", "Centro-africano", "Tcheco", "Dominicano",
			    "Romeno", "Ruandês", "Russo", "Samoano", "Santa-lucense", "São-cristovense", "Samarinês", "Santomense", "São-vicentino",
			    "Seichelense", "Senegalês", "Sérvio", "Singapurense", "Sírio", "Somaliano, Somali", "Sri-lankês", "Suázi", "Sudanês",
			    "Sul-sudanês", "Sueco", "Suíço", "Surinamês", "Tajique", "Tailandês", "Tanzaniano", "Timorense", "Togolês", "Tonganês",
			    "Trinitário", "Tunisiano", "Turcomeno", "Turco", "Tuvaluano", "Ucraniano", "Ugandês", "Uruguaio", "Uzbeque", "Vanuatuense",
			    "Vaticano", "Venezuelano", "Vietnamita", "Zambiano", "Zimbabueano"
			}));

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(10, 54, 100, 14);
		panel_2.add(lblDataDeNascimento);

		dtcBirthday = new JDateChooser();
		dtcBirthday.setBounds(116, 51, 289, 20);
		panel_2.add(dtcBirthday);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Contato", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(214, 143, 211, 89);
		getContentPane().add(panel_2_1);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 24, 46, 14);
		panel_2_1.add(lblTelefone);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(66, 21, 135, 20);
		panel_2_1.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewGuest();
			}
		});
		btnOK.setBounds(336, 338, 89, 23);
		getContentPane().add(btnOK);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkIfContainsEntries()) {
					int result = JOptionPane.showOptionDialog(GuestEditorDialog.this, "Ao cancelar a operação, todos os dados\ninseridos serão perdidos. Tem\ncerteza que quer prosseguir?", "Tem certeza que quer cancelar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sim", "Não" }, JOptionPane.NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						dispose();
					}

					return;
				}

				dispose();
			}
		});
		btnCancel.setBounds(237, 338, 89, 23);
		getContentPane().add(btnCancel);
		
		this.setLocationRelativeTo(null);
	}

	private void showOKMessage(String title, String text, int type) {
		JOptionPane.showOptionDialog(this, text, title, JOptionPane.OK_CANCEL_OPTION, type, null, new Object[] { "OK" }, JOptionPane.OK_OPTION);
	}

	private void showErrorMessage(String title, String text) {
		this.showOKMessage(title, text, JOptionPane.ERROR_MESSAGE);
	}

	private void createNewGuest() {
		String firstName = txtFirstName.getText().trim();
		String lastName = txtLastName.getText().trim();
		String phoneNumber = txtPhoneNumber.getText().trim().replaceAll("\\D", "");
		String nationality = cmbNationality.getSelectedItem().toString();
		
		Date birthdayDate = dtcBirthday.getDate();
		if (birthdayDate == null) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para a\ndata de nascimento do hóspede.");
			return;
		}
		
		java.sql.Date birthday = new java.sql.Date(birthdayDate.getTime());

		boolean hasFirstName = !firstName.equals("");
		boolean hasLastName = !lastName.equals("");
		boolean hasPhoneNumber = !phoneNumber.equals("");

		if (!hasFirstName) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para o\nnome do hóspede.");
			return;
		} else if (!hasLastName) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para o\nsobrenome do hóspede.");
			return;
		}else if (!hasPhoneNumber) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para o\ntelefone do hóspede.");
			return;
		}

		GuestModel model = new GuestModel(firstName, lastName, phoneNumber, nationality, birthday);
		this.typedModel = model;

		dispose();
	}

	private boolean checkIfContainsEntries() {
		boolean hasFirstName = !txtFirstName.getText().equals("");
		boolean hasLastName = !txtLastName.getText().equals("");
		boolean hasPhoneNumber = !txtPhoneNumber.getText().equals("");
		boolean hasBirthday = dtcBirthday.getDate() != null;

		return ( hasFirstName || hasLastName ||
				 hasPhoneNumber || hasBirthday);
	}

	public GuestModel getTypedModel() {
		return typedModel;
	}
}
