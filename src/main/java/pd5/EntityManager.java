package pd5;

import java.util.Collection;
import java.util.Optional;

public interface EntityManager<K, V extends Entity<K>> {
    void addEntity(V entity);

    Optional<V> searchEntity(K id);

    void deleteEntity(K id);

    Collection<V> getAllEntities();
}
