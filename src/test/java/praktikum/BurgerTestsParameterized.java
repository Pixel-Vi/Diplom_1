package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerTestsParameterized {
    @Mock
    private Bun mockBun;

    private float bunPrice;

    public BurgerTestsParameterized(float bunPrice) {
        this.bunPrice = bunPrice;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Параметризированный тест для проверки метода setBuns с разными ценами булочек.
     */
    @Test
    public void testSetBunsWithDifferentPrices() {
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        // Проверяем, что булочка установлена правильно
        assertEquals("Булочка не установлена правильно", mockBun, burger.bun);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100.0f},
                {150.0f},
                {200.0f},
                {250.0f}
        });
    }
}
