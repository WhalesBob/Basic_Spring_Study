package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JPAMemberRepository implements MemberRepository {

    private final EntityManager em;
    // JPA 는 EntityManager 라는 것을 통해서 모든 것이 동작한다.
    // data-jpa 라이브러리를 받았으면, springboot 가 자동으로 EntityManager 객체를
    // 생성해 준다. (DB 연결 포함)(그런 EntityManager 를 그냥 Injection 받으면 된다!)

    public JPAMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 얘 하나만 해도 JPA 가
        // insert query 다 만들어서 집어넣고, ID 까지 member 에 set 해준다!
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    // 저장, 조회, 삭제 등은 SQL 짤 필요 없다. JPA 가 다 알아서 해준다.
    // findByID 같이 Primary Key 관련 애는, 그냥 em.find 한방이면 되지만,
    // findByName, findALl 같이 Primary Key 와 관련 없는 애들은 JPQL 이라는 것을 따로
    // 넣어줘야 한다.

    // JPA 를 쓸 때 주의해야 할 점 : Transaction 이 있어야 한다!

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // JPQL 쿼리 언어 : table 을 대상으로 query 를 날리는 게 아니라, 객체를 대상으로
        // query 를 날리는 언어. 이것이 SQL 로 변역된다.

        // 정확하게는 entity 를 대상으로 query 를 날리는 것이다.

        // select 의 대상은, member entity(객체) 자체를 select 한다!
        // 그리고 조회하면 끝이다.


    }
}
