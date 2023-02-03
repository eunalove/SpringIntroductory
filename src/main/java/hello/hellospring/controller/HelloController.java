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
        model.addAttribute("data","hello!!");
        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //통신프로토콜에서 바디부분에 직접 넣어주겠다는 뜻임
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    //이걸 쓸일은 거의 없다ㅏㅏㅏ고...?
    //진ㅉㄱ 먼데..?
    @GetMapping("hello-api")
    @ResponseBody
    //객체반환+ 리스폰즈바디는 기본으로 JSON방식으로 설정되어있음
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        //private인 변수는 메소드를 통해 접근함
        //프로퍼티 접근방식

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;




    }


}
