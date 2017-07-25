import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class ReceiptTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void receipt_instantiatesCorrectly_true() {
    Receipt testReceipt = new Receipt(6.75f);
    assertEquals(true, testReceipt instanceof Receipt);
  }

  @Test
  public void Receipt_instantiatesWithMealTotal_Float() {
    Receipt testReceipt = new Receipt(6.75f);
    assertEquals(6.75f, testReceipt.getMealTotal(), 0.0);
  }

  @Test
  public void save_successfullyAddsReceiptToDatabase_List() {
    Receipt testReceipt = new Receipt(6.75f);
    testReceipt.save();
    assertTrue(Receipt.all().get(0).equals(testReceipt));
  }

  @Test
  public void save_assignsIdToReceipt() {
    Receipt testReceipt = new Receipt(6.75f);
    testReceipt.save();
    Receipt savedReceipt = Receipt.all().get(0);
    assertEquals(savedReceipt.getId(), testReceipt.getId());
  }

  @Test
  public void all_returnsAllInstancesOfReceipt_true() {
    Receipt firstReceipt = new Receipt(6.75f);
    firstReceipt.save();
    Receipt secondReceipt = new Receipt(8.50f);
    secondReceipt.save();
    assertEquals(true, Receipt.all().get(0).equals(firstReceipt));
    assertEquals(true, Receipt.all().get(1).equals(secondReceipt));
  }

  @Test
  public void find_returnsReceiptWithSameId_secondReceipt() {
    Receipt firstReceipt = new Receipt(6.75f);
    firstReceipt.save();
    Receipt secondReceipt = new Receipt(8.50f);
    secondReceipt.save();
    assertEquals(Receipt.find(secondReceipt.getId()), secondReceipt);
  }

  // @Test
  // public void getCustomers_retrievesAllCustomersFromDatabase_customersList() {
  //   Receipt testReceipt = new Receipt("Henry's Receipt", 5);
  //   testReceipt.save();
  //   Customer firstCustomer = new Customer("Bubbles", testReceipt.getId());
  //   firstCustomer.save();
  //   Customer secondCustomer = new Customer("Spud", testReceipt.getId());
  //   secondCustomer.save();
  //   Customer[] customers = new Customer[] { firstCustomer, secondCustomer };
  //   assertTrue(testReceipt.getCustomers().containsAll(Arrays.asList(customers)));
  // }
}
