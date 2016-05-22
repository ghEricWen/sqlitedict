package paul.sqlitedict.entity;

public final class Word {
	private final String word ;
	private final String type ;
	private final String definition ;
	
	public Word(String word,String type,String definition){
		this.word = word;
		this.type = type;
		this.definition = definition;
	}

	public String getWord() {
		return word;
	}

	public String getType() {
		return type;
	}

	public String getDefinition() {
		return definition;
	}
	
	@Override
	public String toString(){
		return word ;
	}
}
