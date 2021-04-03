package stemmer.expressions;

import stemmer.Word;

public class OrExpression extends Expression {

	public OrExpression(String Source) {
		super(Source);
	}

	public boolean evaluate(Word w) {
		return false;
	}

}
