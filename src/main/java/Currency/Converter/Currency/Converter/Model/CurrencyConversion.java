    package Currency.Converter.Currency.Converter.Model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    import java.time.LocalDateTime;

    @Entity
    public class CurrencyConversion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fromCurrency;
        private String toCurrency;
        private double amount;
        private double convertedAmount;
        private double conversionRate;
        private LocalDateTime timestamp;

        public CurrencyConversion(Long id, String fromCurrency, String toCurrency, double amount, double convertedAmount, double conversionRate, LocalDateTime timestamp) {
            this.id = id;
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.amount = amount;
            this.convertedAmount = convertedAmount;
            this.conversionRate = conversionRate;
            this.timestamp = timestamp;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFromCurrency() {
            return fromCurrency;
        }

        public void setFromCurrency(String fromCurrency) {
            this.fromCurrency = fromCurrency;
        }

        public String getToCurrency() {
            return toCurrency;
        }

        public void setToCurrency(String toCurrency) {
            this.toCurrency = toCurrency;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getConvertedAmount() {
            return convertedAmount;
        }

        public void setConvertedAmount(double convertedAmount) {
            this.convertedAmount = convertedAmount;
        }

        public double getConversionRate() {
            return conversionRate;
        }

        public void setConversionRate(double conversionRate) {
            this.conversionRate = conversionRate;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public CurrencyConversion() {
      }

            }