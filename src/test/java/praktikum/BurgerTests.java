package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    /**
     * Проверка метода setBuns: проверяет, что булочка устанавливается корректно.
     */
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Булочка не установлена правильно", mockBun, burger.bun);
    }

    /**
     * Проверка метода addIngredient: проверяет, что ингредиент добавляется корректно.
     */
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient);
        assertTrue("Ингредиент не добавлен", burger.ingredients.contains(mockIngredient));
    }

    /**
     * Проверка метода removeIngredient: проверяет, что ингредиент удаляется корректно.
     */
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertFalse("Ингредиент не удален", burger.ingredients.contains(mockIngredient));
    }

    /**
     * Проверка метода moveIngredient: проверяет, что ингредиенты перемещаются корректно.
     */
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);
        assertEquals("Ингредиенты не перемещены правильно", mockIngredient2, burger.ingredients.get(0));
        assertEquals("Ингредиенты не перемещены правильно", mockIngredient, burger.ingredients.get(1));
    }

    /**
     * Проверка метода getPrice с моками: проверяет, что цена бургера рассчитывается корректно.
     * Использует моки для булочки и ингредиентов.
     */
    @Test
    public void testGetPriceWithMocks() {
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        assertEquals("Цена бургера рассчитана неправильно", 250.0f, burger.getPrice(), 0.0f);
    }

    /**
     * Проверка метода getPrice с несколькими ингредиентами.
     */
    @Test
    public void testGetPriceWithMultipleIngredients() {
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);
        Mockito.when(mockIngredient2.getPrice()).thenReturn(75.0f);
        Mockito.when(mockIngredient3.getPrice()).thenReturn(25.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        // Цена = 100*2 + 50 + 75 + 25 = 350
        assertEquals("Цена бургера рассчитана неправильно", 350.0f, burger.getPrice(), 0.0f);
    }

    /**
     * Проверка метода getReceipt с моками.
     */
    @Test
    public void testGetReceiptWithMocks() {
        Mockito.when(mockBun.getName()).thenReturn("black bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredient.getName()).thenReturn("hot sauce");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String expectedReceipt = String.format("(==== %s ====)%n= sauce %s =%n(==== %s ====)%n%nPrice: %f%n",
                "black bun", "hot sauce", "black bun", 250.0f);

        assertEquals("Квитанция о бургере не соответствует ожидаемой", expectedReceipt, burger.getReceipt());
    }

    /**
     * Проверка метода getReceipt с несколькими ингредиентами разных типов.
     */
    @Test
    public void testGetReceiptWithMultipleIngredients() {
        Mockito.when(mockBun.getName()).thenReturn("white bun");
        Mockito.when(mockBun.getPrice()).thenReturn(200.0f);
        
        Mockito.when(mockIngredient.getName()).thenReturn("hot sauce");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient.getPrice()).thenReturn(100.0f);
        
        Mockito.when(mockIngredient2.getName()).thenReturn("cutlet");
        Mockito.when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(mockIngredient2.getPrice()).thenReturn(150.0f);
        
        Mockito.when(mockIngredient3.getName()).thenReturn("sour cream");
        Mockito.when(mockIngredient3.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient3.getPrice()).thenReturn(200.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        // Цена = 200*2 + 100 + 150 + 200 = 850
        String expectedReceipt = String.format("(==== %s ====)%n= sauce %s =%n= filling %s =%n= sauce %s =%n(==== %s ====)%n%nPrice: %f%n",
                "white bun", "hot sauce", "cutlet", "sour cream", "white bun", 850.0f);

        assertEquals("Квитанция о бургере не соответствует ожидаемой", expectedReceipt, burger.getReceipt());
    }
}
