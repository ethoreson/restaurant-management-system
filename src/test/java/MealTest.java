import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MealTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void meal_instantiatesCorrectly_true() {
    Meal testMeal = new Meal("Chicken", 6.75f);
    assertEquals(true, testMeal instanceof Meal);
  }

  @Test
  public void getName_mealInstantiatesWithName_true() {
    Meal testMeal = new Meal("Chicken", 6.75f);
    assertEquals("Chicken", testMeal.getName());
  }

  @Test
  public void getPrice_mealInstantiatesWithPrice_true() {
    Meal testMeal = new Meal("Chicken", 6.75f);
    assertEquals(6.75f, testMeal.getPrice(), 0.0);
  }

  @Test
  public void equals_returnsTrueIfNameAndPriceAreSame_true() {
    Meal testMeal = new Meal("Chicken", 6.75f);
    Meal testMeal2 = new Meal("Chicken", 6.75f);
    assertTrue(testMeal.equals(testMeal2));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Meal() {
    Meal testMeal = new Meal("Chicken", 6.75f);
    testMeal.save();
    assertEquals(true, Meal.all().get(0).equals(testMeal));
  }

  @Test
  public void all_returnsAllInstancesOfMeal_true() {
    Meal firstMeal = new Meal("Chicken", 6.75f);
    firstMeal.save();
    Meal secondMeal = new Meal("Steak", 12.50f);
    secondMeal.save();
    assertEquals(true, Meal.all().get(0).equals(firstMeal));
    assertEquals(true, Meal.all().get(1).equals(secondMeal));
  }
  //
  // @Test
  // public void getCustomers_returnsAllCustomers_List() {
  //   Customer testCustomer = new Customer("Customer 1");
  //   testCustomer.save();
  //   Meal testMeal = new Meal("Chicken", 6.75f);
  //   testMeal.save();
  //   testMeal.addCustomer(testCustomer);
  //   List savedCustomers = testMeal.getCustomers();
  //   assertEquals(savedCustomers.size(), 1);
  // }


}
