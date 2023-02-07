package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
//스프링 컨테이너가 리포지토리임을 인식함
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //Optional은 NPE를 방지할 수 있도록 도와주는 래퍼 클래스
        return Optional.ofNullable(store.get(id));
        //값이 null일수도 아닐수도 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //람다식으로 filter를 쓰면 값을 걸러줌
        //findAny는 어떤값이든 찾음

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //원래 어레이리스트 저렇게 쓰던가ㅏ?
        //저 자리 생성자인데 저렇게 넣는거엿나
    }

    public void clearStore(){
        store.clear();
    }


}
