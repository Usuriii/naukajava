package pd5;

import java.util.*;

public class EntityStorage<K, V extends Entity<K>> implements EntityManager<K, V> {
    private final Map<K, V> entityStorage = new HashMap<>();

    @Override
    public void addEntity(V entity) {
        if (entity == null) {
            throw new NullPointerException("Entity nie może być nullem");
        } else {
            entityStorage.put(entity.getId(), entity);
        }
    }

    @Override
    public Optional<V> searchEntity(K id) {
        return Optional.ofNullable(entityStorage.get(id));
    }

    @Override
    public void deleteEntity(K id) {
        if (!entityStorage.containsKey(id)) {
            throw new IllegalArgumentException("Brak przypisanej wartości do podanego ID: " + id);
        } else {
            entityStorage.remove(id);
            System.out.println("Usunięto użytkownika o id: " + id);
        }
    }

    @Override
    public Collection<V> getAllEntities() {
        return entityStorage.values();
    }
}
