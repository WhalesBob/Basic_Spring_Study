package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","Spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API
    @GetMapping("hello-string")
    @ResponseBody // HTTP 부분의 Body에 직접 해당 내용을 넣어 주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // ex)"hello spring"
        // template engine 과의 차이점 : View 없이 그대로 내려간다!
        // 이전의 template engine 에서는 기존의 template(view) 를 가지고 조작하는 방식이었다.
        // 그냥 이거는 그대로 데이터를 내려주는 방식이다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 문자가 아닌 객체를 넘겼다!
        // 실제로 넘겨보면, JSON 형식으로 나온다.
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
