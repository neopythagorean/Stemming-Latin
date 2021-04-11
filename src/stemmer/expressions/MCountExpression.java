package stemmer.expressions;

import stemmer.Word;

public class MCountExpression extends Expression {

	public MCountExpression(String source) {
		super(source);
	}

	@Override
	public boolean evaluate(Word w) {
		return false;
	}

}
