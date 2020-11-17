package pl.jsystems.qa.qajunittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.jsystems.qa.Configuration;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tags({@Tag("junit"),@Tag("param")})
@DisplayName("Params test")
public class paramsTest extends Configuration {

    @DisplayName("Params")
    @ParameterizedTest(name ="Params test with value {0}")
    @ValueSource(ints = { 5, 15, 25 })
    public void firstParamTest(int number)
    {
        assertEquals(number % 5, 0);
    }

    @DisplayName("Hello World for string")
    @ParameterizedTest(name ="say hello {0}")
    @ValueSource(strings = {"Hello","Hello junit","Hello students"})
    public void helloWorld(String text)
    {
        assertThat(text).contains("Hello");
    }

    @DisplayName("Multi CSV param")
    @ParameterizedTest(name ="say hello {0}. {1}")
    @CsvSource(value = {"Hello; 5","Hello junit; 5","Hello students; 25"}, delimiter=';')
    public void multiParam(String text, int number)
    {
        assertThat(text).contains("Hello");
        assertEquals(number % 5, 0);
    }

    @DisplayName("Multi file CSV param")
    @ParameterizedTest(name ="say hello {0}. {1}")
    @CsvFileSource(resources = "/plik.csv", delimiter = ',')
    public void paramCSV(String text, int number)
    {
        assertThat(text).contains("hello");
        assertEquals(number % 5, 0);
    }

    @DisplayName("Enum param")
    @ParameterizedTest(name ="ENUM param {0}")
    @EnumSource(value = ParamEnum.class)
    public void enumTest(ParamEnum ParamEnum)
    {
        assertThat(ParamEnum.toString().contains("ENUM"));
    }

    String resultString = "Wordpress powers 100% of the internet";
    String expectedString = "Wordpress powers [number]% of the internet";

    @DisplayName("zad2")
    @ParameterizedTest(name ="Wordpress test {0}")
    @ValueSource(strings ={"1","100","10000"})
    public void zad2(String value)
    {
        String resultString = "Wordpress powers " + value+"% of the internet";
        String expectedString = "Wordpress powers [number]% of the internet";
        assertThat(resultString).startsWith("Wordpress powers ");
        assertThat(resultString).endsWith("% of the internet");
        assertThat(resultString).matches("Wordpress powers \\d+% of the internet");

        String result = resultString.replace("Wordpress powers ","").replace("% of the internet","");
        int resultInt = Integer.parseInt(result);
        assertThat(resultInt>0 && resultInt<101);
    }


    enum ParamEnum {
        ENUM_ONE,
        ENUM_TWO
   }
}
