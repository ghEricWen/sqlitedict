package paul.sqlitedict.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import paul.sqlitedict.entity.Word;
import paul.sqlitedict.utils.DBUtils;

public class WordDao {
	
	private static WordDao instance = new WordDao();
	
	private WordDao(){}
	
	public static WordDao instance(){
		
		if(instance == null){
			synchronized(WordDao.class){
				if(instance == null)
					instance = new WordDao();
			}
		}		
		return instance;
	}
	
	public List<Word> accurateSearch(String word,String table){
		List<Word> words = new ArrayList<>();
		String sql = "select word,type,definition from "+ table +" where word = " + word;		
		List<Map<String,String>> res = DBUtils.instance().executeQuery(sql);
		for(Map<String,String> row : res){
			Word w = new Word(row.get("word"),
					row.get("type"),row.get("desc"));
			words.add(w);
		}
		return words;
	}
	
	public List<Word> vagueSearch(String word,String table){
		List<Word> words = new ArrayList<>();
		String sql = "select word,type,desc from " + table + " where word like '" + word +"%'";		
		List<Map<String,String>> res = DBUtils.instance().executeQuery(sql);
		int count = 0;
		for(Map<String,String> row : res){
			Word w = new Word(row.get("word"),
					row.get("type"),row.get("desc"));			
			if(count < 10) 
				count ++;
			else
				break;
			words.add(w);
		}
		return words;
	}
	
}
