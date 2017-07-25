import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CustomerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void addMeal_addsMealToCustomer() {
    Customer testCustomer = new Customer("Customer 1", 1);
    testCustomer.save();
    Meal testMeal = new Meal("Chicken", 6.75f);
    testMeal.save();
    testCustomer.addMeal(testMeal);
    Meal savedMeal = testCustomer.getMeals().get(0);
    assertTrue(testMeal.equals(savedMeal));
  }

  @Test
  public void getMeals_returnsAllMeals_List() {
    Customer testCustomer = new Customer("Customer 1", 1);
    testCustomer.save();
    Meal testMeal = new Meal("Chicken", 6.75f);
    testMeal.save();
    testCustomer.addMeal(testMeal);
    testMeal.getCustomers();
    System.out.println(testMeal.getCustomers());
    List savedMeals = testCustomer.getMeals();
    assertEquals(1, savedMeals.size());
  }
}
