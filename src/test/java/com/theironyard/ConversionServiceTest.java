package com.theironyard;

import net.doughughes.testifier.exception.CannotFindMethodException;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.test.TestifierTest;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by doug on 1/19/17.
 */
public class ConversionServiceTest extends TestifierTest {

    @Test
    public void testConvert1StoneToPounds() {
        /* arrange */
        ConversionService service = new ConversionService();

        /* act */
        double result = service.convert(1, Weight.STONE, Weight.POUND);

        /* assert */
        assertThat("1 stone should be 14 pounds",
                result, closeTo(14, 0.0001));
    }

    @Test
    public void testConvert200PoundsToKilograms() {
        /* arrange */
        ConversionService service = new ConversionService();

        /* act */
        double result = service.convert(200, Weight.POUND, Weight.KILOGRAM);

        /* assert */
        assertThat("200 pounds should be 90.718474 kilograms",
                result, closeTo(90.718474, 0.0001));
    }

    @Test
    public void testConvert2345Point67GramsToTons() {
        /* arrange */
        ConversionService service = new ConversionService();

        /* act */
        double result = service.convert(2345.67, Weight.GRAM, Weight.TON);

        /* assert */
        assertThat("2345.67 grams should be 0.0025856586 tons",
                result, closeTo(0.0025856586, 0.0001));
    }

    @Test
    public void testListWeightUnits(){
        /* arrange */
        ConversionService service = new ConversionService();

        /* act */
        List<String> result = service.listUnits();

        /* assert */
        assertThat("Weight units should include all of the following: mile, yard, foot, inch, kilometer, meter, centimeter, and millimeter",
                result, containsInAnyOrder("ton", "pound", "ounce", "stone", "metric ton", "kilogram", "gram"));
    }

    @Test
    public void testListWeightUnitsUsesValuesMethod(){
        /* arrange */

        /* act */
        String source = null;
        try {
            source = codeWatcher.getMainSourceCodeService().getDescriptionOfMethod("listUnits");
        } catch (CannotFindMethodException e) {
            fail(e.getMessage());
        }

        /* assert */
        assertThat("listUnits() method should use the values() method of the Weight enum",
                source, RegexMatcher.matches("^.*?MethodCallExpr NameExpr\\[Weight\\] MethodName\\[values\\] MethodArguments /MethodArguments /MethodCallExpr.*?$"));
    }

}