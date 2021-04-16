package stemmer.expressions;

import stemmer.Word;

public class EndsInVowelExpression extends Expression {

	public EndsInVowelExpression(String Source) {
		super(Source);
	}
	
	public boolean evaluate(Word w, Word wp) {
		return Word.isVowel(wp.getRawString().charAt(wp.getRawString().length()-1));
	}
	

}
