package github.chrisdoberman.msscbeerservice.bootstrap;

import github.chrisdoberman.msscbeerservice.domain.Beer;
import github.chrisdoberman.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Trite")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .upc(337010000001L)
                    .price(new BigDecimal(12.95))
                    .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .upc(337010000002L)
                    .price(new BigDecimal(11.95))
                    .minOnHand(12)
                    .build());
        }

        System.out.println("Loaded beers: " + beerRepository.count());
    }
}