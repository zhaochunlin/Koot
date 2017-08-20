import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Administrator on 2017/8/20.
 */
@RestController
@EnableAutoConfiguration
public class AppMain {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppMain.class, args);
    }
}
