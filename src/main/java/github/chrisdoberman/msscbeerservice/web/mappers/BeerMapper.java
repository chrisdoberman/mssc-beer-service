package github.chrisdoberman.msscbeerservice.web.mappers;

import github.chrisdoberman.msscbeerservice.domain.Beer;
import github.chrisdoberman.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    //BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
