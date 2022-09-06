package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    // 장점 : 모든 테스트를 한방에 돌릴 수 있다!
    // 테스트 케이스를 JUnit 으로 작성해서 메소드 단위로 쭉 볼 수 있다!!
    // 실무에서는 테스트 케이스를 이렇게 먼저 적어서 볼 수 있다.
    // TDD : 여기서 개발을 먼저 하지 않고, 테스트 케이스들을 먼저 작성한 다음, 개발에 들어가서 요렇게 테스트를 거치는 것.
    // 수만 수십만 라인 넘어가는 코드가 되면, 아래와 같은 테스트 케이스 없이 개발하는 것이 거의 불가능에 가깝다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ // 메소드 하나하나 동작이 끝날때마다 동작하는 함수. callback method
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findByID(member.getId()).get();
//        System.out.println("result = " + (result == member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
