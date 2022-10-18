package com.ggggght.learningjava8.spel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spring expression language
 */
public class SpEL {
    public static void main(String[] args) throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println("message = " + message);
        System.out.println(parser.parseExpression("'hello world'.bytes.length").getValue());
        System.out.println(parser.parseExpression("new String('hello world').toUpperCase()").getValue());

        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        //  The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        tesla.setPlaceOfBirth(new PlaceOfBirth("Beijing", "China"));
        // System.out.println("parser.parseExpression(\"name\") = " + parser.parseExpression("name").getValue());
        StandardEvaluationContext context = new StandardEvaluationContext(tesla);
        String name = (String) exp.getValue(tesla);
        System.out.println("name = " + name);
        int year = (Integer) parser.parseExpression("1900 + Birthdate.Year").getValue(context);
        System.out.println("year = " + year);
        String city = (String) parser.parseExpression("PlaceOfBirth.City").getValue(context);
        System.out.println("city = " + city);

        Simple simple = new Simple();

        simple.booleanList.add(true);

        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);

        // false is passed in here as a string.  SpEL and the conversion service will
        // correctly recognize that it needs to be a Boolean and convert it
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");

        // b will be false
        Boolean b = simple.booleanList.get(0);
        System.out.println("b = " + b);

        Object nullValue = parser.parseExpression("null").getValue();
        System.out.println("nullValue = " + nullValue);

        System.out.println(parser.parseExpression("{1,2,3,4}").getValue(context));
        System.out.println((List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context));

        // string literal, evaluates to "bc"
        System.out.println(parser.parseExpression("'abc'.substring(2, 3)").getValue(String.class));

        // evaluates to true
        // boolean isMember = parser.parseExpression("isMember('Mihajlo Pupin')").getValue(societyContext,
        //     Boolean.class);


        // Operators
        // evaluates to true
        boolean trueValue = parser.parseExpression("2 eq 2").getValue(Boolean.class);

        // evaluates to false
        boolean falseValue = parser.parseExpression("2 lt -5.0").getValue(Boolean.class);

        // evaluates to true
        boolean trueValue1 = parser.parseExpression("'black' gt 'block'").getValue(Boolean.class);

        // evaluates to false
        boolean falseValue1 = parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);

        // evaluates to true
        boolean trueValue2 =
            parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

        //evaluates to false
        boolean falseValue3 =
            parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2,4})?$'").getValue(Boolean.class);
        System.out.println(falseValue3);
        System.out.println(parser.parseExpression("'5.0067' matches '^-?\\d+\\.\\d{0,4}$'").getValue(Boolean.class));

        // ===Assignment===
        Inventor inventor = new Inventor();
        StandardEvaluationContext inventorContext = new StandardEvaluationContext(inventor);
        parser.parseExpression("Name").setValue(inventorContext, "Alexander Seovic2");
        // alternatively
        // Setting of a property is done by using the assignment operator. This would typically be done within a call to setValue but can also be done inside a call to getValue.
        String aleks = parser.parseExpression("Name = 'Alexandar Seovic'").getValue(inventorContext,
            String.class);
        System.out.println(aleks);

        // ===Types===
        Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
        System.out.println(dateClass);
        Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(stringClass);
        boolean trueValuen =
            parser.parseExpression("T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
                .getValue(Boolean.class);
        System.out.println("trueValuen = " + trueValuen);

        // ===Constructors===
        Inventor einstein =
            parser.parseExpression("new com.ggggght.learningjava8.spel.Inventor('Albert Einstein', 'German')")
            .getValue(Inventor.class);
        Society society = new Society();
        society.getMembers().add(tesla);
        StandardEvaluationContext societyContext = new StandardEvaluationContext(society);

        //create new inventor instance within add method of List
        parser.parseExpression("Members.add(new com.ggggght.learningjava8.spel.Inventor('Albert Einstein', 'German'))").getValue(societyContext);
        System.out.println(society);

        // ===Variables===
        context.setVariable("newName", "Mike Tesla");
        parser.parseExpression("Name = #newName").getValue(context);
        System.out.println(tesla.getName()); // "Mike Tesla"

        // ===The #this and #root variables===
        List<Integer> primes = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
        // create parser and set variable 'primes' as the array of integers
        StandardEvaluationContext newContext = new StandardEvaluationContext();
        newContext.setVariable("primes",primes);
        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTen =
            (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(newContext);
        System.out.println(primesGreaterThanTen);

        // ===Functions===
        context.registerFunction("absNum", Math.class.getDeclaredMethod("abs", int.class));
        int absRes =
            parser.parseExpression("#absNum('-404')").getValue(context, Integer.class);
        System.out.println("helloWorldReversed = " + absRes);

        // ===Ternary Operator (If-Then-Else)===
        String falseString =
            parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class);
        System.out.println(falseString);
        parser.parseExpression("Name").setValue(societyContext, "IEEE");
        societyContext.setVariable("queryName", "Nikola Tesla");

        String expression = "isMember(#queryName)? #queryName + ' is a member of the ' " +
            "+ Name + ' Society' : #queryName + ' is not a member of the ' + Name + ' Society'";

        String queryResultString =
            parser.parseExpression(expression).getValue(societyContext, String.class);
        System.out.println("queryResultString = " + queryResultString);

        // ===The Elvis Operator ===
        String name1 = "Elvis Presley";
        String displayName = name1 != null ? name : "Unknown";
        String name2 = parser.parseExpression("null?:'Unknown'").getValue(String.class);
        System.out.println(name2);  // 'Unknown'
        Inventor teslaN = new Inventor("Nikola Tesla", "Serbian");
        StandardEvaluationContext contextN = new StandardEvaluationContext(teslaN);
        String nameN = parser.parseExpression("Name?:'Elvis Presley'").getValue(contextN, String.class);

        System.out.println(nameN); // Mike Tesla
        teslaN.setName(null);
        nameN = parser.parseExpression("Name?:'Elvis Presley'").getValue(contextN, String.class);
        System.out.println(nameN); // Elvis Presley

        safeNavigationOperator();
        collectionSelection();
        expressionTemplating();
    }
    public static void safeNavigationOperator() {
        ExpressionParser parser = new SpelExpressionParser();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        tesla.setPlaceOfBirth(new PlaceOfBirth("Smiljan"));

        StandardEvaluationContext context = new StandardEvaluationContext(tesla);

        String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
        System.out.println(city); // Smiljan

        tesla.setPlaceOfBirth(null);

        city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);

        System.out.println(city); // null - does not throw NullPointerException!!!
    }

    public static void collectionSelection() {
        ExpressionParser parser = new SpelExpressionParser();
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("list",list);
        System.out.println(parser.parseExpression("#list?.?[#this%2==0]").getValue(context));
        System.out.println(parser.parseExpression("#list?.?[#this%2==0].size").getValue(context));
        context.setVariable("map", Map.of(1, 1, 2, 2, 3, 3, 4, 4));
        System.out.println(parser.parseExpression("#map?.?[value<=2]").getValue(context));
        // System.out.println(parser.parseExpression("#map?.![#this.value<=2?:#this.value]").getValue(context));
    }

    public static void expressionTemplating() {
        ExpressionParser parser = new SpelExpressionParser();
        System.out.println(
            parser.parseExpression("random number is #{T(java.lang.Math).random()}", new TemplateParserContext()).getValue(String.class));
    }
}

class Simple {
    public List<Boolean> booleanList = new ArrayList<Boolean>();
}


