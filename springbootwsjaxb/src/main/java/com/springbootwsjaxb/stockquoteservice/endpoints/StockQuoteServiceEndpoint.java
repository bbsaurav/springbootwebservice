package com.springbootwsjaxb.stockquoteservice.endpoints;

import com.springbootwebservice.GetPrice;
import com.springbootwebservice.GetPriceResponse;
import com.springbootwebservice.Update;
import com.springbootwsjaxb.stockquoteservice.repository.StockQuoteServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class StockQuoteServiceEndpoint {

  private static final String NAMESPACE_URI = "http://springbootwebservice.com";
  private StockQuoteServiceRepository stockQuoteServiceRepository;

  @Autowired
  public StockQuoteServiceEndpoint(StockQuoteServiceRepository stockQuoteServiceRepository) {
    this.stockQuoteServiceRepository = stockQuoteServiceRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPrice")
  @ResponsePayload
  public GetPriceResponse getPrice(@RequestPayload GetPrice request) {
    log.info("Got request to get price for symbol {}", request.getSymbol());
    GetPriceResponse response = new GetPriceResponse();
    response.setReturn(stockQuoteServiceRepository.getStock(request.getSymbol()).getPrice());
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "update")
  @ResponsePayload
  public void update(@RequestPayload Update request) {
    log.info("Got request to update price of {} to {}", request.getSymbol(), request.getPrice());
    stockQuoteServiceRepository.updatePrice(request.getSymbol(), request.getPrice());
  }
}
