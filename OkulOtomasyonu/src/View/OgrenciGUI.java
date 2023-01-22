package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Bolum;
import Model.Ogrenci;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class OgrenciGUI extends JFrame {

	private JPanel w_pane;
	private static Ogrenci ogrenci = new Ogrenci();
	private Bolum bolum = new Bolum();
	private JTable table_ogretmen;
	private DefaultTableModel ogretmenModel;
	private Object[] ogretmenData = null;
	private JTable table_whour;
	private Whour whour = new Whour();	
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectOgretmenID = 0;
	private String selectOgretmenName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OgrenciGUI frame = new OgrenciGUI(ogrenci);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public OgrenciGUI(Ogrenci ogrenci) throws SQLException {
		setTitle("Ders Randevu Sistemi");
		
		ogretmenModel = new DefaultTableModel();
		Object[] colOgretmen = new Object[2];
		colOgretmen[0] = "ID";
		colOgretmen[1] = "Ad Soyad";
		ogretmenModel.setColumnIdentifiers(colOgretmen);
		ogretmenData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Ogretmen";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for (int i = 0; i < appoint.getOgrenciList(ogrenci.getId()).size(); i++) {
			appointData[0] = appoint.getOgrenciList(ogrenci.getId()).get(i).getId();
			appointData[1] = appoint.getOgrenciList(ogrenci.getId()).get(i).getOgrenciName();
			appointData[2] = appoint.getOgrenciList(ogrenci.getId()).get(i).getAddDate();
			appointModel.addRow(appointData);
		}

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + ogrenci.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 248, 32);
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
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Ders Al", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Öğretmen Listesi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 11, 128, 32);
		w_appointment.add(lblNewLabel_1);
		
		JScrollPane w_scrollOgretmen = new JScrollPane();
		w_scrollOgretmen.setBounds(10, 43, 269, 292);
		w_appointment.add(w_scrollOgretmen);
		
		table_ogretmen = new JTable(ogretmenModel);
		w_scrollOgretmen.setViewportView(table_ogretmen);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bölüm Seç");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(289, 12, 100, 31);
		w_appointment.add(lblNewLabel_1_1);
		
		JComboBox select_bolum = new JComboBox();
		select_bolum.setBounds(289, 43, 128, 31);
		select_bolum.addItem("Bölüm Seç");
		for(int i = 0; i < bolum.getList().size(); i++) {
			select_bolum.addItem(new Item(bolum.getList().get(i).getId() , bolum.getList().get(i).getName()));
		}
		select_bolum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_bolum.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_ogretmen.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i = 0; i < bolum.getBolumOgretmenList(item.getKey()).size(); i++) {
							ogretmenData[0] = bolum.getBolumOgretmenList(item.getKey()).get(i).getId();
							ogretmenData[1] = bolum.getBolumOgretmenList(item.getKey()).get(i).getName();
							ogretmenModel.addRow(ogretmenData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) table_ogretmen.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_bolum);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Öğretmen Seç");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(289, 85, 105, 31);
		w_appointment.add(lblNewLabel_1_1_2);
		
		JButton btn_selOgretmen = new JButton("Seç");
		btn_selOgretmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_ogretmen.getSelectedRow();
				if(row >= 0) {
					String value = table_ogretmen.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					table_whour.setModel(whourModel);
					selectOgretmenID = id;
					selectOgretmenName = table_ogretmen.getModel().getValueAt(row, 1).toString();
					//System.out.println(selectOgretmenID + " - " + selectogretmenName);
					
				}else {
					Helper.showMsg("Lütfen bir öğretmen seçiniz!");
				}
						
			}
		});
		btn_selOgretmen.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_selOgretmen.setBounds(289, 116, 128, 31);
		w_appointment.add(btn_selOgretmen);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(430, 43, 269, 292);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JLabel lblNewLabel_1_2 = new JLabel("Öğretmen Listesi");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(430, 11, 128, 32);
		w_appointment.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Randevu Al");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2_1.setBounds(289, 158, 105, 31);
		w_appointment.add(lblNewLabel_1_1_2_1);
		
		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = ogrenci.addAppointment(selectOgretmenID, ogrenci.getId(), selectOgretmenName, ogrenci.getName(), date);
						if(control) {
							Helper.showMsg("success");
							ogrenci.updateWhourStatus(selectOgretmenID, date);
							updateWhourModel(selectOgretmenID);
							updateAppointModel(ogrenci.getId());
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz!");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_addAppoint.setBounds(289, 189, 128, 31);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(new Color(36, 123, 219));
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 11, 689, 324);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
	}
	
	public void updateWhourModel(int ogretmen_id) throws SQLException {
		DefaultTableModel clearmodel = (DefaultTableModel) table_whour.getModel();
		clearmodel.setRowCount(0);
		for(int i = 0; i < whour.getWhourList(ogretmen_id).size(); i++) {
			whourData[0] = whour.getWhourList(ogretmen_id).get(i).getId();
			whourData[1] = whour.getWhourList(ogretmen_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
	public void updateAppointModel(int ogrenci_id) throws SQLException {
		DefaultTableModel clearmodel = (DefaultTableModel) table_appoint.getModel();
		clearmodel.setRowCount(0);
		for (int i = 0; i < appoint.getOgrenciList(ogrenci_id).size(); i++) {
			appointData[0] = appoint.getOgrenciList(ogrenci_id).get(i).getId();
			appointData[1] = appoint.getOgrenciList(ogrenci_id).get(i).getOgretmenName();
			appointData[2] = appoint.getOgrenciList(ogrenci_id).get(i).getAddDate(); 
			appointModel.addRow(appointData);
		}
	}
}
