package pl.jsystems.qa.qajunittest;

import org.junit.jupiter.api.*;
import pl.jsystems.qa.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("junit")
@DisplayName("Junit tests")
public class JunitTest extends Configuration {

    private static final String STRING_TESTOWY="stringTestowy";

    @DisplayName("First")
    //@RepeatedTest(5)
    @Test
    public void firstJunitTest(){
        assertEquals(2 + 3, 5, "message");
        assertNotEquals(2 + 2, 5, "message");
        assertTrue(STRING_TESTOWY.contains("st"));
        assertTrue(STRING_TESTOWY.endsWith("wy"));
        assertEquals(STRING_TESTOWY, "stringTestowy");
    }

    @Tag("second")
    @DisplayName("Second")
    @Test
    public void secondJunitTest(){
        System.out.println(0.2*0.2);
        double result = new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue();
        assertEquals(result, 0.04);
    }

    @Tag("String")
    @DisplayName("String tests")
    @Test
    public void stringTest(){
        String simpleString ="simpleString";
        String simple ="simpleString";

        String simpleString_2 = new String("simpleString");
        String simpleString_3 = new String("simpleString");

        assertSame("simpleString", simpleString);
        assertSame(simpleString, simple);
        assertNotSame(simpleString, simpleString_2);
        assertEquals(simpleString_3, simpleString_2);
    }

    @DisplayName("Google truth")
    @Test
    public void googleTruthTest(){
        assertThat(STRING_TESTOWY).contains("tring");
    }

    @Tags({@Tag("wordpress"),@Tag("word")})
    @DisplayName("Zad1")
    @Test
    public void zad1(){
        String resultString = "Wordpress powers 100% of the internet";
        String expectedString = "Wordpress powers [number]% of the internet";
        assertThat(resultString).startsWith("Wordpress powers ");
        assertThat(resultString).endsWith("% of the internet");
        assertThat(resultString).matches("Wordpress powers \\d+% of the internet");

        String result = resultString.replace("Wordpress powers ","").replace("% of the internet","");
        int resultInt = Integer.parseInt(result);
        assertThat(resultInt>0 && resultInt<101);
    }

    @Nested
    public class nestedTest {
        @Test
        @DisplayName("List test")
        public void listTest() {
            List<Integer> result = Arrays.asList(1, 2, 3, 4, 5);
            List<Integer> expected = Arrays.asList(3, 4, 5);

            assertThat(result).containsAnyIn(expected);
            assertTrue((result.containsAll(expected)));
        }
    }
}