import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleOneItemTest {
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
        assertThat(display.getDisplayText()).isEqualTo("Total: $1.99");
    }

    @Test
    public void productFoundAgain_displaysPrice() {
        sale.onBarcode(23456);
        assertThat(display.getDisplayText()).isEqualTo("Total: $2.99");
    }

    @Test
    public void nonExistingBarcode_00000_displaysProductNotFound() {
        sale.onBarcode(88888);
        assertThat(display.getDisplayText()).isEqualTo("Product not found for: 88888");
    }

    @Test
    public void nonExistingBarcode_99999_displaysProductNotFound() {
        sale.onBarcode(99999);
        assertThat(display.getDisplayText()).isEqualTo("Product not found for: 99999");
    }

    @Test
    public void null_displaysInvalidInput() {
        sale.onBarcode(null);
        assertThat(display.getDisplayText()).isEqualTo("Invalid input");
    }
}
