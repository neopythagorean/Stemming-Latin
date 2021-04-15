package stemmer.expressions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import stemmer.Word;

public class AndExpression extends Expression {
	
	ArrayList<Expression> enclosed;
	
	public AndExpression(String source) {
		super(source);
		String s = source.substring(3, source.length()-1);
		String[] subs = s.split(" ");
		for (String sub : subs) {
//			try {
//				enclosed.add(Expression.getConstructor(Expression.getExpressionType(source)).newInstance(sub));
//			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException e) {
//				e.printStackTrace();
//			}
		}
	}
	
	public boolean evaluate(Word w) {
		for (Expression e : enclosed)
			if (!e.evaluate(w)) return false;
		return true;
	}
	
}
