package hello.hellospring;

//내가 헷갈렸던건 앞에서 개발자가 직접 의존성주입을 해줬었잖아
//그때처럼 생성자 만들고 그걸 필요로 할 때 new를 만드는 식으로?
//컨트롤러에 생성자 만들고 서비스에서 컨트롤러 new, 레포지토리에서 서비스 new를 하면 안되나?
//하지만 이러면 같은 레포지토리를 쓰는 기능이 하나 더 있게 되면
// new를 여러번 만들어야하는 경우가 생길수도 있으니
//좋지 않은 방법 같다

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);

        return new JdbcTemplateMemberRepository(dataSource);
    }
}




















