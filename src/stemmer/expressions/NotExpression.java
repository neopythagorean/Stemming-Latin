package stemmer.expressions;

import stemmer.Word;

public class NotExpression extends Expression {
	
	Expression enclosed;
	
	public NotExpression(String Source) {
		super(Source);
	}
	
	public boolean evaluate(Word w) {
		return !(enclosed.evaluate(w));
	}

}
