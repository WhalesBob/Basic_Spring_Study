package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller // 해당 Annotation 을 보고, Spring 이 실행될 때 이 Controller 를 객체로써
public class MemberController { // 생성하여 Spring 이 들고 있는 것이다.
    // Spring Container 에서 Spring Bean 이 관리된다고 표현되기도 한다.

    private final MemberService memberService = new MemberService();
    // new 로 생성해서 쓸수 있지만, Spring 이 관리하게 되면, Spring Container 에 등록하고
    // Spring Container 로부터 받아서 사용할 수 있도록 관리해야 한다!

    //
}
