package test;

import library.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    @Test
    public void testMemberCreation() {
        Member member = new Member("Alice", 1);
        assertEquals("Alice", member.getName());
        assertEquals(1, member.getId());
    }

}
