package stemmer.expressions;

import stemmer.Word;

public abstract class Expression {
	
	String source;
	
	public Expression(String Source) {
		this.source = source;
	}
	
	public abstract boolean evaluate(Word w);
}
