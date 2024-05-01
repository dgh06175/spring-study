package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void tearDown() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);
        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void 전체_멤버_찾기() {
        // given
        Member member1 = new Member();
        member1.setName("spring11");
        Member member2 = new Member();
        member2.setName("spring12");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        assertThat(memberRepository.findAll()).isEqualTo(List.of(member1, member2));
    }

    @Test
    void 멤버_찾기() {
        // given
        Member member1 = new Member();
        member1.setName("spring11");
        Member member2 = new Member();
        member2.setName("spring12");

        // when
        memberService.join(member1);
        memberService.join(member2);

        assertThat(memberRepository.findById(1L).get()).isEqualTo(member1);
        assertThat(memberRepository.findByName("spring12").get()).isEqualTo(member2);
    }
}