package pd11.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@Getter
public final class Employee implements Identifable<Long> {
    private final Long id;
    private final String firstName;
    private String lastName;

    public Employee(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        validation();
    }

    private void validation() {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
