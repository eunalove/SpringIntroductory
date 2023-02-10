package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
    class MemberServiceIntegrationTest {
//테스트는 한글로도 잘 적음

        @Autowired
        MemberService memberService;
        @Autowired
        MemberRepository memberRepository;
        //어차피 테스트니까 굳이 생성자 안하고 오토와이얼드 붙임


    /*
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

     */

    @Test
    void 회원가입() {
        //given 주어졌을 때 데이터부분
        Member member = new Member();
        member.setName("spring");

        //when 실행했을 때 실행부분
        Long saveId = memberService.join(member);

        //then 그러면 어떻게 되어야해? 검증부분

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //이걸 넣으면 예외가 터져야함
/*        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then


    }

    @Test
    void 멤버찾기() {
    }

    @Test
    void findOne() {
    }
}