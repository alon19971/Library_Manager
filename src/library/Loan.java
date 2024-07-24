package library;

import java.util.Date;

/**
 * Class representing a loan of a book to a member.
 */
public class Loan {
    private Book book;
    private Member member;
    private Date loanDate;
    private Date returnDate;

    /**
     * Constructor to initialize a loan.
     *
     * @param book the book being loaned
     * @param member the member borrowing the book
     */
    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.loanDate = new Date();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book +
                ", member=" + member +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
