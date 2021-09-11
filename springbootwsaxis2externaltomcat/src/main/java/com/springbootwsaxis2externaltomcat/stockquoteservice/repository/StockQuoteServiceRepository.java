package com.springbootwsaxis2externaltomcat.stockquoteservice.repository;

import com.springbootwebservice.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class StockQuoteServiceRepository {

  private static final Map<String, Stock> stocks = new HashMap<>();

  @PostConstruct
  public void initData() {
    log.info("Initializing StockQuoteServiceRepository");
    Stock msft = new Stock();
    msft.setSymbol("MSFT");
    msft.setPrice(295.71);

    stocks.put(msft.getSymbol(), msft);

    Stock aapl = new Stock();
    aapl.setSymbol("AAPL");
    aapl.setPrice(148.97);

    stocks.put(aapl.getSymbol(), aapl);

    Stock goog = new Stock();
    goog.setSymbol("GOOG");
    goog.setPrice(2838.42);

    stocks.put(goog.getSymbol(), goog);

    Stock amzn = new Stock();
    amzn.setSymbol("AMZN");
    amzn.setPrice(3469.15);

    stocks.put(amzn.getSymbol(), amzn);
  }

  public Stock getStock(String symbol) {
    return stocks.get(symbol);
  }

  public void updatePrice(String symbol, double price) {
    Stock stock = new Stock();
    stock.setSymbol(symbol);
    stock.setPrice(price);
    stocks.put(symbol, stock);
  }
}
