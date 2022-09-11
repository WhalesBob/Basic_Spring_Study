package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // SpringData~ 들어가고 JpaRepository (스프링에서 제공하는 클래스) 만 extend 해 두면
    //
    @Override
    Optional<Member> findByName(String name);
}
