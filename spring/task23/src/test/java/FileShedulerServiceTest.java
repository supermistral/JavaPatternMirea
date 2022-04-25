import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mirea.task23.models.Market;
import ru.mirea.task23.models.Product;
import ru.mirea.task23.repositories.MarketRepository;
import ru.mirea.task23.repositories.ProductRepository;
import ru.mirea.task23.services.FileSchedulerService;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class FileShedulerServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private MarketRepository marketRepository;

    @InjectMocks
    private FileSchedulerService fileShedulerService;

    @Test
    public void updateDumpDataTest() {
        Product product = new Product("product", 10);
        Market market = new Market("market", "Address");

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        Mockito.when(marketRepository.findAll()).thenReturn(Arrays.asList(market));

        try {
            fileShedulerService.updateDumpData();

            Mockito.verify(productRepository, Mockito.times(1)).findAll();
            Mockito.verify(marketRepository, Mockito.times(1)).findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
