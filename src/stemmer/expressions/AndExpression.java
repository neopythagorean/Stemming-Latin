package stemmer.expressions;

import java.util.ArrayList;

import stemmer.Word;

public class AndExpression extends Expression {
	
	ArrayList<Expression> enclosed;
	
	public AndExpression(String source) {
		super(source);
	}
	
	public boolean evaluate(Word w) {
		for (Expression e : enclosed)
			if (!e.evaluate(w)) return false;
		return true;
	}
	
}
