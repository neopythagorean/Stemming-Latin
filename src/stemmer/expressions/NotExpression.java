package stemmer.expressions;

import java.lang.reflect.InvocationTargetException;

import stemmer.Word;

public class NotExpression extends Expression {
	
	Expression enclosed;
	
	public NotExpression(String source) {
		super(source);
		String s = source.substring(3, source.length()-1);
		try {
			enclosed = Expression.getConstructor(Expression.getExpressionType(s)).newInstance(s);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean evaluate(Word w) {
		return !(enclosed.evaluate(w));
	}

}
