package stemmer.expressions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import stemmer.Word;

public class AndExpression extends Expression {
	
	ArrayList<Expression> enclosed;
	
	public AndExpression(String source) {
		super(source);
		this.enclosed = new ArrayList<Expression>();
		String s = source.substring(3, source.length()-1);
		int start = 0;
		int stk = 0;
		for (int i = 0; i < s.length()+1; i++) {
			boolean onSpace = i == s.length();
			if (!onSpace) {
				char c = s.charAt(i);
				switch(c) {
				case ' ':
					onSpace = true;
					break;
				case '(':
					stk++;
					break;
				case ')':
					stk--;
					break;
				}
			}
			
			if (stk == 0 && onSpace) {
				String sub = s.substring(start, i);
				try {
					enclosed.add(Expression.getConstructor(Expression.getExpressionType(sub)).newInstance(sub));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
				start = i+1;
			}
		}
	}
	
	public boolean evaluate(Word w, Word wp) {
		for (Expression e : enclosed)
			if (!e.evaluate(w, wp)) return false;
		return true;
	}
	
}
