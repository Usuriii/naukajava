package pd15.repository;

import java.util.Optional;

public interface Repository<V> {
    Optional<V> findById(Long id);
}
