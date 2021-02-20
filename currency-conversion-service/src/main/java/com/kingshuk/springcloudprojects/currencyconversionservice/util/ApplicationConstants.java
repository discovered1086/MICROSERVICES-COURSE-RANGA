package com.kingshuk.springcloudprojects.currencyconversionservice.util;

public class ApplicationConstants {

    private ApplicationConstants(){
        throw new UnsupportedOperationException("This is not allowed");
    }

    public static final String EXCHANGE_URL="http://localhost:8001/currency-exchange/from/{fromCurrency}/to/{toCurrency}";
}
