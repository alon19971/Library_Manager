package test;

import library.Book;
import library.Loan;
import library.Member;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    @Test
    public void testLoanCreation() {
        Book book = new Book("1984", "George Orwell", 1949);
        Member member = new Member("Alice", 1);
        Loan loan = new Loan(book, member);

        assertEquals(book, loan.getBook());
        assertEquals(member, loan.getMember());
        assertNotNull(loan.getLoanDate());
    }
}
