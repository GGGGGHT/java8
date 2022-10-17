package com.ggggght.learningjava8.spel;

import java.util.GregorianCalendar;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spring expression language
 */
public class SpEL {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println("message = " + message);
        System.out.println(parser.parseExpression("'hello world'.bytes.length").getValue());
        System.out.println(parser.parseExpression("new String('hello world').toUpperCase()").getValue());

    }
}
