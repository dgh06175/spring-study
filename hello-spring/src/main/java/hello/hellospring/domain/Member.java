package hello.hellospring.domain;

import jakarta.persistence.*;

// JPA 가 관리하는 엔티티
@Entity
public class Member {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 지정하지 않아도 알아서 생성해주는 것
    private Long id;


    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
