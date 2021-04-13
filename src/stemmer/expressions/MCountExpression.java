package stemmer.expressions;

import stemmer.Word;

public class MCountExpression extends Expression {

	static interface ComparisonFunc {boolean compare(int i, int j);}
	static boolean eq(int i, int j)		{return i == j;}
	static boolean lteq(int i, int j)	{return i <= j;}
	static boolean gteq(int i, int j)	{return i >= j;}
	static boolean lt(int i, int j)		{return i <  j;}
	static boolean gt(int i, int j)		{return i >  j;}
	
	public ComparisonFunc c;
	public int constant;
	
	public MCountExpression(String source) {
		super(source);
		String s = source.substring(2, source.length()-1);
		int cutoff = 1;
		if(s.startsWith("=")) {
			c = MCountExpression::eq;
		} else if(s.startsWith("<=")) {
			c = MCountExpression::lteq;
			cutoff++;
		} else if(s.startsWith(">=")) {
			c = MCountExpression::gteq;
			cutoff++;
		} else if(s.startsWith("<")) {
			c = MCountExpression::lteq;
		} else if(s.startsWith(">")) {
			c = MCountExpression::eq;
		} else {
			System.out.println("Unkown equality in " + source);
		}
		s = s.substring(cutoff);
		constant = Integer.parseInt(s);
	}
	
	
	public boolean evaluate(Word w) {
		return c.compare(w.getM(), constant);
	}

}
