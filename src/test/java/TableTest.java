import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class TableTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void table_instantiatesCorrectly_true() {
    Table testTable = new Table("Table A", 2);
    assertEquals(true, testTable instanceof Table);
  }

  @Test
  public void Table_instantiatesWithName_String() {
    Table testTable = new Table("Table A", 2);
    assertEquals("Table A", testTable.getName());
  }

  @Test
  public void equals_returnsTrueIfNameAndGuestCountAreSame_true() {
    Table testTable = new Table("Table A", 2);
    Table anotherTable = new Table("Table A", 2);
    assertTrue(testTable.equals(anotherTable));
  }

  @Test
  public void save_successfullyAddsTableToDatabase_List() {
    Table testTable = new Table("Table A", 2);
    testTable.save();
    assertTrue(Table.all().get(0).equals(testTable));
  }

  @Test
  public void save_assignsIdToTable() {
    Table testTable = new Table("Table A", 2);
    testTable.save();
    Table savedTable = Table.all().get(0);
    assertEquals(savedTable.getId(), testTable.getId());
  }

  @Test
  public void all_returnsAllInstancesOfTable_true() {
    Table firstTable = new Table("Table A", 2);
    firstTable.save();
    Table secondTable = new Table("Table B", 3);
    secondTable.save();
    assertEquals(true, Table.all().get(0).equals(firstTable));
    assertEquals(true, Table.all().get(1).equals(secondTable));
  }

  @Test
  public void find_returnsTableWithSameId_secondTable() {
    Table firstTable = new Table("Table A", 2);
    firstTable.save();
    Table secondTable = new Table("Table B", 3);
    secondTable.save();
    assertEquals(Table.find(secondTable.getId()), secondTable);
  }

  @Test
  public void getCustomers_retrievesAllCustomersFromDatabase_customersList() {
    Table testTable = new Table("Henry's Table", 5);
    testTable.save();
    Customer firstCustomer = new Customer("Bubbles", testTable.getId());
    firstCustomer.save();
    Customer secondCustomer = new Customer("Spud", testTable.getId());
    secondCustomer.save();
    Customer[] customers = new Customer[] { firstCustomer, secondCustomer };
    assertTrue(testTable.getCustomers().containsAll(Arrays.asList(customers)));
  }
}
