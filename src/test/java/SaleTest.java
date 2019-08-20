import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleTest {
    Sale sale;
    Display display;
    Catalog catalog;
    Map<Integer, BigDecimal> priceByBarcode = new HashMap<Integer, BigDecimal>() {{
        put(12345, BigDecimal.valueOf(1.99));
        put(23456, BigDecimal.valueOf(2.99));
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
        assertThat(display.getText()).isEqualTo("1.99");
    }

    @Test
    public void productFoundAgain_displaysPrice() {
        sale.onBarcode(23456);
        assertThat(display.getText()).isEqualTo("2.99");
    }

    @Test
    public void nonExistingBarcode_00000_displaysProductNotFound() {
        sale.onBarcode(88888);
        assertThat(display.getText()).isEqualTo("Product not found for: 88888");
    }

    @Test
    public void nonExistingBarcode_99999_displaysProductNotFound() {
        sale.onBarcode(99999);
        assertThat(display.getText()).isEqualTo("Product not found for: 99999");
    }

    @Test
    public void null_displaysInvalidInput() {
        sale.onBarcode(null);
        assertThat(display.getText()).isEqualTo("Invalid input");
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
            return catalog.getPriceByBarcode().get(barcode).toString();
        }
    }

    public class Display {
        String text;

        public String getText() {
            return text;
        }

        private void setPriceAsText(String priceAsText) {
            this.text = priceAsText;
        }

        public void displayProductNotFoundForBarcode(Integer barcode) {
            setPriceAsText("Product not found for: " + barcode);
        }

        public void displayInvalidInput() {
            setPriceAsText("Invalid input");
        }
    }

    public class Catalog {
        private Map<Integer, BigDecimal> priceByBarcode;

        public Catalog(Map<Integer, BigDecimal> priceByBarcode) {
            this.priceByBarcode = priceByBarcode;
        }

        public Map<Integer, BigDecimal> getPriceByBarcode() {
            return priceByBarcode;
        }
    }
}
