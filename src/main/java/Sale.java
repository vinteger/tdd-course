import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sale {
    private Display display;
    private Catalog catalog;
    private List<BigDecimal> priceTotal = new ArrayList<>();

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(Integer barcode) {
        if (barcode == null) {
            display.displayInvalidInput();
            return;
        }

        if (findPriceByBarcode(barcode) != null) {
            priceTotal.add(findPriceByBarcode(barcode));
            updateCurrentTotalText();
        } else {
            display.displayProductNotFoundForBarcode(barcode);
        }
    }

    private BigDecimal findPriceByBarcode(Integer barcode) {
        return catalog.getPrice(barcode);
    }

    private void updateCurrentTotalText() {
        Optional<BigDecimal> total = priceTotal.stream().reduce(BigDecimal::add);
        BigDecimal totalAsDecimal = total.get().setScale(2, BigDecimal.ROUND_HALF_EVEN);

        display.displayProductPrice(totalAsDecimal.toString());
    }
}
