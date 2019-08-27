import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleMultipleItemsTest {

    private Sale sale;
    private Display display;
    private Map<Integer, BigDecimal> priceByBarcode = new HashMap<Integer, BigDecimal>() {{
       put(12345, BigDecimal.valueOf(10.00));
       put(23456, BigDecimal.valueOf(15.00));
       put(34567, BigDecimal.valueOf(5.00));
       put(11111, BigDecimal.valueOf(0.01));
    }};

    @Before
    public void setUp() {
        Catalog catalog = new Catalog(priceByBarcode);
        display = new Display();
        sale = new Sale(display, catalog);
    }

    @Test
    public void zeroItems() {
        sale.getTotal();

        assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
    }

    @Test
    public void sellOneItem() {
        sale.onBarcode(23456);

        sale.getTotal();

        assertThat(display.getText()).isEqualTo("Total: $15.00");
    }

    @Test
    public void sellOneItem_notFound() {
        Catalog catalog = new Catalog(Collections.singletonMap(null, null));
        sale = new Sale(display, catalog);
        sale.onBarcode(22222);

        sale.getTotal();

        assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
    }

    @Test
    public void sellSeveralItems_trailingZeros() {
        sale.onBarcode(12345);
        sale.onBarcode(23456);
        sale.onBarcode(34567);

        sale.getTotal();

        assertThat(display.getText()).isEqualTo("Total: $30.00");
    }

    @Test
    public void sellSeveralItems_LeadingZeros() {
        sale.onBarcode(23456);
        sale.onBarcode(34567);
        sale.onBarcode(11111);

        sale.getTotal();

        assertThat(display.getText()).isEqualTo("Total: $20.01");
    }
}
