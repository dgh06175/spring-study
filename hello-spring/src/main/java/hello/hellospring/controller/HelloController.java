package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "안녕 세상!!아!");
        // 모델의 데이터 이름과 값
        return "hello"; // resources/templates/hello.html 을 실행해라
    }
}
