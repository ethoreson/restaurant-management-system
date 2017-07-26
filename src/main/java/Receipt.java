import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
  private Float meal_total;
  private int id;
  private int tableId;
  private String name;

  public Receipt(int tableId, String name) {
    this.tableId = tableId;
    this.name = name;
  }

  public int getTableId() {
    return tableId;
  }

  public String getName() {
    return name;
  }

  public Float getMealTotal() {
    return meal_total;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherReceipt){
    if (!(otherReceipt instanceof Receipt)) {
      return false;
    } else {
      Receipt newReceipt = (Receipt) otherReceipt;
      return this.getMealTotal().equals(newReceipt.getMealTotal());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO receipts (meal_total, tableid, name) VALUES (:meal_total, :tableid, :name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("meal_total", this.meal_total)
        .addParameter("tableid", this.tableId)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Receipt> all() {
    String sql = "SELECT * FROM receipts";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Receipt.class);
    }
  }

  public static Receipt find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM receipts where id=:id";
      Receipt receipt = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Receipt.class);
      return receipt;
    }
  }

  public void addCustomer(Customer customer) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO customers_receipts (customer_id, receipt_id) VALUES (:customer_id, :receipt_id);";
      con.createQuery(sql)
      .addParameter("customer_id", customer.getId())
      .addParameter("receipt_id", this.getId())
      .executeUpdate();
    }
  }

  public List<Customer> getCustomers() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT customer_id FROM customers_receipts WHERE receipt_id=:id";
      List<Integer> customerIds = con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Integer.class);
      List<Customer> customers = new ArrayList<Customer>();
      for (Integer id : customerIds) {
        String customerSql = "SELECT * FROM customers WHERE id = :id;";
        Customer customer = con.createQuery(customerSql)
          .addParameter("id", id)
          .executeAndFetchFirst(Customer.class);
        customers.add(customer);
      }
      return customers;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM receipts WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
