package pd5.service;

import lombok.RequiredArgsConstructor;
import pd5.data.InMemoryRepository;
import pd5.data.Repository;
import pd5.model.User;

import java.util.Collection;

@RequiredArgsConstructor(staticName = "initialize")
public class UserService {
    private final Repository<Long, User> repository = new InMemoryRepository<>();

    public void changeUserName(Long id, String newName) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika o takim ID"));
        user.setName(newName);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void printUser(Long id) {
        repository.findById(id)
                .ifPresent(u -> System.out.println(u.getName()));
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public Collection<User> getAllUsers() {
        return repository.getAll();
    }
}
