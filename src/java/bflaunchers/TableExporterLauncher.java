package bflaunchers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import wbflisteners.ObservableProcess;

public class TableExporterLauncher extends ObservableProcess {
	String userName;
	String password;
	String databaseFrom;
	String databaseTo;
	boolean overwrite;
	ArrayList<String> fromTables;
	ArrayList<String> toTables;
	
	TableExporterLauncher(String userName, String password, String databaseFrom, String databaseTo, boolean overwrite){
		this.userName=userName;
		this.password=password;
		this.databaseFrom=databaseFrom;
		this.databaseTo=databaseTo;
		this.overwrite=overwrite;
		this.fromTables=new ArrayList<String>();
		this.toTables=new ArrayList<String>();
	}
	

	public void getFromTables() throws ClassNotFoundException, SQLException{
		Statement st = DatabaseConnector.getConnection(this.userName,this.password).createStatement();
		String query = "show tables from "+this.databaseFrom;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			fromTables.add(rs.getString(1));
		}
	}

	public void getToTables() throws ClassNotFoundException, SQLException{
		Statement st = DatabaseConnector.getConnection(this.userName,this.password).createStatement();
		String query = "show tables from "+this.databaseTo;
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			toTables.add(rs.getString(1));
		}
	}
	
	public void launch(String tableFrom, String tableTo) throws ClassNotFoundException, SQLException{
		this.launch(this.userName, this.password, this.databaseFrom, tableFrom, this.databaseTo, tableTo, this.overwrite);
	}
	
	public void launch (String user, String pass, String dbFrom, String tableFrom, String dbTo, String tableTo, boolean overwrite) throws ClassNotFoundException, SQLException{
		Statement st = DatabaseConnector.getConnection(this.userName,this.password).createStatement();
		if(overwrite){
			String drop = "DROP TABLE IF EXISTS "+dbTo+"."+tableTo;
			System.out.println(drop);
			st.executeUpdate(drop);
		}
		this.getFromTables();
		this.getToTables();
		if(fromTables.contains(tableFrom) & !toTables.contains(tableTo)){
			String rename = "RENAME TABLE "+dbFrom+"."+tableFrom+" TO "+dbTo+"."+tableTo;
			System.out.println(rename);
			st.execute(rename);
		}
	}
}
