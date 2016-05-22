package paul.sqlitedict.service;

import java.util.List;

import paul.sqlitedict.dao.WordDao;
import paul.sqlitedict.entity.Word;

public class WordService {
	private WordService(){}
	
	private static WordService instance = null;
	
	public static WordService instance(){
		if(instance == null){
			synchronized(WordService.class){
				if(instance == null)
					instance = new WordService();
			}
		}	
		return instance;
	}
	
	public List<Word> search(String word){
		if(word == null || word.isEmpty())
			throw new NullPointerException();
		String table = word.substring(0,1).toUpperCase();
		List<Word> words = WordDao.instance().vagueSearch(word, table);
		return words;
	}
}
