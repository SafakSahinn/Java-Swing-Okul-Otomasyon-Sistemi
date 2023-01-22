package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id,ogretmenID,ogrenciID;
	private String ogretmenName,ogrenciName,addDate;
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Appointment(int id, int ogretmenID, int ogrenciID, String ogretmenName, String ogrenciName, String addDate) {
		super();
		this.id = id;
		this.ogretmenID = ogretmenID;
		this.ogrenciID = ogrenciID;
		this.ogretmenName = ogretmenName;
		this.ogrenciName = ogrenciName;
		this.addDate = addDate;
	}

	public Appointment() {

	}
	
	public ArrayList<Appointment> getOgrenciList(int ogrenci_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE ogrenci_id = " + ogrenci_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setOgretmenID(rs.getInt("ogretmen_id"));
				obj.setOgretmenName(rs.getString("ogretmen_name"));
				obj.setOgrenciID(rs.getInt("ogrenci_id"));
				obj.setOgrenciName(rs.getString("ogrenci_name"));
				obj.setAddDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}
	
	public ArrayList<Appointment> getOgretmenList(int ogretmen_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE ogretmen_id = " + ogretmen_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setOgretmenID(rs.getInt("ogretmen_id"));
				obj.setOgretmenName(rs.getString("ogretmen_name"));
				obj.setOgrenciID(rs.getInt("ogrenci_id"));
				obj.setOgrenciName(rs.getString("ogrenci_name"));
				obj.setAddDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOgretmenID() {
		return ogretmenID;
	}

	public void setOgretmenID(int ogretmenID) {
		this.ogretmenID = ogretmenID;
	}

	public int getOgrenciID() {
		return ogrenciID;
	}

	public void setOgrenciID(int ogrenciID) {
		this.ogrenciID = ogrenciID;
	}

	public String getOgretmenName() {
		return ogretmenName;
	}

	public void setOgretmenName(String ogretmenName) {
		this.ogretmenName = ogretmenName;
	}

	public String getOgrenciName() {
		return ogrenciName;
	}

	public void setOgrenciName(String ogrenciName) {
		this.ogrenciName = ogrenciName;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	
	
}
