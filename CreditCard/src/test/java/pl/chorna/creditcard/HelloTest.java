package pl.chorna.creditcard;

import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    void helloTest(){
        var name="Nastusia";
        var message=String.format("Hello, %s",name);
        System.out.println(message);
    }
    @Test
    void equationTest(){
        //Arrange-act-assert
        int a =2;
        int b=3;
        var result=a+b;
        assert (5==result);

    }
    @Test
    void equationTestV2(){
        //A / Arrange

        int a = 2;
        int b = 3;

        //A / Act

        var result = a + b;

        //A / Assert
        assert (10 == result);

    }
}
