package pd5;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor(staticName = "inicialization")
public class UserService {
    private final EntityManager<Long, User> entityManager = new EntityStorage<>();

    public void changeUserName(Long id, String newName) {
        User user = entityManager.searchEntity(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika o takim ID"));
        user.setName(newName);
    }

    public void addUser(User user) {
        entityManager.addEntity(user);
    }

    public void printUser(Long id) {
        entityManager.searchEntity(id)
                .ifPresent(u -> System.out.println(u.getName()));
    }

    public void deleteUser(Long id) {
        entityManager.deleteEntity(id);
    }

    public Collection<User> getAllUsers() {
        return entityManager.getAllEntities();
    }
}
