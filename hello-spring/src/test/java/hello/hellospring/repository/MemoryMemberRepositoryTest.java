package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryMemberRepository();
        repository.clearStore();
    }

    @Test
    void 멤버_저장_후_정상적으로_찾기() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void 멤버_저장_후_찾기() {
        Member member1 = new Member();
        member1.setName("Spring1");

        Member member2 = new Member();
        member2.setName("Spring2");

        repository.save(member1);
        repository.save(member2);

        Member result1 = repository.findById(member1.getId()).get();
        Member result2 = repository.findById(member2.getId()).get();
        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
        assertThat(result1).isNotEqualTo(result2);
    }

    @Test
    void 없는_ID로_찾을_때_Optional_empty_반환() {
        Optional<Member> result = repository.findById(1L);
        assertThat(result).isNotPresent();
    }

    @Test
    void 멤버_저장_후_이름으로_찾기() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findByName("Spring").get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void 없는_이름으로_찾을_때_Optional_empty_반환() {
        Optional<Member> result = repository.findByName("Spring");
        assertThat(result).isNotPresent();
    }

    @Test
    void 전체_멤버_목록_반환() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result).hasSize(2);
        assertThat(result).contains(member1, member2);
    }
}
