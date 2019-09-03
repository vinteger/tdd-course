import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleMultipleItemsTest {

    private final static String NO_SALE_IN_PROGRESS = "No sale in progress. Try scanning a product.";

    private Display display;
    private Sale sale;
    private Map<Integer, BigDecimal> priceByBarcode = new HashMap<Integer, BigDecimal>() {{
        put(15000, BigDecimal.valueOf(15.00));
        put(12345, BigDecimal.valueOf(1.00));
        put(23456, BigDecimal.valueOf(2.00));
        put(44444, BigDecimal.valueOf(0.40));
        put(55555, BigDecimal.valueOf(0.05));
    }};

    @Before
    public void setUp() {
        display = new Display();
        Catalog catalog = new Catalog(priceByBarcode);
        sale = new Sale(display, catalog);
    }

    @Test
    public void zeroItems() {
        Map<Integer, BigDecimal> priceByBarcode = Collections.singletonMap(null, null);
        Catalog catalog = new Catalog(priceByBarcode);
        Sale sale = new Sale(display, catalog);

        sale.getTotal();

        assertThat(display.getTotal()).isEqualTo(NO_SALE_IN_PROGRESS);
    }

    @Test
    public void sellOneItem() {
        sale.onBarcode(15000);

        sale.getTotal();

        assertThat(display.getTotal()).isEqualTo("Total: $15.00");
    }

    @Test
    public void sellOneItem_notFound() {
        Catalog catalog = new Catalog(Collections.singletonMap(null, null));
        Sale sale = new Sale(display, catalog);
        sale.onBarcode(22222);

        sale.getTotal();

        assertThat(display.getTotal()).isEqualTo(NO_SALE_IN_PROGRESS);
    }

    @Test
    public void sellSeveralItems_trailingZeros() {
        sale.onBarcode(12345);
        sale.onBarcode(23456);

        sale.getTotal();

        assertThat(display.getTotal()).isEqualTo("Total: $3.00");
    }

    @Test
    public void sellSeveralItems_LeadingZeros() {
        sale.onBarcode(44444);
        sale.onBarcode(55555);

        sale.getTotal();

        assertThat(display.getTotal()).isEqualTo("Total: $0.45");
    }

    @Test
    public void sellSeveralItems_OneNotFound() {
        sale.onBarcode(15000);
        sale.onBarcode(12345);
        sale.onBarcode(99999);
        String resultFromLastScan = display.getTotal();

        sale.getTotal();

        assertThat(display.getTotal()).isEqualTo("Total: $16.00");
        assertThat(resultFromLastScan).isEqualTo("Product not found for: 99999");
    }
}
