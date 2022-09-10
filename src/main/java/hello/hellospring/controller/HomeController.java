package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // localhost:8080에 들어오면 처음 들어가지는 곳.
    public String home(){
        return "home";
    }
    // Spring 에서 아무것도 없을 때, Welcome Page 우선순위
    // 1. 먼저 Controller 에서 찾아본다. 만약에 있으면, 그냥 바로 Mapping 되어 있는 애로 로딩해주고 끝난다.
    // 2. 없으면, index.html 이 있는지 확인한다. 그래서 index.html을 띄운다.


}
