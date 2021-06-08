package com.ggggght.learningjava8.gof23_lambda;

/**
 * 使用lambda优化策略模式的实现
 */
public class Strategy {
	private final ValidationStrategy strategy;

	public Strategy(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean validate(String s) {
		return strategy.execute(s);
	}

	public static void main(String[] args) {
		Strategy strategy = new Strategy(new IsNumeric());
		System.out.println("strategy.validate(\"aaa\") = " + strategy.validate("aaa"));
		strategy = new Strategy(new IsAllLowerCase());
		System.out.println("strategy.validate(\"bbb\") = " + strategy.validate("bbb"));
	}
}

class IsAllLowerCase implements ValidationStrategy {
	@Override
	public boolean execute(String s) {
		return s.matches("[a-z]+");
	}
}

class IsNumeric implements ValidationStrategy {

	@Override
	public boolean execute(String s) {
		return s.matches("\\d+");
	}
}

@FunctionalInterface
interface ValidationStrategy {
	boolean execute(String s);
}