public class Display {
    private String text;

    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    public void displayProductPrice(String priceAsText) {
        setText(priceAsText);
    }

    public void displayTotalPurchase(String price) {
        setText("Total: $" + price);
    }

    public void displayProductNotFoundForBarcode(Integer barcode) {
        setText("Product not found for: " + barcode);
    }

    public void displayInvalidInput() {
        setText("Invalid input");
    }

    public void displayNoSaleInProgress() {
        setText("No sale in progress. Try scanning a product.");
    }
}
