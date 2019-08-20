import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleTest {
    Sale sale;
    Display display;
    Catalog catalog;
    Map<Integer, String> priceByBarcode = new HashMap<Integer, String>() {{
        put(12345, "$1.99");
        put(23456, "$2.99");
    }};

    @Before
    public void setUp() {
        display = new Display();
        catalog = new Catalog(priceByBarcode);
        sale = new Sale(display, catalog);
    }

    @Test
    public void productFound_displaysPrice() {
        sale.onBarcode(12345);
        assertThat(display.displayPrice()).isEqualTo("$1.99");
    }

    @Test
    public void productFoundAgain_displaysPrice() {
        sale.onBarcode(23456);
        assertThat(display.displayPrice()).isEqualTo("$2.99");
    }

    @Test
    public void nonExistingBarcode_00000_displaysProductNotFound() {
        sale.onBarcode(88888);
        assertThat(display.displayPrice()).isEqualTo("Product not found for: 88888");
    }

    @Test
    public void nonExistingBarcode_99999_displaysProductNotFound() {
        sale.onBarcode(99999);
        assertThat(display.displayPrice()).isEqualTo("Product not found for: 99999");
    }

    @Test
    public void null_displaysInvalidInput() {
        sale.onBarcode(null);
        assertThat(display.displayPrice()).isEqualTo("Invalid input");
    }

    public class Sale {
        Display display;
        Catalog catalog;

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
            display.setPriceAsText(findPrice(barcode));
        }

        private String findPrice(Integer barcode) {
            return catalog.getPriceByBarcode().get(barcode);
        }
    }

    public class Display {
        String priceAsText;

        public String displayPrice() {
            return priceAsText;
        }

        public void setPriceAsText(String priceAsText) {
            this.priceAsText = priceAsText;
        }

        public void displayProductNotFoundForBarcode(Integer barcode) {
            setPriceAsText("Product not found for: " + barcode);
        }

        public void displayInvalidInput() {
            setPriceAsText("Invalid input");
        }
    }

    public class Catalog {
        private Map<Integer, String> priceByBarcode;

        public Catalog(Map<Integer, String> priceByBarcode) {
            this.priceByBarcode = priceByBarcode;
        }

        public Map<Integer, String> getPriceByBarcode() {
            return priceByBarcode;
        }
    }
}
