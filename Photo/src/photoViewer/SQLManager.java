package photoViewer;

import java.sql.*;

import javax.swing.ImageIcon;

import java.io.*;

public class SQLManager {

	private int photoNumber;
	private Connection con;
	private Statement stmt;

	public SQLManager() throws SQLException {
		
		String url = getURL();
		String userID = getUserName();
		String password = getPassword();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userID, password);
		}
		catch (java.lang.ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		stmt = con.createStatement();
		createTables();
		
	}
		
	public int getTotalNumbPhoto() throws SQLException{
		int rowCount=0;
	   try{
		String query = "SELECT id from photoviewer";  
		ResultSet rs = stmt.executeQuery(query);
	      while (rs.next()) {
	        String id = rs.getString(1);
	       }
	      rs.last();
	      rowCount = rs.getRow();
	      
	      
	}
	   catch(SQLException e1){
		   System.out.println(e1);
	   }
	 System.out.println("Number of Rows=" + rowCount);
	return rowCount;
	}
	
	public void cleanup() throws SQLException {
		stmt.close();
		con.close();
	}

	public void createTables() throws SQLException {

		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet rslt = dbmd.getTables(null, null, "photoviewer", null);
		if (!rslt.next()) {
			String sqlcmd = "CREATE TABLE PhotoViewer" + "(ID INT NOT NULL," + "PhotoNumber INT NOT NULL,"
					+ "Description VARCHAR (254)," + "Date DATE," + "Photo LONG VARBINARY)";
			stmt.executeUpdate(sqlcmd);
		}
		
	}

	public Photo Getcurrentphoto() {
		int c;
		Photo temp = new Photo(null, null, null);
		try {
			ResultSet tempRS = stmt.executeQuery(
					"SELECT photo, description," + " date FROM PhotoViewer WHERE ID = " + (photoNumber) + ";");
			boolean there = tempRS.next();
			if (there) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				InputStream in = tempRS.getBinaryStream("photo");

				while ((c = in.read()) != -1)
					bos.write(c);
				byte[] rawimage = null;
				rawimage = bos.toByteArray();
				temp.image = new ImageIcon(rawimage);
				temp.description = new String(tempRS.getString(2));
				temp.date = new String(tempRS.getString(3));
			} else {
				Photo tempnull = null;
				return (tempnull);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return temp;
	}

	public Photo Getphoto(int a) {
		photoNumber = a;
		return Getcurrentphoto();
	}

	public Photo Previous() {
		photoNumber--;
		return Getcurrentphoto();
	}

	public Photo Next() {
		photoNumber++;
		return Getcurrentphoto();
	}

	public Photo Deletecurrent() {
		try {
			stmt.executeUpdate("delete from PhotoViewer where id = " + photoNumber + ";");

			stmt.executeUpdate("update PhotoViewer set id = id - 1 where id > " + photoNumber + ";");
			stmt.executeUpdate("update location set total = total - 1 ;");
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		int currenttotal = 0;
		try {
			ResultSet tempRS = stmt.executeQuery("select total from location;");
			if (tempRS.next()) {
				currenttotal = tempRS.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		if (photoNumber > currenttotal) {
			photoNumber = currenttotal;
		}
		return Getcurrentphoto();
	}

	public int Getcurrentphotonumber() {
		return (photoNumber);
	}

	public void Addphoto(byte[] data, String desc, String date) {
		String sql = "insert into PhotoViewer (id, description, date, photo) " + "values (?,?,?,?)";
		java.sql.PreparedStatement pstmt = null;
		try {

			stmt.executeUpdate("update PhotoViewer set id = id + 1 where id > " + photoNumber + ";");

			pstmt = con.prepareStatement(sql);
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			pstmt.setBinaryStream(4, bis, (int) data.length);
			pstmt.setInt(1, (photoNumber + 1));
			pstmt.setString(2, desc);
			pstmt.setString(3, date);
			pstmt.executeUpdate();
			pstmt.close();
			int num = 0;
			ResultSet tempRS = stmt.executeQuery("SELECT total FROM location;");
			boolean there = (tempRS.next());
			if (there) {
				num = tempRS.getInt(1);
			}
			stmt.executeUpdate("update location set total = " + (num + 1) + ";");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void EditPhoto(String desc, String DATE) {
		System.out.println(desc);
		System.out.println(DATE);
		System.out.println(photoNumber);
		try {
			if (desc != "") {
				stmt.executeUpdate(
						"UPDATE PhotoViewer SET description = '" + desc + "' WHERE id = " + (photoNumber) + ";");
			}
			if (DATE != "") {
				stmt.executeUpdate("UPDATE PhotoViewer SET date = '" + DATE + "' WHERE id = " + (photoNumber) + ";");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	protected static String getURL() {
		return ("jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/wspn8c");
	}

	protected static String getUserName() {
		return ("wspn8c");
	}

	protected static String getPassword() {
		return ("QbZhr7gEjG");
	}
}
