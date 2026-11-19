package pd15;

import pd15.domain.Book;
import pd15.domain.Loan;
import pd15.domain.Member;
import pd15.repository.BookRepository;
import pd15.repository.LoanRepository;
import pd15.repository.MemberRepository;
import pd15.service.impl.BookServiceImpl;
import pd15.service.impl.LoanServiceImpl;
import pd15.service.impl.MemberServiceImpl;


public class Main {
    public static void main(String[] args) {
        var bookRepo = BookRepository.initialize();
        var memberRepo = MemberRepository.initialize();
        var loanRepo = LoanRepository.initialize();

        var bookService = BookServiceImpl.initialize(bookRepo);
        var memberService = MemberServiceImpl.initialize(memberRepo);
        var loanService = LoanServiceImpl.initialize(loanRepo, bookRepo, memberRepo);

        bookService.addBook(new Book(1L, "9788373271890", "Pan Tadeusz", "Adam Mickiewicz", 1834, 5));
        bookService.addBook(new Book(4L, "9788373271920", "Dziady cz. III", "Adam Mickiewicz", 1832, 4));
        bookService.addBook(new Book(6L, "9788373271944", "Quo vadis", "Henryk Sienkiewicz", 1896, 1));
        bookService.addBook(new Book(7L, "9788373271951", "Kamienie na szaniec", "Aleksander Kamiński", 1943, 6));

        bookService.findAvailable().forEach(System.out::println);
        System.out.println();

        bookService.search("mickiewicz").forEach(System.out::println);
        System.out.println();

        memberService.register(new Member(1L, "Jan", "dupa@onet.pl"));
        memberService.register(new Member(2L, "Maciek", "klan@o2.pl"));

        loanService.borrow(1L, 6L);

        bookService.findAvailable().forEach(System.out::println);
        System.out.println();

        memberService.getActiveLoans(1L).forEach(System.out::println);

//        loanService.returnBook(1L);
//        bookService.findAvailable().forEach(System.out::println);
    }
}
