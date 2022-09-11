package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 이제는 순수 자바코드가 아닌, Spring, DB 와 연동해서 테스트를 진행해 보자!
// 어차피 실제로는 다 연동해서 테스트를 진행해야 하니, 분명히 필요하다.

@SpringBootTest
@Transactional
@WebAppConfiguration
class MemberServiceIntegrationTest {

    // 통합형에서는 Spring 에게 memberService, memberRepository 내놓으라고 해야 한다.

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    // 기존 코드에서는 Dependency Injection 으로 생성자에 다 넣어서 진행해 줬지만,
    // Test 에서는, (다른데서 Test code 를 갖다쓸 것이 아니기 때문에) 필요한 것들을 Autowired해서
    // 갖다쓰기만 하면 되는 개념이다.
    // 그래서 그냥 Field Injection 으로 해주면 끝.

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring100");

        // when
        Long saveID = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

        // 주로 3가지로, 머리가슴배 마냥 나뉜다!
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring2");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}