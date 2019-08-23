public class Display {
    private String text;

    public String getDisplayText() {
        return text;
    }

    public void setDisplayText(String text) {
        this.text = text;
    }

    public void displayProductPrice(String priceAsText) {
        setDisplayText("Total: $" + priceAsText);
    }

    public void displayProductNotFoundForBarcode(Integer barcode) {
        setDisplayText("Product not found for: " + barcode);
    }

    public void displayInvalidInput() {
        setDisplayText("Invalid input");
    }
}
