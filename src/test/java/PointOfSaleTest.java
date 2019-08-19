import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointOfSaleTest {

    PointOfSale pos;
    Item[] items = {
            new ItemBuilder(11111111, "Keyboard", 2500, "User interface for getting input to the computer.").build(),
            new ItemBuilder(22222222, "Mouse", 1000, "Pointer for a computer's GUI").build(),
            new ItemBuilder(33333333, "Monitor", 10000, "Display unit for the computer").build()
    };

    @Before
    public void setUp() {
        pos = new PointOfSale();
    }

    @Test
    public void nonExistingBarcode_displays_ItemNotFound() {
        final int barcodeFromScanner = 12341234;

        pos.onBarcode(barcodeFromScanner);

        assertEquals("Item Not Found", pos.displayText);
    }

    @Test
    public void correctBarcode_displaysItemInfo() {
        int expectedBarcode = 11111111;

        String expectedDisplayText =
                displayTextBuilder(expectedBarcode, items[0].getName(), items[0].getPrice(), items[0].getDescription());

        pos.onBarcode(expectedBarcode);

        assertEquals(expectedDisplayText, pos.displayText);
    }

    @Test
    public void correctBarcode_displaysItemInfo_2() {
        int expectedBarcode = 22222222;

        String expectedDisplayText =
                displayTextBuilder(expectedBarcode, items[1].getName(), items[1].getPrice(), items[1].getDescription());

        pos.onBarcode(expectedBarcode);

        assertEquals(expectedDisplayText, pos.displayText);
    }

    private String displayTextBuilder(int barcode, String name, int price, String description) {
        return "SKU: " + barcode + "\n" +
                "Item: " + name + "\n" +
                "Price: " + price + "\n" +
                "Description: " + description;
    }
}
