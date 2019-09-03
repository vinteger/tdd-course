public class Display {

    private String displayTotal;
    private String textOnScan;

    public String getTextOnScan() {
        return textOnScan;
    }

    private void setTextOnScan(String textOnScan) {
        this.textOnScan = textOnScan;
    }

    public String getDisplayTotal() {
        return displayTotal;
    }

    private void setDisplayTotal(String displayTotal) {
        this.displayTotal = displayTotal;
    }

    void displayNoSaleInProgress() {
        setDisplayTotal("No sale in progress. Try scanning a product.");
    }

    void displayTotalPurchase(String price) {
        setDisplayTotal("Total: $" + price);
    }

    void displayProductPriceFromScan(String priceAsText) {
        setTextOnScan(priceAsText);
    }

    void displayProductNotFoundForBarcodeFromScan(Integer barcode) {
        setTextOnScan("Product not found for: " + barcode);
    }

    void displayInvalidInputFromScan() {
        setTextOnScan("Invalid input");
    }
}
