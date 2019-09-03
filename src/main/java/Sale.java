import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sale {

    private Display display;
    private Catalog catalog;
    private List<BigDecimal> priceTotal = new ArrayList<>();
    private boolean saleInProgress = false;

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(Integer barcode) {

        if (barcode == null) {
            display.displayInvalidInputFromScan();
            return;
        }

        BigDecimal scannedPrice = findPriceByBarcode(barcode);

        if (scannedPrice != null) {
            priceTotal.add(scannedPrice);
            saleInProgress = true;
            updateCurrentTotalText(barcode);
        } else {
            display.displayProductNotFoundForBarcodeFromScan(barcode);
        }
    }

    public void getSaleTotal() {

        if (saleInProgress) {
            display.displayTotalPurchase(getCurrentTotal());
        } else {
            display.displayNoSaleInProgress();
        }
    }

    private BigDecimal findPriceByBarcode(Integer barcode) {
        return catalog.getPrice(barcode);
    }

    private String findPrice(Integer barcode) {
        BigDecimal priceFromInventory = catalog.getPrice(barcode);
        return formatMoney(priceFromInventory);
    }

    private void updateCurrentTotalText(Integer barcode) {
        display.displayProductPriceFromScan("$" + findPrice(barcode));
    }

    private String getCurrentTotal() {
        if(!priceTotal.isEmpty()) {
            Optional<BigDecimal> total = priceTotal.stream().reduce(BigDecimal::add);
            return formatMoney(total.get());
        } else {
            return "";
        }
    }

    private String formatMoney(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();

    }
}
