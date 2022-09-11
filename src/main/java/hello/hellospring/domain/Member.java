package hello.hellospring.domain;


// ORM : Object-Relational DB-Mapping
// Annotation 으로 이것을 매핑한다

import javax.persistence.*;

@Entity // JPA 가 관리하는 "Entity" 이다!
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Id 는 Primary Key 를 뜻하는 것.
    // GeneratedValue 는, 자동으로 생성되는 것을 말한다.
    // GenerationType.IDENTITY 는, DB가 알아서 생성해 주는 것이라는 말임.
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
