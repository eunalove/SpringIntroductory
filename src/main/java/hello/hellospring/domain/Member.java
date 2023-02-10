package hello.hellospring.domain;


import javax.persistence.*;


//ORM 객체오브젝트와 데이터베이스 테이블 매핑
@Entity
public class Member {

    //pk임
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //DB에서 자동으로 id를 생성해줌
    private Long id;

    //@Column(name = "username")
    //이러면 db에 있는 컬럼명은 username이랑 매핑이 된다
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
