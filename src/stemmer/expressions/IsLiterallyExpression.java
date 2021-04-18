package stemmer.expressions;

import stemmer.Word;

public class IsLiterallyExpression  extends Expression {

	public IsLiterallyExpression(String source) {
		super(source);
	}
	
	public boolean evaluate(Word w, Word wp) {
		return wp.getRawString().equals("");
	}
	

}
