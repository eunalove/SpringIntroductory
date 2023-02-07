package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//스프링에 올라올 때 서비스라고 스프링 컨테이너에 딱 등록해줌
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member){
        //같이 이름이 있는 중복회원X
        valiateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void valiateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
            //만약 값이 있으면 근데 
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
