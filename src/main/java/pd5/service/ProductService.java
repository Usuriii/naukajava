package pd5.service;

import lombok.RequiredArgsConstructor;
import pd5.data.InMemoryRepository;
import pd5.data.Repository;
import pd5.entity.Product;

import java.util.Collection;

@RequiredArgsConstructor(staticName = "initialize")
public class ProductService {
    private final Repository<String, Product> repository = new InMemoryRepository<>();

    public void changeProductName(String id, String newName) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono produktu o takim ID"));
        product.setName(newName);

    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void printProduct(String id) {
        repository.findById(id)
                .ifPresent(p -> System.out.println(p.getName()));
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    public Collection<Product> getAllProduct() {
        return repository.getAll();
    }

}
