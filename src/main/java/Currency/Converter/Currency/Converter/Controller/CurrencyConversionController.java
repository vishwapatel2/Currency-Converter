package Currency.Converter.Currency.Converter.Controller;

import Currency.Converter.Currency.Converter.Model.CurrencyConversion;
import Currency.Converter.Currency.Converter.Service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversions")
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService service;

    // POST: Convert currency
    @PostMapping("/convert")
    public ResponseEntity<CurrencyConversion> convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {

        CurrencyConversion result = service.convert(from, to, amount);
        return ResponseEntity.ok(result);
    }

    // GET: Get all conversions
    @GetMapping
    public ResponseEntity<List<CurrencyConversion>> getAllConversions() {
        return ResponseEntity.ok(service.getAllConversions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConversionById(@PathVariable Long id) {
        boolean deleted = service.deleteConversion(id);  // Correct use of service instance
        if (deleted) {
            return ResponseEntity.ok("Conversion with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conversion not found.");
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<CurrencyConversion>> filterByCurrency(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {

        List<CurrencyConversion> filtered = service.filterConversions(from, to);
        return ResponseEntity.ok(filtered);
    }



    // GET: Get conversion by ID
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyConversion> getConversionById(@PathVariable Long id) {
        return service.getConversionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


