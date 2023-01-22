package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Whour {
	private int id,ogretmen_id;
	private String ogretmen_name,wdate,status;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Whour(int id, int ogretmen_id, String ogretmen_name, String wdate, String status) {
		this.id = id;
		this.ogretmen_id = ogretmen_id;
		this.ogretmen_name = ogretmen_name;
		this.wdate = wdate;
		this.status = status;
	}
	
	public Whour() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOgretmen_id() {
		return ogretmen_id;
	}

	public void setOgretmen_id(int ogretmen_id) {
		this.ogretmen_id = ogretmen_id;
	}

	public String getOgretmen_name() {
		return ogretmen_name;
	}

	public void setOgretmen_name(String ogretmen_name) {
		this.ogretmen_name = ogretmen_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public ArrayList<Whour> getWhourList(int ogretmen_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		try {
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND ogretmen_id = " + ogretmen_id);
			while (rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setOgretmen_id(rs.getInt("ogretmen_id"));
				obj.setOgretmen_name(rs.getString("ogretmen_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return list;
	}
	
	
}
