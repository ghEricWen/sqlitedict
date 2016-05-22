package paul.sqlitedict.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import paul.sqlitedict.dao.WordDao;
import paul.sqlitedict.entity.Word;

public class DriverTest {
	@Test
	public void dt() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:dict.db");
		System.out.println(conn);
		conn.close();
	}
	
	@Test
	public void findTest(){
		List<Word> list = WordDao.instance().vagueSearch("lo", "L");
		for(Word w : list){
			System.out.println(w);
		}
	}
}
