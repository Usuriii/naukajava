package pd5.data;

import pd5.model.Identifiable;

import java.util.Collection;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
    void save(V entity);

    Optional<V> findById(K id);

    void deleteById(K id);

    Collection<V> getAll();
}
