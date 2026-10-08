package pd5.data;

import pd5.model.Identifiable;

import java.util.*;

public class InMemoryRepository<K, V extends Identifiable<K>> implements Repository<K, V> {
    private final Map<K, V> entityStorage = new HashMap<>();

    @Override
    public void save(V entity) {
        if (entity == null) {
            throw new NullPointerException("Entity nie może być nullem");
        } else {
            entityStorage.put(entity.getId(), entity);
        }
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entityStorage.get(id));
    }

    @Override
    public void deleteById(K id) {
        if (!entityStorage.containsKey(id)) {
            throw new IllegalArgumentException("Nie znaleziono entity ID: " + id);
        } else {
            entityStorage.remove(id);
            System.out.println("Usunięto encje o id: " + id);
        }
    }

    @Override
    public Collection<V> getAll() {
        return entityStorage.values();
    }
}
