import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Table {
  private String name;
  private int guestCount;
  private int id;


  public Table(String name, int guestCount) {
    this.name = name;
    this.guestCount = guestCount;
  }

  public String getName() {
    return name;
  }

  public int getGuestCount() {
    return guestCount;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherTable){
    if (!(otherTable instanceof Table)) {
      return false;
    } else {
      Table newTable = (Table) otherTable;
      return this.getName().equals(newTable.getName()) &&
             this.getGuestCount() == newTable.getGuestCount();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tables (name, guestcount) VALUES (:name, :guestcount)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("guestcount", this.guestCount)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Table> all() {
    String sql = "SELECT * FROM tables";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Table.class);
    }
  }

  public static Table find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tables where id=:id";
      Table table = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Table.class);
      return table;
    }
  }

  public List<Customer> getCustomers() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM customers where table_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Customer.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM tables WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
