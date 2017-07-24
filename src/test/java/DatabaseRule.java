import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/restaurant_management_system_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCustomersQuery = "DELETE FROM customers *;";
      String deleteTablesQuery = "DELETE FROM tables *;";
      String deleteReceiptsQuery = "DELETE FROM receipts *;";
      String deleteMealsQuery = "DELETE FROM meals *;";
      String deleteJoinsQuery = "DELETE FROM customers_meals *;";
      con.createQuery(deleteCustomersQuery).executeUpdate();
      con.createQuery(deleteTablesQuery).executeUpdate();
      con.createQuery(deleteReceiptsQuery).executeUpdate();
      con.createQuery(deleteMealsQuery).executeUpdate();
      con.createQuery(deleteJoinsQuery).executeUpdate();
    }
  }

}
