package pl.jsystems.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Configuration {

    @BeforeEach
    public void setupBeforeEach(){
        System.out.println("========= BEFORE each ==========");
    }
    @AfterEach
    public void setupAfterEach(){
        System.out.println("========= After each ==========");
    }

    @BeforeAll
    public static void setupBeforeALL(){
        System.out.println("========= BEFORE ALL ==========");
    }
}
