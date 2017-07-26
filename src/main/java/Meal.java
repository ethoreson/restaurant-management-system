import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Meal {
  private String name;
  private Float price;
  private int id;

  public Meal(String name, Float price){
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public Float getPrice() {
    return price;
  }

  public int getId() {
    return id;
  }

  public String displayTwoDecimals() {
    return String.format("%.2f", price);
  }

  @Override
  public boolean equals(Object otherMeal){
    if (!(otherMeal instanceof Meal)) {
      return false;
    } else {
    Meal newMeal = (Meal) otherMeal;
    return this.getName().equals(newMeal.getName());
   }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO meals (name, price) VALUES (:name, :price)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("price", this.price)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Meal> all() {
    String sql = "SELECT * FROM meals";
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Meal.class);
    }
  }

  public static Meal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM meals where id=:id";
      Meal meal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Meal.class);
      return meal;
    }
  }

  public List<Customer> getCustomers() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT customer_id FROM customers_meals WHERE meal_id = :meal_id";
      List<Integer> customerIds = con.createQuery(joinQuery)
        .addParameter("meal_id", this.getId())
        .executeAndFetch(Integer.class);
      List<Customer> customers = new ArrayList<Customer>();
      for (Integer customerId : customerIds) {
        String customerQuery = "SELECT * FROM customers WHERE id = :customerId";
        Customer customer = con.createQuery(customerQuery)
          .addParameter("customerId", customerId)
          .executeAndFetchFirst(Customer.class);
        customers.add(customer);
      }
      return customers;
    }
  }

  public void update(String name, Float price) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE meals SET name = :name, price = :price WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("price", price)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM meals WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
