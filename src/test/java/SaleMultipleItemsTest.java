import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleMultipleItemsTest {

    private Sale sale;
    private Display display;
    private Map<Integer, BigDecimal> priceByBarcode = new HashMap<Integer, BigDecimal>() {{
       put(12345, BigDecimal.valueOf(10.00));
       put(23456, BigDecimal.valueOf(15.00));
       put(34567, BigDecimal.valueOf(5.00));
    }};

    @Before
    public void setUp() {
        Catalog catalog = new Catalog(priceByBarcode);
        display = new Display();
        sale = new Sale(display, catalog);
    }

    @Test
    public void zeroItems() {
        sale.onTotal();

        assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
    }

    @Test
    public void sellOneItem() {
        Catalog catalog = new Catalog(Collections.singletonMap(11111, BigDecimal.valueOf(6.50)));
        sale = new Sale(display, catalog);
        sale.onBarcode(11111);

        sale.onTotal();

        assertThat(display.getText()).isEqualTo("Total: $6.50");
    }
}
