package github.chrisdoberman.msscbeerservice.services.brewing;

import github.chrisdoberman.brewery.model.events.BrewBeerEvent;
import github.chrisdoberman.msscbeerservice.config.JmsConfig;
import github.chrisdoberman.msscbeerservice.domain.Beer;
import github.chrisdoberman.msscbeerservice.repositories.BeerRepository;
import github.chrisdoberman.msscbeerservice.services.inventory.BeerInventoryService;
import github.chrisdoberman.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;


    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void checkForLowInventory() {
        List<Beer>  beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());
            log.debug("Min on hand: {}", beer.getMinOnHand());
            log.debug("Inventory is: {}", invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
