package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByID(Long id);
    Optional<Member> findByName(String name);
    // Optional 은, findByID나 Name 으로 가져올 때 이것이 null 인 순간들이 있을 수 있다.
    // 요즘은 null 을 던지기보다, Optional 이라는 것으로 감싸서 반환하는 것을 선호한다고 한다.
    // Java 8에 들어가 있는 기능이다.
    List<Member> findAll();

}
