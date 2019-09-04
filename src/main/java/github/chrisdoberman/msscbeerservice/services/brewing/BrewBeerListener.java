package github.chrisdoberman.msscbeerservice.services.brewing;

import github.chrisdoberman.msscbeerservice.config.JmsConfig;
import github.chrisdoberman.msscbeerservice.domain.Beer;
import github.chrisdoberman.msscbeerservice.events.BrewBeerEvent;
import github.chrisdoberman.msscbeerservice.events.NewInventoryEvent;
import github.chrisdoberman.msscbeerservice.repositories.BeerRepository;
import github.chrisdoberman.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent brewBeerEvent) {
        BeerDto beerDto = brewBeerEvent.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        // simulating a really quick beer brewing process, very simplified!!!
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer: {} : new QOH: {}", beer.getBeerName(), beerDto.getQuantityOnHand());
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
