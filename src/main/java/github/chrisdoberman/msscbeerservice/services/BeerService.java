package github.chrisdoberman.msscbeerservice.services;

import github.chrisdoberman.brewery.model.BeerDto;
import github.chrisdoberman.brewery.model.BeerPagedList;
import github.chrisdoberman.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerDto getByUpc(String upc, Boolean showInventoryOnHand);
}
