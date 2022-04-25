package ru.mirea.task14.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.mirea.task14.models.Market;
import ru.mirea.task14.services.MarketService;

import java.util.List;

@RestController
@RequestMapping("api/markets")
public class MarketController {
    @Autowired
    private MarketService marketService;

    @GetMapping
    public List<Market> getMarketList() {
        return marketService.getAll();
    }

    @GetMapping("{id}")
    public Market getMarket(@PathVariable("id") int id) {
        return marketService.getOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMarket(@RequestBody Market market) {
        marketService.createOne(market);
    }

    @DeleteMapping("{id}")
    public void deleteMarket(@PathVariable("id") int id) {
        marketService.deleteOne(id);
    }
}
