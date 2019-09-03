public class Display {
    private String total;
    private String textOnScan;

    public String getTextOnScan() {
        return textOnScan;
    }

    private void setTextOnScan(String textOnScan) {
        this.textOnScan = textOnScan;
    }

    public String getTotal() {
        return total;
    }

    private void setTotal(String total) {
        this.total = total;
    }

    public void displayProductPrice(String priceAsText) {
        setTextOnScan(priceAsText);
    }

    public void displayTotalPurchase(String price) {
        setTotal("Total: $" + price);
    }

    public void displayProductNotFoundForBarcode(Integer barcode) {
        setTextOnScan("Product not found for: " + barcode);
    }

    public void displayInvalidInput() {
        setTextOnScan("Invalid input");
    }

    public void displayNoSaleInProgress() {
        setTotal("No sale in progress. Try scanning a product.");
    }
}
