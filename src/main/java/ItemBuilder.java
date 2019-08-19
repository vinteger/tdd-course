public class ItemBuilder {

    private int barcode;
    private String name;
    private int price;
    private String description;

    public ItemBuilder(int barcode, String name, int price, String description) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Item build() {
        return new Item(barcode, name, price, description);
    }

}
