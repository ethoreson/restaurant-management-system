import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
  private Float meal_total;
  private int id;

  public Receipt(Float meal_total) {
    this.meal_total = meal_total;
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
      String sql = "INSERT INTO receipts (meal_total) VALUES (:meal_total)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("meal_total", this.meal_total)
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

  // public List<Customer> getCustomers() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT * FROM customers where table_id=:id";
  //     return con.createQuery(sql)
  //       .addParameter("id", this.id)
  //       .executeAndFetch(Customer.class);
  //   }
  // }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM receipts WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
