package com.koitoer.java.work;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.assertj.core.api.Assertions;

public class Example {

    public static void main(String[] args) {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();

        // Create an expression
        String falseExpr = "numberOfBooking > 3 and numberOfReviews > 0 and liveDate >= 90 and (olb  == true and ipm == false)";
        JexlExpression e = jexl.createExpression( falseExpr );

        // Create an expression
        String trueExpr = "numberOfBooking >= 2 and numberOfReviews < 0 and liveDate < 90 and (olb  == true and ipm == false)";
        JexlExpression e2 = jexl.createExpression( trueExpr );

        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set("numberOfBooking", 2);
        jc.set("numberOfReviews", -1);
        jc.set("liveDate", 80);
        jc.set("olb", true);
        jc.set("ipm", false);

        // Now evaluate the expression, getting the result
        boolean o = (Boolean)e.evaluate(jc);
        Assertions.assertThat(o).isFalse();

        // Now evaluate the expression, getting the result
        boolean o2 = (Boolean)e2.evaluate(jc);
        Assertions.assertThat(o2).isTrue();

        // Create an expression
        jc.set("foo", new Example());
        String trueExpr2 = "numberOfBooking >= 2 and numberOfReviews < 0 and liveDate < 90 and (olb  == true and ipm == false) and foo.aComplexAssertion(liveDate)";
        JexlExpression e3 = jexl.createExpression( trueExpr2 );

        // Now evaluate the expression, getting the result
        boolean o4 = (Boolean)e3.evaluate(jc);
        Assertions.assertThat(o4).isFalse();
    }


    public boolean aComplexAssertion(Integer liveDate){
        return true;
    }

}
