package Currency.Converter.Currency.Converter.Service;

import Currency.Converter.Currency.Converter.Model.CurrencyConversion;
import Currency.Converter.Currency.Converter.Repository.CurrenyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyConversionService {

    @Autowired
    private CurrenyRepository repository;

    // Hardcoded exchange rates for simplicity (you can fetch from API later)
    private static final double USD_TO_INR = 83.0;
    private static final double INR_TO_USD = 0.012;
    private static final double USD_TO_EUR = 0.92;
    private static final double EUR_TO_USD = 1.09;
    private static final double INR_TO_EUR = 0.011;
    private static final double EUR_TO_INR = 90.0;

    public CurrencyConversion convert(String fromCurrency, String toCurrency, double amount) {
        double conversionRate = getConversionRate(fromCurrency, toCurrency);
        double convertedAmount = amount * conversionRate;

        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setFromCurrency(fromCurrency);
        conversion.setToCurrency(toCurrency);
        conversion.setAmount(amount);
        conversion.setConvertedAmount(convertedAmount);
        conversion.setConversionRate(conversionRate);
        conversion.setTimestamp(LocalDateTime.now());

        return repository.save(conversion);
    }

    public List<CurrencyConversion> getAllConversions() {
        return repository.findAll();
    }

    public Optional<CurrencyConversion> getConversionById(Long id) {
        return repository.findById(id);
    }

    public boolean deleteConversion(Long id){
        Optional<CurrencyConversion> optionalConversion = repository.findById(id);
        if (optionalConversion.isPresent()){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
    public List<CurrencyConversion> filterConversions(String from, String to) {
        if (from != null && to != null) {
            return repository.findByFromCurrencyAndToCurrency(from, to);
        } else if (from != null) {
            return repository.findByFromCurrency(from);
        } else if (to != null) {
            return repository.findByToCurrency(to);
        } else {
            return repository.findAll();
        }
    }



    private double getConversionRate(String from, String to) {
        if (from.equalsIgnoreCase("USD") && to.equalsIgnoreCase("INR")) return USD_TO_INR;
        if (from.equalsIgnoreCase("INR") && to.equalsIgnoreCase("USD")) return INR_TO_USD;
        if (from.equalsIgnoreCase("USD") && to.equalsIgnoreCase("EUR")) return USD_TO_EUR;
        if (from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase("USD")) return EUR_TO_USD;
        if (from.equalsIgnoreCase("INR") && to.equalsIgnoreCase("EUR")) return INR_TO_EUR;
        if (from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase("INR")) return EUR_TO_INR;

        throw new IllegalArgumentException("Unsupported currency conversion: " + from + " to " + to);
    }


}
