package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Helper;
import Model.Mudur;
import Model.Ogrenci;
import Model.Ogretmen;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_ogrenciTc;
	private JPasswordField fld_ogrenciPass;
	private JTextField fld_ogretmenTc;
	private JPasswordField fld_ogretmenPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Ders Randevu Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DERS RANDEVU SİSTEMİ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(122, 82, 263, 48);
		w_pane.add(lblNewLabel_1);
		
		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setBounds(10, 164, 464, 186);
		w_pane.add(w_tabPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		w_tabPane.addTab("Öğrenci Girişi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C Numarası");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 11, 100, 31);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(73, 53, 37, 31);
		panel.add(lblNewLabel_1_1_1);
		
		fld_ogrenciTc = new JTextField();
		fld_ogrenciTc.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogrenciTc.setBounds(120, 13, 255, 31);
		panel.add(fld_ogrenciTc);
		fld_ogrenciTc.setColumns(10);
		
		fld_ogrenciPass = new JPasswordField();
		fld_ogrenciPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogrenciPass.setBounds(120, 55, 255, 31);
		panel.add(fld_ogrenciPass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_register.setBounds(83, 107, 141, 40);
		panel.add(btn_register);
		
		JButton btn_ogrenciLogin = new JButton("Giriş Yap");
		btn_ogrenciLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_ogrenciTc.getText().length()== 0 || fld_ogrenciPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_ogrenciTc.getText().equals(rs.getString("tcno")) && fld_ogrenciPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("ogrenci")) {
									Ogrenci ogrenci = new Ogrenci();
									ogrenci.setId(rs.getInt("id"));
									ogrenci.setPassword("password");
									ogrenci.setTcno(rs.getString("tcno"));
									ogrenci.setName(rs.getString("name"));
									ogrenci.setType(rs.getString("type"));
									OgrenciGUI oGUI = new OgrenciGUI(ogrenci);
									oGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(key) {
						Helper.showMsg("Böyle bir öğrenci bulunamadı, lütfen kayıt olunuz!");
					}
				}
			}
		});
		btn_ogrenciLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_ogrenciLogin.setBounds(234, 107, 141, 40);
		panel.add(btn_ogrenciLogin);
		
		JPanel w_ogretmenLogin = new JPanel();
		w_ogretmenLogin.setBackground(new Color(255, 255, 255));
		w_tabPane.addTab("Öğretmen Girişi", null, w_ogretmenLogin, null);
		w_ogretmenLogin.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("T.C Numarası");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_2.setBounds(10, 11, 100, 31);
		w_ogretmenLogin.add(lblNewLabel_1_1_2);
		
		fld_ogretmenTc = new JTextField();
		fld_ogretmenTc.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogretmenTc.setColumns(10);
		fld_ogretmenTc.setBounds(120, 13, 255, 31);
		w_ogretmenLogin.add(fld_ogretmenTc);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Şifre");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(73, 53, 37, 31);
		w_ogretmenLogin.add(lblNewLabel_1_1_1_1);
		
		fld_ogretmenPass = new JPasswordField();
		fld_ogretmenPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_ogretmenPass.setBounds(120, 55, 255, 31);
		w_ogretmenLogin.add(fld_ogretmenPass);
		
		JButton btn_ogretmenLogin = new JButton("Giriş Yap");
		btn_ogretmenLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_ogretmenTc.getText().length() == 0 || fld_ogretmenPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM USER");
						while(rs.next()) {
							if(fld_ogretmenTc.getText().equals(rs.getString("tcno")) && fld_ogretmenPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("mudur")) {
									Mudur mudur = new Mudur();
									mudur.setId(rs.getInt("id"));
									mudur.setPassword("password");
									mudur.setTcno(rs.getString("tcno"));
									mudur.setName(rs.getString("name"));
									mudur.setType(rs.getString("type"));
									MudurGUI mGUI = new MudurGUI(mudur);
									mGUI.setVisible(true);
									dispose();
								}
								
								if(rs.getString("type").equals("ogretmen")) {
									Ogretmen ogretmen = new Ogretmen();
									ogretmen.setId(rs.getInt("id"));
									ogretmen.setPassword("password");
									ogretmen.setTcno(rs.getString("tcno"));
									ogretmen.setName(rs.getString("name"));
									ogretmen.setType(rs.getString("type"));
									OgretmenGUI oGUI = new OgretmenGUI(ogretmen);
									oGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_ogretmenLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_ogretmenLogin.setBounds(73, 107, 302, 40);
		w_ogretmenLogin.add(btn_ogretmenLogin);
	}
}
