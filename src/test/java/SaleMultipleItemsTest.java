import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SaleMultipleItemsTest {

    private Sale sale;
    private Display display;
    private Catalog catalog;
    private Map<Integer, BigDecimal> priceByBarcode = new HashMap<Integer, BigDecimal>() {{
       put(12345, BigDecimal.valueOf(10.00));
       put(23456, BigDecimal.valueOf(15.00));
       put(34567, BigDecimal.valueOf(5.00));
    }};

    @Before
    public void setUp() {
        catalog = new Catalog(priceByBarcode);
        display = new Display();
        sale = new Sale(display, catalog);
    }

    @Test
    public void multipleItems_displaysTotal() {
    }

}
