package stemmer.expressions;

import stemmer.Word;

public class NotExpression extends Expression {
	
	Expression enclosed;
	
	public NotExpression(String source) {
		super(source);
	}
	
	public boolean evaluate(Word w) {
		return !(enclosed.evaluate(w));
	}

}
