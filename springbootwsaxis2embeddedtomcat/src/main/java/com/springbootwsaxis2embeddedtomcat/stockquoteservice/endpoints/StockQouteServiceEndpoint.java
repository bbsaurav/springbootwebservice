package com.springbootwsaxis2embeddedtomcat.stockquoteservice.endpoints;

import com.springbootwebservice.GetPrice;
import com.springbootwebservice.GetPriceResponse;
import com.springbootwebservice.StockQuoteServiceSkeletonInterface;
import com.springbootwebservice.Update;
import com.springbootwsaxis2embeddedtomcat.stockquoteservice.repository.StockQuoteServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockQouteServiceEndpoint implements StockQuoteServiceSkeletonInterface {

  private StockQuoteServiceRepository stockQuoteServiceRepository;

  @Autowired
  public StockQouteServiceEndpoint(StockQuoteServiceRepository stockQuoteServiceRepository) {
    log.info("StockQouteServiceEndpoint constructor invoked");
    this.stockQuoteServiceRepository = stockQuoteServiceRepository;
  }
  @Override
  public void update(Update request) {
    log.info("Got request to update price of {} to {}", request.getSymbol(), request.getPrice());
    stockQuoteServiceRepository.updatePrice(request.getSymbol(), request.getPrice());
  }

  @Override
  public GetPriceResponse getPrice(GetPrice request) {
    log.info("Got request to get price for symbol {}", request.getSymbol());
    GetPriceResponse response = new GetPriceResponse();
    response.set_return(stockQuoteServiceRepository.getStock(request.getSymbol()).getPrice());
    return response;
  }
}
