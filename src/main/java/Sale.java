public class Sale {
    private Display display;
    private Catalog catalog;

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(Integer barcode) {
        if (barcode == null) {
            display.displayInvalidInput();
            return;
        }

        if (hasBarcode(barcode)) {
            setTextByBarcode(barcode);
        } else {
            display.displayProductNotFoundForBarcode(barcode);
        }
    }

    private boolean hasBarcode(Integer barcode) {
        return catalog.getPriceByBarcode().containsKey(barcode);
    }

    private void setTextByBarcode(Integer barcode) {
        display.setPriceAsText("Total: $" + findPrice(barcode));
    }

    private String findPrice(Integer barcode) {
        return catalog.getPriceByBarcode().get(barcode).toString();
    }
}
