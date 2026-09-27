package pd5;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor(staticName = "inicialization")
public class ProductService {
    private final EntityManager<String, Product> entityManager = new EntityStorage<>();

    public void changeProductName(String id, String newName) {
        Product product = entityManager.searchEntity(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono produktu o takim ID"));
        product.setName(newName);

    }

    public void addProduct(Product product) {
        entityManager.addEntity(product);
    }

    public void printProduct(String id) {
        entityManager.searchEntity(id)
                .ifPresent(p -> System.out.println(p.getName()));
    }

    public void deleteProduct(String id) {
        entityManager.deleteEntity(id);
    }

    public Collection<Product> getAllProduct() {
        return entityManager.getAllEntities();
    }

}
