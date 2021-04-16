package stemmer.expressions;

import stemmer.Word;

public class EndsInExpression extends Expression {

	String ending;
	
	public EndsInExpression(String source) {
		super(source);
		this.ending = source.substring(2, source.length()-1);
	}

	public boolean evaluate(Word w, Word wp) {
		return wp.endsIn(ending);
	}
}
