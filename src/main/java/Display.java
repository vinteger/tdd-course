public class Display {
    private String text;

    public String getDisplayText() {
        return text;
    }

    public void setPriceAsText(String priceAsText) {
        this.text = priceAsText;
    }

    public void displayProductNotFoundForBarcode(Integer barcode) {
        setPriceAsText("Product not found for: " + barcode);
    }

    public void displayInvalidInput() {
        setPriceAsText("Invalid input");
    }
}
