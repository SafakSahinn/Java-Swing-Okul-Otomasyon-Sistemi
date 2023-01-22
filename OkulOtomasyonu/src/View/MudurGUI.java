package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.JComboBox;

public class MudurGUI extends JFrame {

	static Mudur mudur = new Mudur();
	Bolum bolum = new Bolum();
	private JPanel w_pane;
	private JTextField fld_ogretName;
	private JTextField fld_ogretTcno;
	private JPasswordField fld_ogretPass;
	private JTable table_ogretmen;
	private DefaultTableModel ogretmenModel = null;
	private Object[] ogretmenData = null;
	private JTextField fld_ogretID;
	private JTable table_bolum;
	private JTextField fld_bolumName;
	private DefaultTableModel bolumModel = null;
	private Object[] bolumData = null;
	private JPopupMenu bolumMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MudurGUI frame = new MudurGUI(mudur);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public MudurGUI(Mudur mudur) throws SQLException {
		setTitle("Ders Randevu Sistemi");

		// öğretmen modeli
		ogretmenModel = new DefaultTableModel();
		Object[] colOgretmenName = new Object[4];
		colOgretmenName[0] = "ID";
		colOgretmenName[1] = "Ad Soyad";
		colOgretmenName[2] = "TC NO";
		colOgretmenName[3] = "Şifre";
		ogretmenModel.setColumnIdentifiers(colOgretmenName);
		ogretmenData = new Object[4];
		for (int i = 0; i < mudur.getOgretmenList().size(); i++) {
			ogretmenData[0] = mudur.getOgretmenList().get(i).getId();
			ogretmenData[1] = mudur.getOgretmenList().get(i).getName();
			ogretmenData[2] = mudur.getOgretmenList().get(i).getTcno();
			ogretmenData[3] = mudur.getOgretmenList().get(i).getPassword();
			ogretmenModel.addRow(ogretmenData);
		}

		// bölüm modeli
		bolumModel = new DefaultTableModel();
		Object[] colBolum = new Object[2];
		colBolum[0] = "ID";
		colBolum[1] = "Ad Soyad";
		bolumModel.setColumnIdentifiers(colBolum);
		bolumData = new Object[2];
		for (int i = 0; i < bolum.getList().size(); i++) {
			bolumData[0] = bolum.getList().get(i).getId();
			bolumData[1] = bolum.getList().get(i).getName();
			bolumModel.addRow(bolumData);
		}

		// worker modeli
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 226, 32);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(581, 13, 143, 32);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 76, 714, 374);
		w_pane.add(w_tab);

		JPanel w_ogretmen = new JPanel();
		w_ogretmen.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Öğretmen Yönetimi", null, w_ogretmen, null);
		w_ogretmen.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Ad Soyad");
		lblNewLabel_1_1.setBounds(562, 0, 100, 31);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		w_ogretmen.add(lblNewLabel_1_1);

		fld_ogretName = new JTextField();
		fld_ogretName.setBounds(562, 29, 137, 31);
		fld_ogretName.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogretName.setColumns(10);
		w_ogretmen.add(fld_ogretName);

		JLabel lblNewLabel_1_1_1 = new JLabel("T.C Numarası");
		lblNewLabel_1_1_1.setBounds(562, 59, 100, 31);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		w_ogretmen.add(lblNewLabel_1_1_1);

		fld_ogretTcno = new JTextField();
		fld_ogretTcno.setBounds(562, 87, 137, 31);
		fld_ogretTcno.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogretTcno.setColumns(10);
		w_ogretmen.add(fld_ogretTcno);

		JLabel lblNewLabel_1_1_2 = new JLabel("Şifre");
		lblNewLabel_1_1_2.setBounds(562, 117, 100, 31);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		w_ogretmen.add(lblNewLabel_1_1_2);

		fld_ogretPass = new JPasswordField();
		fld_ogretPass.setBounds(562, 146, 137, 31);
		w_ogretmen.add(fld_ogretPass);

		JButton btn_addOgret = new JButton("Ekle");
		btn_addOgret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_ogretName.getText().length() == 0 || fld_ogretPass.getText().length() == 0
						|| fld_ogretTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = mudur.addOgretmen(fld_ogretTcno.getText(), fld_ogretPass.getText(),
								fld_ogretName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_ogretName.setText(null);
							fld_ogretTcno.setText(null);
							fld_ogretPass.setText(null);
							updateOgretmenModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addOgret.setBounds(562, 188, 137, 31);
		btn_addOgret.setFont(new Font("Tahoma", Font.BOLD, 15));
		w_ogretmen.add(btn_addOgret);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_1_2_1.setBounds(562, 230, 100, 31);
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		w_ogretmen.add(lblNewLabel_1_1_2_1);

		JButton btn_delOgret = new JButton("Sil");
		btn_delOgret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_ogretID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir öğretmen seçiniz!");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_ogretID.getText());
						try {
							boolean control = mudur.deleteOgretmen(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_ogretID.setText(null);
								updateOgretmenModel();
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delOgret.setBounds(562, 303, 137, 31);
		btn_delOgret.setFont(new Font("Tahoma", Font.BOLD, 15));
		w_ogretmen.add(btn_delOgret);

		JScrollPane w_scrollOgretmen = new JScrollPane();
		w_scrollOgretmen.setBounds(10, 11, 542, 323);
		w_ogretmen.add(w_scrollOgretmen);

		table_ogretmen = new JTable(ogretmenModel);
		w_scrollOgretmen.setViewportView(table_ogretmen);

		fld_ogretID = new JTextField();
		fld_ogretID.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogretID.setColumns(10);
		fld_ogretID.setBounds(562, 261, 137, 31);
		w_ogretmen.add(fld_ogretID);

		table_ogretmen.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_ogretID.setText(table_ogretmen.getValueAt(table_ogretmen.getSelectedRow(), 0).toString());

				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
		});

		table_ogretmen.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_ogretmen.getValueAt(table_ogretmen.getSelectedRow(), 0).toString());
					String selectName = table_ogretmen.getValueAt(table_ogretmen.getSelectedRow(), 1).toString();
					String selectTcno = table_ogretmen.getValueAt(table_ogretmen.getSelectedRow(), 2).toString();
					String selectPass = table_ogretmen.getValueAt(table_ogretmen.getSelectedRow(), 3).toString();

					try {
						boolean control = mudur.updateOgretmen(selectID, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel w_bolum = new JPanel();
		w_bolum.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Bölümler", null, w_bolum, null);
		w_bolum.setLayout(null);

		JScrollPane scroll_bolumler = new JScrollPane();
		scroll_bolumler.setBounds(10, 11, 250, 324);
		w_bolum.add(scroll_bolumler);

		bolumMenu = new JPopupMenu(); // popup menü
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		bolumMenu.add(updateMenu);
		bolumMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_bolum.getValueAt(table_bolum.getSelectedRow(), 0).toString());
				Bolum selectBolum = bolum.getFetch(selID);
				UpdateBolumGUI updateGUI = new UpdateBolumGUI(selectBolum);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateBolumModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure"))
					;
				int selID = Integer.parseInt(table_bolum.getValueAt(table_bolum.getSelectedRow(), 0).toString());
				try {
					if (bolum.deleteBolum(selID)) {
						Helper.showMsg("success");
						updateBolumModel();
					} else {
						Helper.showMsg("error");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		table_bolum = new JTable(bolumModel);
		table_bolum.setComponentPopupMenu(bolumMenu);
		table_bolum.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_bolum.rowAtPoint(point);
				table_bolum.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});
		scroll_bolumler.setViewportView(table_bolum);

		JLabel lblNewLabel_1_1_3 = new JLabel("Bölüm Adı");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_3.setBounds(270, 11, 100, 31);
		w_bolum.add(lblNewLabel_1_1_3);

		fld_bolumName = new JTextField();
		fld_bolumName.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_bolumName.setColumns(10);
		fld_bolumName.setBounds(270, 40, 169, 31);
		w_bolum.add(fld_bolumName);

		JButton btn_addBolum = new JButton("Ekle");
		btn_addBolum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_bolumName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (bolum.addBolum(fld_bolumName.getText())) {
							Helper.showMsg("success");
							fld_bolumName.setText(null);
							updateBolumModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addBolum.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_addBolum.setBounds(270, 82, 169, 31);
		w_bolum.add(btn_addBolum);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(449, 11, 250, 324);
		w_bolum.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JComboBox select_ogretmen = new JComboBox();
		select_ogretmen.setBounds(270, 262, 169, 31);
		for (int i = 0; i < mudur.getOgretmenList().size(); i++) {
			select_ogretmen.addItem(
					new Item(mudur.getOgretmenList().get(i).getId(), mudur.getOgretmenList().get(i).getName()));
		}
		select_ogretmen.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			// System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_bolum.add(select_ogretmen);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_bolum.getSelectedRow();
				if (selRow >= 0) {
					String selBolum = table_bolum.getModel().getValueAt(selRow, 0).toString();
					int selBolumID = Integer.parseInt(selBolum);
					Item ogretmenItem = (Item) select_ogretmen.getSelectedItem();
					try {
						boolean control = mudur.addWorker(ogretmenItem.getKey(), selBolumID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < mudur.getBolumOgretmenList(selBolumID).size(); i++) {
								workerData[0] = mudur.getBolumOgretmenList(selBolumID).get(i).getId();
								workerData[1] = mudur.getBolumOgretmenList(selBolumID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen bir bölüm seçiniz!");
				}
			}
		});
		btn_addWorker.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_addWorker.setBounds(270, 304, 169, 31);
		w_bolum.add(btn_addWorker);

		JLabel lblNewLabel_1_1_3_1 = new JLabel("Bölüm Adı");
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_3_1.setBounds(270, 124, 100, 31);
		w_bolum.add(lblNewLabel_1_1_3_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_bolum.getSelectedRow();
				if (selRow >= 0) {
					String selBolum = table_bolum.getModel().getValueAt(selRow, 0).toString();
					int selBolumID = Integer.parseInt(selBolum);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < mudur.getBolumOgretmenList(selBolumID).size(); i++) {
							workerData[0] = mudur.getBolumOgretmenList(selBolumID).get(i).getId();
							workerData[1] = mudur.getBolumOgretmenList(selBolumID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen bir bölüm seçiniz!");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_workerSelect.setBounds(270, 157, 169, 31);
		w_bolum.add(btn_workerSelect);
	}

	public void updateOgretmenModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_ogretmen.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < mudur.getOgretmenList().size(); i++) {
			ogretmenData[0] = mudur.getOgretmenList().get(i).getId();
			ogretmenData[1] = mudur.getOgretmenList().get(i).getName();
			ogretmenData[2] = mudur.getOgretmenList().get(i).getTcno();
			ogretmenData[3] = mudur.getOgretmenList().get(i).getPassword();
			ogretmenModel.addRow(ogretmenData);
		}
	}

	public void updateBolumModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_bolum.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bolum.getList().size(); i++) {
			bolumData[0] = bolum.getList().get(i).getId();
			bolumData[1] = bolum.getList().get(i).getName();
			bolumModel.addRow(bolumData);
		}
	}
}
