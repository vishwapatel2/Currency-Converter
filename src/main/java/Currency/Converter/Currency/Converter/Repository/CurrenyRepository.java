package Currency.Converter.Currency.Converter.Repository;

import Currency.Converter.Currency.Converter.Model.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrenyRepository extends JpaRepository<CurrencyConversion, Long> {


    void deleteById(Long id);

    Optional<CurrencyConversion> findById(Long id);

    List<CurrencyConversion> findByToCurrency(String toCurrency);
    List<CurrencyConversion> findByFromCurrency(String fromCurrency);
    List<CurrencyConversion> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

}
