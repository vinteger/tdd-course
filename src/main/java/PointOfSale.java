public class PointOfSale {

    public String displayText = "";

    private Item[] itemInventory = {
            new ItemBuilder(11111111, "Keyboard", 2500, "User interface for getting input to the computer.").build(),
            new ItemBuilder(22222222, "Mouse", 1000, "Pointer for a computer's GUI").build(),
            new ItemBuilder(33333333, "Monitor", 10000, "Display unit for the computer").build()};

    public void onBarcode(int barcodeFromScanner) {
        for (Item item : itemInventory) {
            if (barcodeFromScanner == item.getBarcode()) {
                setTextToDisplay(barcodeFromScanner,
                        item.getName(),
                        item.getPrice(),
                        item.getDescription());
                break;
            } else {
                displayText = "Item Not Found";
            }
        }
    }

    private void setTextToDisplay(int barcode, String name, int price, String description) {
        displayText = "SKU: " + barcode + "\n" +
                "Item: " + name + "\n" +
                "Price: " + price + "\n" +
                "Description: " + description;
    }
}

class PointOfSaleDemo {
    public static void main(String[] args) {
        PointOfSale pos = new PointOfSale();
        int monitor = 33333333;

        pos.onBarcode(monitor);
        System.out.println(pos.displayText);
    }
}
