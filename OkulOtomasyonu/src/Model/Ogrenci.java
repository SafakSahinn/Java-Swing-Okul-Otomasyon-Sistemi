package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Ogrenci extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Ogrenci() {
		super();
	}

	public Ogrenci(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public boolean register(String tcno, String password, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");

			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu TC numarasına ait başka bir kayıt bulunmakta");
				break;
			}

			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "ogrenci");
				preparedStatement.executeUpdate();
				key = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean addAppointment(int ogretmen_id, int ogrenci_id, String ogretmen_name, String ogrenci_name,
			String appDate) throws SQLException {
		int key = 0;
		String query = "INSERT INTO appointment" + "(ogretmen_id,ogretmen_name,ogrenci_id,ogrenci_name,app_date) VALUES" + "(?,?,?,?,?)";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, ogretmen_id);
			preparedStatement.setString(2, ogretmen_name);
			preparedStatement.setInt(3, ogrenci_id);
			preparedStatement.setString(4, ogrenci_name);
			preparedStatement.setString(5, appDate);
			preparedStatement.executeUpdate();
			key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}
	
	public boolean updateWhourStatus(int ogretmen_id, String wdate) throws SQLException {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE ogretmen_id = ? AND wdate = ?";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, ogretmen_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;
	}
}
