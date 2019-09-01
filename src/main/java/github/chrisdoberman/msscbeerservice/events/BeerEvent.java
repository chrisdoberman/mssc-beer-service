package github.chrisdoberman.msscbeerservice.events;

import github.chrisdoberman.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -5261741136895282100L;

    private final BeerDto beerDto;
}
