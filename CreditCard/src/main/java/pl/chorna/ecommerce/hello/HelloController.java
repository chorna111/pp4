package pl.chorna.ecommerce.hello;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello-world")
    String helloWorld() {
        var name = "Anastazja";
        return String.format("Hello %s", name);
    }
}