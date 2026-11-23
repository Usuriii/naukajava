package pd15;

import pd15.dto.CommandBookRequest;
import pd15.dto.CommandLoanRequest;
import pd15.dto.CommandMemberRegisterRequest;
import pd15.dto.MemberDto;
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

        CommandBookRequest bookRequest = new CommandBookRequest("9788373271890", "Pan Tadeusz", "Adam Mickiewicz", 1834, 5);
        CommandBookRequest bookRequest1 = new CommandBookRequest("9788373271920", "Dziady cz. III", "Adam Mickiewicz", 1832, 4);
        CommandBookRequest bookRequest2 = new CommandBookRequest("9788373271944", "Quo vadis", "Henryk Sienkiewicz", 1896, 1);
        CommandBookRequest bookRequest3 = new CommandBookRequest("9788373271951", "Kamienie na szaniec", "Aleksander Kamiński", 1943, 6);

        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest1);
        bookService.addBook(bookRequest2);
        bookService.addBook(bookRequest3);

        bookService.findAvailable().forEach(System.out::println);
        System.out.println();

        bookService.search("mickiewicz").forEach(System.out::println);
        System.out.println();

        CommandMemberRegisterRequest memberRequest = new CommandMemberRegisterRequest("Jan", "dupa@onet.pl");
        CommandMemberRegisterRequest memberRequest1 = new CommandMemberRegisterRequest("Maciek", "klan@o2.pl");

        memberService.register(memberRequest);
        memberService.register(memberRequest1);

        CommandLoanRequest loanRequest = new CommandLoanRequest(1L, 2L);
        loanService.borrow(loanRequest);

        bookService.findAvailable().forEach(System.out::println);
        System.out.println();

        memberService.getActiveLoans(1L).forEach(System.out::println);
    }
}
