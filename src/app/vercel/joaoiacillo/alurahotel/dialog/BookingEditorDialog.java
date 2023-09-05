package app.vercel.joaoiacillo.alurahotel.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import app.vercel.joaoiacillo.alurahotel.controller.GuestController;
import app.vercel.joaoiacillo.alurahotel.daily.DailyValueCalculator;
import app.vercel.joaoiacillo.alurahotel.model.BookingModel;
import app.vercel.joaoiacillo.alurahotel.model.GuestModel;

import java.awt.Dialog.ModalityType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class BookingEditorDialog extends JDialog {

	private BookingModel typedModel = null;
	
	private ArrayList<GuestModel> guestModels;
	private JDateChooser dtcCheckin;
	private JDateChooser dtcCheckout;
	private JComboBox<String> cmbGuest;
	private JTextField txtPrice;
	private JComboBox<String> cmbPaymentMethod;
	
	public BookingEditorDialog(JFrame owner, BookingModel bookingData) {
		this(owner);
		
		setTitle("Gerenciador Hotel Alura - Editar Reserva");
		
		this.dtcCheckin.setDate(bookingData.getCheckinDate());
		this.dtcCheckout.setDate(bookingData.getCheckoutDate());
		this.txtPrice.setText(((Double) bookingData.getPrice()).toString());
		
		switch (bookingData.getPaymentMethod()) {
		case "Debit Card":
			this.cmbPaymentMethod.setSelectedIndex(1);
			break;
		case "Bank Payment Slip":
			this.cmbPaymentMethod.setSelectedIndex(2);
			break;
		default:
			this.cmbPaymentMethod.setSelectedIndex(0);
			break;
		}
		
		for (int i = 0; i < guestModels.size(); i++)
			if (guestModels.get(i).getId() == bookingData.getGuestId())
				this.cmbGuest.setSelectedIndex(i);

	}

	private int getCmbNationalityValueIndex(String value) {
		for (int index = 0; index < this.cmbPaymentMethod.getItemCount() - 1; index++) {
			String itemValue = this.cmbPaymentMethod.getItemAt(index);
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
	public BookingEditorDialog(JFrame owner) {
		super(owner);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		
		setTitle("Gerenciador Hotel Alura - Nova Reserva");
		setBounds(100, 100, 451, 377);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 415, 121);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 11, 100, 100);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setIcon(new ImageIcon(BookingEditorDialog.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblNewLabel_2 = new JLabel("Editor de Reserva");
		lblNewLabel_2.setBounds(120, 25, 285, 28);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_3 = new JLabel("<html>\r\nUtilize as caixas de inserção abaixo para inserir as novas informações sobre a reserva.\r\n</html>");
		lblNewLabel_3.setBounds(120, 56, 285, 36);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 143, 201, 89);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCheckin = new JLabel("Checkin:");
		lblCheckin.setBounds(22, 26, 41, 14);
		panel_1.add(lblCheckin);

		JLabel lblCheckout = new JLabel("Checkout:");
		lblCheckout.setBounds(14, 57, 49, 14);
		panel_1.add(lblCheckout);
		
		dtcCheckin = new JDateChooser();
		dtcCheckin.setBounds(73, 23, 118, 20);
		panel_1.add(dtcCheckin);
		
		dtcCheckout = new JDateChooser();
		dtcCheckout.setBounds(73, 54, 118, 20);
		panel_1.add(dtcCheckout);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "H\u00F3spede", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 238, 415, 60);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNacionalidade = new JLabel("Hóspede:");
		lblNacionalidade.setBounds(10, 27, 70, 14);
		panel_2.add(lblNacionalidade);
		
		cmbGuest = new JComboBox<String>();
		cmbGuest.setBounds(90, 23, 315, 22);
		panel_2.add(cmbGuest);
		
		DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>) cmbGuest.getModel();
		
		guestModels = new GuestController().list();
		for (GuestModel model : guestModels) {
			comboBoxModel.addElement(model.getFirstName() + " " + model.getLastName());
		}

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pre\u00E7os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(214, 143, 211, 89);
		getContentPane().add(panel_2_1);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(23, 26, 27, 14);
		panel_2_1.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setEditable(false);
		txtPrice.setBounds(66, 21, 98, 20);
		panel_2_1.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblMtodo = new JLabel("Método:");
		lblMtodo.setBounds(10, 55, 40, 14);
		panel_2_1.add(lblMtodo);
		
		cmbPaymentMethod = new JComboBox<>();
		cmbPaymentMethod.setBounds(66, 52, 135, 22);
		panel_2_1.add(cmbPaymentMethod);
		cmbPaymentMethod.setModel(new DefaultComboBoxModel<>(new String[] {
		    "Cartão de Crédito", "Cartão de Débito", "Boleto"
		}));
		
		JButton btnRefreshPrice = new JButton("");
		btnRefreshPrice.setIcon(new ImageIcon(BookingEditorDialog.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/refresh.png")));
		btnRefreshPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double newDailyValue = DailyValueCalculator.calculate(
						new java.sql.Date(dtcCheckin.getDate().getTime()),
						new java.sql.Date(dtcCheckout.getDate().getTime())
				);
				
				txtPrice.setText(((Double) newDailyValue).toString());
			}
		});
		btnRefreshPrice.setBounds(174, 19, 27, 25);
		panel_2_1.add(btnRefreshPrice);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewBooking();
			}
		});
		btnOK.setBounds(336, 309, 89, 23);
		getContentPane().add(btnOK);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkIfContainsEntries()) {
					int result = JOptionPane.showOptionDialog(BookingEditorDialog.this, "Ao cancelar a operação, todos os dados\ninseridos serão perdidos. Tem\ncerteza que quer prosseguir?", "Tem certeza que quer cancelar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Sim", "Não" }, JOptionPane.NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						dispose();
					}

					return;
				}

				dispose();
			}
		});
		btnCancel.setBounds(237, 309, 89, 23);
		getContentPane().add(btnCancel);
		
		this.setLocationRelativeTo(null);
	}

	private void showOKMessage(String title, String text, int type) {
		JOptionPane.showOptionDialog(this, text, title, JOptionPane.OK_CANCEL_OPTION, type, null, new Object[] { "OK" }, JOptionPane.OK_OPTION);
	}

	private void showErrorMessage(String title, String text) {
		this.showOKMessage(title, text, JOptionPane.ERROR_MESSAGE);
	}

	private void createNewBooking() {
		Date checkinDate = dtcCheckin.getDate();
		Date checkoutDate = dtcCheckout.getDate();
		
		if (checkinDate == null) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para a\ndata de checkin.");
			return;
		} else if (checkoutDate == null) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para a\ndata de checkout.");
			return;
		}
		
		java.sql.Date checkin = new java.sql.Date(checkinDate.getTime());
		java.sql.Date checkout = new java.sql.Date(checkoutDate.getTime());
		String priceText = txtPrice.getText().trim();
		int paymentMethodIndex = cmbPaymentMethod.getSelectedIndex();
		int guestId = guestModels.get(cmbGuest.getSelectedIndex()).getId();
		
		boolean hasPrice = priceText != "";
		
		if (!hasPrice) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para o\npreço.");
			return;
		}
		
		String paymentMethod = "Credit Card";
		
		switch (paymentMethodIndex) {
		case 1:
			paymentMethod = "Debit Card";
			break;
		case 2:
			paymentMethod = "Bank Payment Slip";
			break;
		}
		
		double price;
		try {
			price = Double.parseDouble(priceText);
		} catch (NumberFormatException e) {
			this.showErrorMessage("Algum valor não foi inserido.", "Por favor, insira um valor válido para o\npreço.");
			return;
		}
		
		BookingModel model = new BookingModel(checkin, checkout, price, paymentMethod, guestId);
		this.typedModel = model;
		
		dispose();
	}

	private boolean checkIfContainsEntries() {
		return false;
	}

	public BookingModel getTypedModel() {
		return typedModel;
	}
}
