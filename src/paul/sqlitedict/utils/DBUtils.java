package paul.sqlitedict.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
	
	private final String url = "jdbc:sqlite:dict.db";
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	
	private DBUtils(){}
	
	private static DBUtils instance = null;
	
	public static DBUtils instance(){
		if(instance == null){
			synchronized(DBUtils.class){
				if(instance == null)
					instance = new DBUtils();
			}
		}
		return instance;
	}
	
	public List<Map<String,String>> executeQuery(String sql,Object... params){
		List<Map<String,String>> entries = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;				
		try {
			conn = DriverManager.getConnection(url);
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();						 
			while(rs.next()){
				Map<String,String> row = new HashMap<>();				
				ResultSetMetaData rsmd = rs.getMetaData();				
				for(int i = 1 ;i <= rsmd.getColumnCount() ;i++){
					row.put(rsmd.getColumnName(i) , (String) rs.getObject(i));
				}
				entries.add(row);
			}
		} catch (SQLException e) {			
			System.out.println(e.getMessage());
		} finally{
			try{
				rs.close();
				ps.close();
				conn.close();
			} catch(SQLException e){
				System.out.println(e.getMessage());				
			}			
			
		}		
		return entries;
	}
	
	
}
