package wisioft.io;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    @DisplayName("helloJUnit")
    public void helloJUnit() {
        String expect = "hello";
        assertEquals("hello", expect);
    }

}
