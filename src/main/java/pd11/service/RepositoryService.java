package pd11.service;

import lombok.RequiredArgsConstructor;
import pd11.data.InMemoryEmployeeRepository;
import pd11.data.InMemoryTicketRepository;
import pd11.enums.TicketStatus;
import pd11.model.Employee;
import pd11.model.Identifable;
import pd11.model.Ticket;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@RequiredArgsConstructor(staticName = "initialize")
public final class RepositoryService {
    private final InMemoryTicketRepository ticketRepository;
    private final InMemoryEmployeeRepository employeeRepository;

    public void registerEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void registerTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void assignTicket(Ticket ticket, Employee employee) {
        if (!employeeRepository.getEmployeesMap().containsKey(employee.getId())) {
            throw new IllegalArgumentException("This employee doesnt exist in company");
        }
        if (!ticketRepository.getTicketMap().containsKey(ticket.getId())) {
            throw new IllegalArgumentException("This ticket doesnt exist in data");
        }
        if (ticket.getTicketStatus() == TicketStatus.CLOSED)
            throw new IllegalStateException("Cannot assign ticket with status closed");
        ticketRepository.saveAssigment(ticket, employee);
    }

    public void changeTicketExecutor(Integer id, Employee employee) {
        if (!employeeRepository.getEmployeesMap().containsKey(employee.getId())) {
            throw new IllegalArgumentException("This employee doesnt exist in company");
        }
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Ticket with this id doesnt exist"));
        if (ticket.getTicketStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Cannot change executor for closed ticket");
        }
        ticketRepository.changeTicketExecutor(ticket, employee);
    }

    public void startWorking(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new IllegalArgumentException("Ticket with this id doesnt exist"));
        if (ticketRepository.getAssigmentEmployee(ticket).isPresent()) {
            ticket.setStatusToActive();
        } else {
            throw new IllegalStateException("Ticket doesnt have assigned employee");
        }
    }

    public void endWorking(Integer id, String closeDescription) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Ticket with this id doesnt exist"));
        if (closeDescription == null || closeDescription.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (ticketRepository.getAssigmentEmployee(ticket).isPresent()) {
            ticket.setStatusToClose();
            ticketRepository.saveClosedAssigmentDescription(ticket, closeDescription);
        } else {
            throw new IllegalStateException("Ticket doesnt have assigned employee");
        }
    }

    public void printAssignedTicketInfo(Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        Optional<Employee> assigmentEmployee = ticketRepository.getAssigmentEmployee(ticket);
        String employee = assigmentEmployee.map(Employee::toString).orElse("Brak przypisanego pracownika");
        System.out.println(ticket + " " + employee);
    }

    public <T extends Identifable<?>, U extends Comparable<U>> void sortedBy(Function<T, U> getter, Collection<T> collection) {
        collection.stream()
                .sorted(Comparator.comparing(getter))
                .forEach(System.out::println);
    }

    public <T extends Identifable<?>> void findBy(Predicate<T> ticketPredicate, Collection<T> collection) {
        collection.stream()
                .filter(ticketPredicate)
                .forEach(System.out::println);
    }
}
