package pd11;

import pd11.data.InMemoryEmployeeRepository;
import pd11.data.InMemoryTicketRepository;
import pd11.enums.TicketCategory;
import pd11.enums.TicketPriority;
import pd11.model.Employee;
import pd11.model.Ticket;
import pd11.service.Report;
import pd11.service.TicketFilterMethods;
import pd11.service.RepositoryService;

public class Main {
    public static void main(String[] args) {
        var ticketRepository = InMemoryTicketRepository.initialize();
        var employeeRepository = InMemoryEmployeeRepository.initialize();
        var service = RepositoryService.initialize(ticketRepository, employeeRepository);
        var report = Report.initialize(ticketRepository);

        service.registerTicket(new Ticket(1, "powpow", "kopie", TicketCategory.ELECTRICAL, TicketPriority.MEDIUM));
        service.registerTicket(new Ticket(2, "piwpaw", "prad strzela", TicketCategory.ELECTRICAL, TicketPriority.LOW));
        service.registerTicket(new Ticket(3, "pyrpyr", "wyciek", TicketCategory.PNEUMATIC, TicketPriority.LOW));
        service.registerTicket(new Ticket(4, "zepsute", "co bys zrobil", TicketCategory.MECHANICAL, TicketPriority.MEDIUM));
        service.registerTicket(new Ticket(5, "wozek", "yasuoo", TicketCategory.ELECTRICAL, TicketPriority.HIGH));
        service.registerTicket(new Ticket(6, "bumbum", "wybuch", TicketCategory.MECHANICAL, TicketPriority.HIGH));
        service.registerEmployee(new Employee(1L, "Jan", "Kowalski"));
        service.registerEmployee(new Employee(2L, "Michal", "Maslana"));

        service.assignTicket(ticketRepository.findById(2).orElseThrow(), employeeRepository.findById(2L).orElseThrow());
        service.assignTicket(ticketRepository.findById(1).orElseThrow(), employeeRepository.findById(2L).orElseThrow());
        service.startWorking(2);
        service.startWorking(1);
        service.changeTicketExecutor(2, employeeRepository.findById(1L).orElseThrow());
        service.endWorking(2, "teraz bedzie pikobelo");

        report.printReport();
        System.out.println();

        service.sortedBy(Ticket::getDescription, ticketRepository.getTicketMap().values());
        System.out.println();

        service.findBy(TicketFilterMethods.byCategory(TicketCategory.PNEUMATIC), ticketRepository.getTicketMap().values());
        System.out.println();

        service.printAssignedTicketInfo(2);
    }
}
