package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 해당 Annotation 을 보고, Spring 이 실행될 때 이 Controller 를 객체로써
public class MemberController { // 생성하여 Spring 이 들고 있는 것이다.
    // Spring Container 에서 Spring Bean 이 관리된다고 표현되기도 한다.

    /*private final MemberService memberService = new MemberService();*/

    // new 로 생성해서 쓸수 있지만, Spring 이 관리하게 되면, Spring Container 에 등록하고
    // Spring Container 로부터 받아서 사용할 수 있도록 관리해야 한다!

    // 특정 MemberController 에서 new 해서 새로 객체생성을 하게 되면, 원래는 다른 여러 controller들이
    // 이 MemberController 를 가져다 쓸 수 있는데,(ex : 주문 Controller, 회원 로그인 Controller 등)
    // 하나만 생성해 놓고 공용으로 쓸 수 있는 것은, 하나만 생성해서 공용으로 쓰면 된다!

    private final MemberService memberService;

    @Autowired // 생성자에서 연결시켜 줄 때 필요한 애. 생성자에서 쓰면,
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 그냥 위에 이렇게 MemberService 객체를 MemberController 의 멤버로 정해 놓고,
    // 생성자로 받아서 등록해 버리게 한 뒤, @Autowired Annotation 을 붙여 놓으면.
    // MemberController 가 맨 처음에 @Controller Annotation 을 받아서 등록하면,
    // 쨋든 해당 MemberController 가 생성이 될 때 Spring 에서 생성자를 통해서 만들어버린다.

    // @Autowired 를 등록해 버리면, Spring Container 안에서 관리되고 있는 memberService 객체를
    // 이 Controller 에 있는 생성자와 연결시켜 준다.


    // 단, MemberController 에서 Autowired 해 놓은 Service 를 처음에 제대로 동작시키려면,
    // Service 단에 가서 @Service Annotation 을 등록을 해야,
    // Spring 이 맨 처음에 해당 서비스를 등록해서 관리해 주고, @Autowired 생성자에 연결시켜 준다.

    // 이렇게 미리미리 Annotation 다 붙여놓고 맨처음에 Spring 이 해당 객체를 다 생성해 준 뒤에,
    // @Autowired 로 연결해 주는 특성을 보고 "Dependency Injection" 이라고 한다!
    // "의존성 주입" 이라고 하기도 한다. Spring 이 밖에서, Spring Bean 에 등록되어 있는 애를
    // 넣어주는 느낌이 든다!
    // 의존관계를 주입하는 것이다!

    // "Spring Bean" 을 등록하는 두가지 방법

    // 1. Component Scan : Annotation 넣어서 해준 위의 방법
    // @Service, @Controller 등의 Annotation 의 내부에 들어가 보면, Component 라고 등록이 되어 있다.
    // Spring 이 올라올 때, 관련된 Component Annotation 이 있으면, Spring 이 시작할 때 부터 해당 객체들을
    // 다 생성해서 등록해 놓는 것임.
    // @Autowired 는 이 연관관계를 선으로 연결해 주는 것.

    // 2. Java Code 로 직접 Spring Bean 에 등록하기.

    // 어지간하면 모두 Spring Bean 에 등록해서 쓰는 것이 이득이다!
    // 그럼 그냥 패키지 하나 만들어서 클래스 생성한 다음에, Component 관련 Annotation 붙이면 무조건 이득인가?
    // 답은 "아니오" 이다.
    // 결국 시작되는것은 항상 ~~SpringApplication 에서 시작되는데, 소속 패키지 안에 있는 것들은
    // Spring 이 다 뒤져서 Spring Bean 에다가 등록한다.
    // 그런데, 해당 하위 패키지가 아닌 애들은, Spring 이 Component Scan 안한다. 대상이 아님.

    // 기본으로, Spring 은 Spring Container 에 Spring Bean 을 등록할 때,
    // 기본적으로 SingleTon 으로 등록해서 전체와 공유한다.
    // 같은 Spring Bean 이면 모두 같은 객체이다. 정말 특수한 케이스로, 설정에서 이렇게 안되게 할 수 있지만,
    // 거의 사용되지는 않는다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
