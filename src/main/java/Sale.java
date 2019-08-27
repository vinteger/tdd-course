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
            updateCurrentTotalText(barcode); //TODO fix?
        } else {
            display.displayProductNotFoundForBarcode(barcode);
        }
    }

    public void getTotal() {

        boolean saleInProgress = scannedPrice != null;
        if (saleInProgress) {
            display.displayTotalPurchase(getCurrentTotal());
        } else {
            display.displayNoSaleInProgress();
        }
    }

    private void updateCurrentTotalText(Integer barcode) {
        display.displayProductPrice("$" + findPrice(barcode)); //TODO fix?
    }

    private BigDecimal findPriceByBarcode(Integer barcode) {
        return catalog.getPrice(barcode);
    }

    private String findPrice(Integer barcode) { //TODO fix?
        return catalog.getPrice(barcode).toString();
    }

    private String getCurrentTotal() {
        Optional<BigDecimal> total = priceTotal.stream().reduce(BigDecimal::add);
        return total.get().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    }
}
