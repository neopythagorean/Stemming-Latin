package stemmer.expressions;

import java.util.ArrayList;

import stemmer.Word;

public class OrExpression extends Expression {
	
	ArrayList<Expression> enclosed;
	
	public OrExpression(String source) {
		super(source);
	}

	public boolean evaluate(Word w) {
		for (Expression e : enclosed)
			if (e.evaluate(w))
				return true;
		return false;
	}
}
