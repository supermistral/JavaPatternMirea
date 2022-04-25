import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task23.models.Market;
import ru.mirea.task23.models.Product;
import ru.mirea.task23.models.dto.MarketDTO;
import ru.mirea.task23.models.dto.ProductDTO;
import ru.mirea.task23.repositories.MarketRepository;
import ru.mirea.task23.services.MarketService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MarketServiceTest {
    @Mock
    private MarketRepository marketRepository;

    @InjectMocks
    private MarketService marketService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilderImpl criteriaBuilder;

    @Mock
    private CriteriaQuery<Market> criteriaQuery;

    @Mock
    private Query<Market> query;

    @Mock
    private Root<Market> root;

    @Test
    public void findAllTest() {
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(Market.class)).thenReturn(criteriaQuery);
        Mockito.when(criteriaQuery.from(Market.class)).thenReturn(root);

        Market market1 = new Market("market-1", "address-1");
        Market market2 = new Market("market-2", "address-2");

        Mockito.when(entityManager.createQuery(Mockito.any(CriteriaQuery.class))).thenReturn(query);
        Mockito.when(query.getResultStream()).thenReturn(Arrays.asList(market1, market2).stream());

        List<MarketDTO> marketList = marketService.findAll(new ArrayList<>());

        Assertions.assertEquals(2, marketList.size());
        Assertions.assertEquals(market1.getName(), marketList.get(0).getName());
        Assertions.assertEquals(market2.getAddress(), marketList.get(1).getAddress());
    }

    @Test
    public void findByIdTest() {
        Market market1 = new Market("market-1", "address-1");
        Market market2 = new Market("market-2", "address-2");
        Long id1 = 1l;
        Long id2 = 2l;

        Mockito.when(marketRepository.findById(id1)).thenReturn(Optional.of(market1));
        Mockito.when(marketRepository.findById(id2)).thenReturn(Optional.of(market2));

        Assertions.assertEquals(market1.getName(), marketService.findById(id1).getName());
        Assertions.assertEquals(market2.getName(), marketService.findById(id2).getName());
    }

    @Test
    public void createTest() {
        Market market = new Market("market-1", "address-1");

        Mockito.when(marketRepository.save(market)).thenReturn(market);

        Assertions.assertEquals(market.getName(), marketService.create(market).getName());
        Assertions.assertEquals(market.getAddress(), marketService.create(market).getAddress());
    }

    @Test
    public void deleteByIdTest() {
        Long id = 1l;

        marketService.deleteById(id);
        Mockito.verify(marketRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void findAllProductsByIdTest() {
        Product product = new Product("product", 10);
        Market market = new Market("market", "address");
        market.setProducts(Arrays.asList(product));

        Mockito.when(marketRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(market));

        List<ProductDTO> productList = marketService.findAllProductsById(1l);

        Assertions.assertEquals(1, productList.size());
        Assertions.assertEquals(product.getName(), productList.get(0).getName());
    }
}
