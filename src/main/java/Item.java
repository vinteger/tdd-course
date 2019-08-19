public class Item {
    private int barcode;
    private String name;
    private int price;
    private String description;

    public Item(int barcode, String name, int price, String description) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "barcode: " + barcode + "\n" +
        "name: " + name + "\n" +
        "price: " + price + "\n" +
        "description: " + description;
    }
}
