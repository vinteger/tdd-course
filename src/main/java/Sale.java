import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sale {
    private Display display;
    private Catalog catalog;
    private List<BigDecimal> priceTotal = new ArrayList<>();

    private BigDecimal scannedPrice;

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(Integer barcode) {

        if (barcode == null) {
            display.displayInvalidInput();
            return;
        }

        scannedPrice = findPriceByBarcode(barcode);

        if (scannedPrice != null) {
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
        display.displayProductPrice("$" + getCurrentTotal().toString());
    }

    public void getTotal() {

        boolean saleInProgress = scannedPrice != null;
        if (saleInProgress) {
            display.displayTotalPurchase(getCurrentTotal().toString());
        } else {
            display.displayNoSaleInProgress();
        }
    }

    private BigDecimal getCurrentTotal() {
        Optional<BigDecimal> total = priceTotal.stream().reduce(BigDecimal::add);
        return total.get().setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
