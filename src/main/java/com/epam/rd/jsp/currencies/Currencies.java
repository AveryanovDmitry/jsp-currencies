package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Currencies {
    private static final int SCALE_VALUE = 5;
    private static final int LARGE_SCALE_VALUE = 10;
    private Map<String, BigDecimal> curs = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        curs.put(currency, weight);
    }

    public Collection<String> getCurrencies() {
        return curs.keySet();
    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        Map<String, BigDecimal> exchangeRates = new TreeMap<>();
        for (Map.Entry<String,BigDecimal> entry : curs.entrySet()) {
            BigDecimal relativeRate = curs.get(referenceCurrency)
                    .divide(entry.getValue(), SCALE_VALUE, RoundingMode.HALF_UP);
            exchangeRates.put(entry.getKey(), relativeRate);
        }
        return exchangeRates;
    }

    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        BigDecimal relativeRate = curs.get(sourceCurrency)
                .divide(curs.get(targetCurrency), LARGE_SCALE_VALUE, RoundingMode.HALF_UP);
        return amount.multiply(relativeRate, new MathContext(LARGE_SCALE_VALUE))
                .setScale(SCALE_VALUE, RoundingMode.HALF_UP);
    }
}
