package stemmer.expressions;

import java.lang.reflect.Constructor;

import stemmer.Word;

public abstract class Expression {
	
	String source;
	
	public Expression(String source) {
		this.source = source;
	}
	
	public static Class<? extends Expression> getExpressionType(String source) {
		String s = source.substring(1, source.length()-1);
		String[] split = s.split(" ");
		if (s.charAt(0) == '&') {
			return AndExpression.class;
		} else if (s.charAt(0) == '|') {
			return OrExpression.class;
		} else if (s.charAt(0) == '!') {
			return NotExpression.class;
		} else if (s.charAt(0) == 'm') {
			return MCountExpression.class;
		} else if (split[0].equals("*d*")) {
			return DoubleConsonantExpression.class;
		} else if (split[0].matches("\\*[A-Z]+")) {
			return EndsInExpression.class;
		}
		System.out.println("Unknow expression in " + source);
		return Expression.class;
	}
	
	public static Constructor<? extends Expression> getConstructor(Class<? extends Expression> c) {
		Constructor<? extends Expression> constr = null;
		try {
			constr = c.getConstructor(String.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return constr;
	}
	
	public abstract boolean evaluate(Word w);
}
