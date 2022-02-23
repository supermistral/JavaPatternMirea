package ru.mirea.task14.services;

import org.springframework.stereotype.Service;
import ru.mirea.task14.models.Market;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MarketService extends CRUDService<Market> {

    public MarketService() {
        super();

        itemList = new ArrayList<>(Arrays.asList(
                new Market("market-1", "address-1")
        ));
    }
}
