import java.math.BigDecimal;
import java.util.Map;

public class Catalog {
    private Map<Integer, BigDecimal> priceByBarcode;

    public Catalog(Map<Integer, BigDecimal> priceByBarcode) {
        this.priceByBarcode = priceByBarcode;
    }

    public Map<Integer, BigDecimal> getPriceByBarcode() {
        return priceByBarcode;
    }

    public BigDecimal getPrice(Integer barcode) {
        return priceByBarcode.get(barcode);
    }
}
