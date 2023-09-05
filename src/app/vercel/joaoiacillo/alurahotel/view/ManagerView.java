package app.vercel.joaoiacillo.alurahotel.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.vercel.joaoiacillo.alurahotel.Constants;
import app.vercel.joaoiacillo.alurahotel.Interface;
import app.vercel.joaoiacillo.alurahotel.auth.Authenticator;
import app.vercel.joaoiacillo.alurahotel.controller.BookingController;
import app.vercel.joaoiacillo.alurahotel.controller.GuestController;
import app.vercel.joaoiacillo.alurahotel.dao.BookingDAO;
import app.vercel.joaoiacillo.alurahotel.dao.GuestDAO;
import app.vercel.joaoiacillo.alurahotel.dialog.AboutDialog;
import app.vercel.joaoiacillo.alurahotel.dialog.BookingEditorDialog;
import app.vercel.joaoiacillo.alurahotel.dialog.GuestEditorDialog;
import app.vercel.joaoiacillo.alurahotel.event.ExitConfirmationWindowAdapter;
import app.vercel.joaoiacillo.alurahotel.model.BookingModel;
import app.vercel.joaoiacillo.alurahotel.model.GuestModel;
import app.vercel.joaoiacillo.alurahotel.sql.ConnectionFactory;
import app.vercel.joaoiacillo.alurahotel.utils.RowFilterUtil;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.mchange.v1.cachedstore.CachedStore.Manager;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;

public class ManagerView extends JFrame {

	private JPanel contentPane;
	
	private JTable tblGuests;
	private JTable tblBookings;
	
	private JTextField txtGuestFilter;
	private JTextField txtBookingFilter;
	
	private JButton btnEditGuest;
	private JButton btnDeleteGuest;
	private JButton btnGuestsUseAsFilter;
	private JButton btnEditBooking;
	private JButton btnDeleteBooking;
	private JButton btnBookingsUseAsFilter;
	private JButton btnLookUpGuestBookings;

	private JTabbedPane tbpSelectors;

	/**
	 * Create the frame.
	 */
	public ManagerView() {
		addWindowListener(new ExitConfirmationWindowAdapter(this));
		
		setResizable(false);		
		Interface.setNativeLookAndFeel();
		
		setTitle("Gerenciador Hotel Alura");
		setIconImage(Constants.WINDOW_ICON);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSystem = new JMenu("Sistema");
		mnSystem.setHorizontalAlignment(SwingConstants.TRAILING);
		mnSystem.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/small-computer.png")));
		menuBar.add(mnSystem);
		
		JMenuItem mniLogOut = new JMenuItem("Sair");
		mniLogOut.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/exit.png")));
		mniLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispatchEvent(new WindowEvent(ManagerView.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		mnSystem.add(mniLogOut);
		
		JMenu mnHelp = new JMenu("Ajuda");
		mnHelp.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/help.png")));
		menuBar.add(mnHelp);
		
		JMenuItem mniAbout = new JMenuItem("Sobre");
		mniAbout.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/about.png")));
		mniAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog aboutDialog = new AboutDialog(ManagerView.this);
				aboutDialog.setVisible(true);
			}
		});
		mnHelp.add(mniAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHotelLogo = new JLabel("");
		lblHotelLogo.setIcon(new ImageIcon(ManagerView.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		lblHotelLogo.setBounds(10, 11, 100, 100);
		contentPane.add(lblHotelLogo);
		
		JLabel lblTitle = new JLabel("Gerenciador de Hóspedes e Reservas");
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(120, 25, 654, 27);
		contentPane.add(lblTitle);
		
		JLabel lblDescription = new JLabel("<html>Bem vindo, " + Authenticator.getAuthenticatedUser() + ". Essa janela é a introdução do gerenciador do Hotel Alura. A utilize para visualizar e modificar os registros de hóspedes e reservas. Lembre-se, você é a cara do nosso hotel, então sorrie sempre.</html>");
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setBounds(120, 56, 654, 47);
		contentPane.add(lblDescription);
		
		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(10, 122, 764, 406);
		contentPane.add(pnlMain);
		pnlMain.setLayout(new BorderLayout(0, 0));
		
		tbpSelectors = new JTabbedPane(JTabbedPane.TOP);
		pnlMain.add(tbpSelectors);
		
		JPanel pnlGuestSelector = new JPanel();
		tbpSelectors.addTab("Hóspedes", new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/guest.png")), pnlGuestSelector, null);
		pnlGuestSelector.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scpGuests = new JScrollPane();
		pnlGuestSelector.add(scpGuests);
		
		tblGuests = new JTable();
		tblGuests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblGuests.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Sobrenome", "Anivers\u00E1rio", "Nacionalidade", "Telefone"
			}
		) {
			private static final long serialVersionUID = 2874411187897629333L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblGuests.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				activateGuestActions();
				
			}
			
		});
		
		scpGuests.setViewportView(tblGuests);
		
		JPanel pnlGuestActions = new JPanel();
		FlowLayout fl_pnlGuestActions = (FlowLayout) pnlGuestActions.getLayout();
		fl_pnlGuestActions.setAlignment(FlowLayout.LEFT);
		pnlGuestSelector.add(pnlGuestActions, BorderLayout.SOUTH);
		
		btnEditGuest = new JButton("Editar");
		btnEditGuest.setEnabled(false);
		btnEditGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateGuest();
			}
		});
		
		JButton btnAddGuest = new JButton("Adicionar");
		btnAddGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertGuest();
			}
		});
		btnAddGuest.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/cylinder.png")));
		pnlGuestActions.add(btnAddGuest);
		btnEditGuest.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/edit.png")));
		pnlGuestActions.add(btnEditGuest);
		
		btnDeleteGuest = new JButton("Deletar");
		btnDeleteGuest.setEnabled(false);
		btnDeleteGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeGuest();
			}
		});
		btnDeleteGuest.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/remove.png")));
		pnlGuestActions.add(btnDeleteGuest);
		
		btnLookUpGuestBookings = new JButton("Procurar Reservas do Hóspede");
		btnLookUpGuestBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lookUpGuestBookings();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		pnlGuestActions.add(separator);
		btnLookUpGuestBookings.setEnabled(false);
		btnLookUpGuestBookings.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/lookup.png")));
		pnlGuestActions.add(btnLookUpGuestBookings);
		
		JPanel pnlGuestFilter = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlGuestFilter.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlGuestSelector.add(pnlGuestFilter, BorderLayout.NORTH);
		
		JLabel lblGuestFilter = new JLabel(" Filtro: ");
		pnlGuestFilter.add(lblGuestFilter);
		
		txtGuestFilter = RowFilterUtil.createRowFilter(tblGuests);
		pnlGuestFilter.add(txtGuestFilter);
		
		JButton btnGuestsCleanFilter = new JButton("Limpar Filtro");
		btnGuestsCleanFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtGuestFilter.setText("");
			}
		});
		btnGuestsCleanFilter.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/filter-delete.png")));
		pnlGuestFilter.add(btnGuestsCleanFilter);
		
		btnGuestsUseAsFilter = new JButton("Usar Valor Atual Como Filtro");
		btnGuestsUseAsFilter.setEnabled(false);
		pnlGuestFilter.add(btnGuestsUseAsFilter);
		btnGuestsUseAsFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useCurrentValueAsFilterOf(tblGuests, txtGuestFilter);
			}
		});
		btnGuestsUseAsFilter.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/filter.png")));
		
		JPanel pngBookingSelector = new JPanel();
		tbpSelectors.addTab("Reservas", new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/booking.png")), pngBookingSelector, null);
		pngBookingSelector.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scpBookings = new JScrollPane();
		pngBookingSelector.add(scpBookings, BorderLayout.CENTER);
		
		tblBookings = new JTable();
		tblBookings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookings.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "N\u00FAmero", "Checkin", "Checkout", "Pre\u00E7o", "M\u00E9todo de Pagamento", "H\u00F3spede"
			}
		) {
			private static final long serialVersionUID = 7155487209977340138L;
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblBookings.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				activateBookingActions();
				
			}
			
		});
		scpBookings.setViewportView(tblBookings);
		
		JPanel pnlBookingActions = new JPanel();
		FlowLayout fl_pnlBookingActions = (FlowLayout) pnlBookingActions.getLayout();
		fl_pnlBookingActions.setAlignment(FlowLayout.LEFT);
		pngBookingSelector.add(pnlBookingActions, BorderLayout.SOUTH);
		
		btnEditBooking = new JButton("Editar");
		btnEditBooking.setEnabled(false);
		btnEditBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBooking();
			}
		});
		
		JButton btnAddBooking = new JButton("Adicionar");
		btnAddBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertBooking();
			}
		});
		btnAddBooking.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/cylinder.png")));
		pnlBookingActions.add(btnAddBooking);
		btnEditBooking.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/edit.png")));
		pnlBookingActions.add(btnEditBooking);
		
		btnDeleteBooking = new JButton("Deletar");
		btnDeleteBooking.setEnabled(false);
		btnDeleteBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeBooking();
			}
		});
		btnDeleteBooking.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/remove.png")));
		pnlBookingActions.add(btnDeleteBooking);
		
		JPanel pnlBookingFilter = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlBookingFilter.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pngBookingSelector.add(pnlBookingFilter, BorderLayout.NORTH);
		
		JLabel lblBookingFilter = new JLabel(" Filtro: ");
		pnlBookingFilter.add(lblBookingFilter);
		
		txtBookingFilter = RowFilterUtil.createRowFilter(tblBookings);
		pnlBookingFilter.add(txtBookingFilter);
		
		JButton btnBookingsCleanFilter = new JButton("Limpar Filtro");
		btnBookingsCleanFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookingFilter.setText("");
			}
		});
		btnBookingsCleanFilter.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/filter-delete.png")));
		pnlBookingFilter.add(btnBookingsCleanFilter);
		
		btnBookingsUseAsFilter = new JButton("Usar Valor Atual Como Filtro");
		btnBookingsUseAsFilter.setEnabled(false);
		btnBookingsUseAsFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useCurrentValueAsFilterOf(tblBookings, txtBookingFilter);
			}
		});
		btnBookingsUseAsFilter.setIcon(new ImageIcon(ManagerView.class.getResource("/app/vercel/joaoiacillo/alurahotel/images/filter.png")));
		pnlBookingFilter.add(btnBookingsUseAsFilter);
		
		this.updateGuestsTable();
		this.updateBookingsTable();
		setLocationRelativeTo(null);
	}
	
	private void lookUpGuestBookings() {
		DefaultTableModel tableModel = (DefaultTableModel) tblGuests.getModel();
		
		int selectedRow = tblGuests.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede.", "Nenhum hóspede selecionado.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int modelRow = tblGuests.convertRowIndexToModel(selectedRow);
		
		String firstName = tableModel.getValueAt(modelRow, 1).toString();
		String lastName = tableModel.getValueAt(modelRow, 2).toString();
		
		String query = firstName + " " + lastName;
		
		txtBookingFilter.setText(query);
		tbpSelectors.setSelectedIndex(1);
	}

	private void activateActions(JButton[] actions) {
		for (JButton btn : actions) btn.setEnabled(true);
	}
	
	private void activateGuestActions() {
		JButton[] buttons = { this.btnEditGuest, this.btnDeleteGuest, this.btnGuestsUseAsFilter, this.btnLookUpGuestBookings };
		this.activateActions(buttons);
	}
	
	private void activateBookingActions() {
		JButton[] buttons = { this.btnEditBooking, this.btnDeleteBooking, this.btnBookingsUseAsFilter };
		this.activateActions(buttons);
	}

	private Object[] guestModelToObject(GuestModel model) {
		return new Object[] {
			model.getId(),
			model.getFirstName(),
			model.getLastName(),
			model.getBirthday(),
			model.getNationality(),
			model.getPhoneNumber()
		};
	}
	
	private void insertGuest() {
		GuestEditorDialog editorDialog = new GuestEditorDialog(this);
		editorDialog.setVisible(true);
		
		GuestModel model = editorDialog.getTypedModel();
		if (model == null) return;
		
		new GuestController().insert(model);
		this.updateGuestsTable();
	}

	private void updateGuest() {
		DefaultTableModel tableModel = (DefaultTableModel) tblGuests.getModel();
		
		int selectedRow = tblGuests.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede.", "Nenhum hóspede selecionado.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int modelRow = tblGuests.convertRowIndexToModel(selectedRow);
		int id = (int) tableModel.getValueAt(modelRow, 0);
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			GuestModel model = dao.select(id);
			
			GuestEditorDialog editorDialog = new GuestEditorDialog(this, model);
			editorDialog.setVisible(true);
			
			GuestModel typedModel = editorDialog.getTypedModel();
			if (typedModel == null) return; 
			dao.update(id, typedModel);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
		this.updateGuestsTable();
	}
	
	private void removeGuest() {
		DefaultTableModel tableModel = (DefaultTableModel) tblGuests.getModel();
		
		int selectedRow = tblGuests.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um hóspede.", "Nenhum hóspede selecionado.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int confirmResult = JOptionPane.showConfirmDialog(this, "<html><body><p style='width: 200px'>Remover esse hóspede pode resultar na remoção de hospedagens realizadas por ele. Tem certeza que quer continuar?</p></body></html>", "Remoção de Hóspede", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		System.out.println(confirmResult);
		if (confirmResult != JOptionPane.YES_OPTION) {
			return;
		}
		
		int modelRow = tblGuests.convertRowIndexToModel(selectedRow);
		int id = (int) tableModel.getValueAt(modelRow, 0);
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			GuestDAO dao = new GuestDAO(conn);
			dao.remove(id);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
		this.updateGuestsTable();
		this.updateBookingsTable();
	}
	
	private void updateGuestsTable() {
		DefaultTableModel tableModel = (DefaultTableModel) this.tblGuests.getModel();
		tableModel.setRowCount(0);
		
		ArrayList<GuestModel> values = new GuestController().list();
		
		for (GuestModel model : values) {
			Object[] modelObject = this.guestModelToObject(model);
			tableModel.addRow(modelObject);
		}
	}
	
	private Object[] bookingModelToObject(BookingModel model) {
		String paymentMethod = "Cartão de Crédito";
		
		switch (model.getPaymentMethod()) {
		case "Debit Card":
			paymentMethod = "Cartão de Débito";
			break;
		case "Bank Payment Slip":
			paymentMethod = "Boleto";
			break;
		}
		
		int guestId = model.getGuestId();
		
		GuestModel guestModel = new GuestController().get(guestId);
		
		return new Object[] {
			model.getId(),
			model.getNumber(),
			model.getCheckinDate(),
			model.getCheckoutDate(),
			model.getPrice(),
			paymentMethod,
			guestModel.getFirstName() + " " + guestModel.getLastName()
		};
	}
	
	private void insertBooking() {
		BookingEditorDialog editorDialog = new BookingEditorDialog(this);
		editorDialog.setVisible(true);
		
		BookingModel model = editorDialog.getTypedModel();
		if (model == null) return;
		
		new BookingController().insert(model);
		this.updateBookingsTable();
	}
	
	private void updateBooking() {
		DefaultTableModel tableModel = (DefaultTableModel) tblBookings.getModel();
		
		int selectedRow = tblBookings.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva.", "Nenhuma reserva selecionada.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int modelRow = tblBookings.convertRowIndexToModel(selectedRow);
		int id = (int) tableModel.getValueAt(modelRow, 0);
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			BookingModel model = dao.select(id);
			System.out.println(model);
			
			BookingEditorDialog editorDialog = new BookingEditorDialog(this, model);
			editorDialog.setVisible(true);
			
			BookingModel typedModel = editorDialog.getTypedModel();
			if (typedModel == null) return; 
			dao.update(id, typedModel);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
		this.updateBookingsTable();
	}
	
	private void updateBookingsTable() {
		DefaultTableModel tableModel = (DefaultTableModel) this.tblBookings.getModel();
		tableModel.setRowCount(0);
		
		ArrayList<BookingModel> values = new BookingController().list();
		
		for (BookingModel model : values) {
			Object[] modelObject = this.bookingModelToObject(model);
			tableModel.addRow(modelObject);
		}
	}
	
	private void removeBooking() {
		DefaultTableModel tableModel = (DefaultTableModel) tblBookings.getModel();
		
		int selectedRow = tblBookings.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva.", "Nenhuma reserva selecionada.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int modelRow = tblBookings.convertRowIndexToModel(selectedRow);
		int id = (int) tableModel.getValueAt(modelRow, 0);
		
		try (Connection conn = ConnectionFactory.getConnection()) {
			
			BookingDAO dao = new BookingDAO(conn);
			dao.remove(id);
			
		} catch (SQLException err) {
			throw new RuntimeException(err);
		}
		
		this.updateBookingsTable();
	}

	private void useCurrentValueAsFilterOf(JTable table, JTextField filterField) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int selectedRow = table.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecione um item da tabela.", "Nenhum item selecionado.", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int modelRow = table.convertRowIndexToModel(selectedRow);
		int modelCol = table.convertColumnIndexToModel(table.getSelectedColumn());
		filterField.setText(tableModel.getValueAt(modelRow, modelCol).toString());
	}
}
