package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.*;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateBolumGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_bolumName;
	private static Bolum bolum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBolumGUI frame = new UpdateBolumGUI(bolum);
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
	public UpdateBolumGUI(Bolum bolum) {
		setTitle("Düzenleme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fld_bolumName = new JTextField();
		fld_bolumName.setFont(new Font("Tahoma", Font.BOLD, 15));
		fld_bolumName.setColumns(10);
		fld_bolumName.setBounds(10, 29, 189, 31);
		fld_bolumName.setText(bolum.getName());
		contentPane.add(fld_bolumName);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Bölüm Adı");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_3.setBounds(10, 0, 100, 31);
		contentPane.add(lblNewLabel_1_1_3);
		
		JButton btn_updateBolum = new JButton("Düzenle");
		btn_updateBolum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						bolum.updateBolum(bolum.getId(), fld_bolumName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateBolum.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_updateBolum.setBounds(10, 71, 189, 31);
		contentPane.add(btn_updateBolum);
	}
}
