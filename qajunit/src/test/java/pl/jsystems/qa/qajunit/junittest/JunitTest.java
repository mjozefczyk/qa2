package pl.jsystems.qa.qajunit.junittest;

import org.junit.jupiter.api.*;
import pl.jsystems.qa.junit.GamePlay;
import pl.jsystems.qa.qajunit.ConfigJunit;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tags({@Tag("junit"), @Tag("sanity"), @Tag("noparamTest")})
@DisplayName("Junit tests")
public class JunitTest extends ConfigJunit {

    private static final String STRING_TESTOWY = "stringTestowy";

    @DisplayName("First junit test")
    @RepeatedTest(5)
    @Disabled("Jire issue 1526")
    @Test
    public void firstJunitTest() {
        assertEquals(2 + 3, 5);
        assertNotEquals(2 + 2, 5);
        assertTrue(STRING_TESTOWY.contains("st"));
        assertTrue(STRING_TESTOWY.endsWith("wy"));
        assertEquals(STRING_TESTOWY, "stringTestowy");
    }

    @Tag("second")
    @DisplayName("Second junit test")
    @Test
    public void secondJunitTest() {
        System.out.println(0.2 * 0.2);
        double result = new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue();
        assertEquals(result, 0.04);
        assertNotEquals(0.2 * 0.2, 0.4, 0.0);
    }

    @Tag("string")
    @DisplayName("String tests")
    @Test
    public void stringTest() {
        String simpleString = "simpleString";
        String simple = "simpleString";

        String simpleString_2 = new String("simpleString");
        String simpleString_3 = new String("simpleString");

        assertSame("simpleString", simpleString);
        assertSame(simpleString, simple);
        assertNotSame(simpleString, simpleString_2);
        assertNotSame(simpleString_2, simpleString_3);

        assertEquals(simpleString_2, simpleString);
        assertEquals(simpleString, "simpleString");
        assertEquals(simple, simpleString);

    }

    @DisplayName("Google truth test")
    @Test
    public void googleTruthTest() {
        assertThat(STRING_TESTOWY).contains("tring");
    }

    @Tags({@Tag("wordpress"), @Tag("word")})
    @DisplayName("Zadanie 1")
    @Test
    public void zad1() {
        String resultString = "Wordpress powers 100% of the internet";
        String expectedString = "Wordpress powers [number]% of the internet";
        assertThat(resultString).startsWith("Wordpress powers ");
        assertThat(resultString).endsWith("% of the internet");
        assertThat(resultString).matches("Wordpress powers \\d+% of the internet");
        String result = resultString.replace("Wordpress powers ", "").replace("% of the internet", "");
        assertThat(result).matches("^\\d+$");
        int resultNumber = Integer.parseInt(result);
        assertThat(resultNumber > 0);
    }

    @DisplayName("Nested suite")
    @Nested
    public class NestedTest {

        @DisplayName("List test")
        @Test
        public void listTest() {
            List<Integer> result = Arrays.asList(1, 2, 3, 4, 5);
            List<Integer> expected = Arrays.asList(3, 4, 5);

            assertThat(result).containsAnyIn(expected);
            assertTrue(result.containsAll(expected));
            assertThat(result).hasSize(5);
            assertThat(result).containsAnyOf(1, 2, 3);

        }

        @DisplayName("Exception test")
        @Test
        public void exceptionTest() {
            GamePlay gamePlay = new GamePlay();
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> {
                       gamePlay.play(0);
                    }
                    );
        }

    }

}
