import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task23.models.Product;
import ru.mirea.task23.repositories.ProductRepository;
import ru.mirea.task23.services.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilderImpl criteriaBuilder;

    @Mock
    private CriteriaQuery<Product> criteriaQuery;

    @Mock
    private Query<Product> query;

    @Mock
    private Root<Product> root;

    @Test
    public void findAllTest() {
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        Mockito.when(criteriaQuery.from(Product.class)).thenReturn(root);

        Product product1 = new Product("product-1", 125.5);
        Product product2 = new Product("product-2", 52.2);

        Mockito.when(entityManager.createQuery(Mockito.any(CriteriaQuery.class))).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(product1, product2));

        List<Product> productList = productService.findAll(new ArrayList<>());

        Assertions.assertEquals(2, productList.size());
        Assertions.assertEquals(product1.getName(), productList.get(0).getName());
        Assertions.assertEquals(product2.getPrice(), productList.get(1).getPrice());
    }

    @Test
    public void findByIdTest() {
        Product product1 = new Product("product-1", 125.5);
        Product product2 = new Product("product-2", 52.2);
        Long id1 = 1l;
        Long id2 = 2l;

        Mockito.when(productRepository.findById(id1)).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(id2)).thenReturn(Optional.of(product2));

        Assertions.assertEquals(product1.getName(), productService.findById(id1).getName());
        Assertions.assertEquals(product2.getName(), productService.findById(id2).getName());
    }

    @Test
    public void createTest() {
        Product product = new Product("product-1", 125.5);

        Mockito.when(productRepository.save(product)).thenReturn(product);

        Assertions.assertEquals(product.getName(), productService.create(product).getName());
        Assertions.assertEquals(product.getPrice(), productService.create(product).getPrice());
    }

    @Test
    public void deleteByIdTest() {
        Long id = 1l;

        productService.deleteById(id);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);
    }
}
