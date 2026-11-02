package pd11.data;

import lombok.RequiredArgsConstructor;
import pd11.model.Employee;

import java.util.*;

@RequiredArgsConstructor(staticName = "initialize")
public final class InMemoryEmployeeRepository implements Repository<Employee, Long> {
    private final Map<Long, Employee> employeesMap = new HashMap<>();

    public Map<Long, Employee> getEmployeesMap() {
        return Map.copyOf(employeesMap);
    }

    @Override
    public void save(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (employeesMap.containsKey(employee.getId())) {
            throw new IllegalArgumentException("Employee with this ID already exist");
        }
        employeesMap.put(employee.getId(), employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(employeesMap.get(id));
    }
}
