package github.chrisdoberman.msscbeerservice.services;

import github.chrisdoberman.msscbeerservice.domain.Beer;
import github.chrisdoberman.msscbeerservice.repositories.BeerRepository;
import github.chrisdoberman.msscbeerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;


    public void checkForLowInventory() {
        List<Beer>  beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
            log.debug("Min on hand: {}", beer.getMinOnHand());
            log.debug("Inventory is: {}", invQOH);

            if (beer.getMinOnHand() <= invQOH) {

            }
        });
    }
}
