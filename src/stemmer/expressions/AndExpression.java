package stemmer.expressions;

import stemmer.Word;

public class AndExpression extends Expression {

	public AndExpression(String Source) {
		super(Source);
	}
	
	public boolean evaluate(Word w) {
		return false;
	}
	
}
