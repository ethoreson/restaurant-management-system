import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Customer {
  private String name;
  private int table_id;
  private int id;

  public Customer(String name, int table_id) {
    this.name = name;
    this.table_id = table_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getTableId() {
    return table_id;
  }
  @Override
  public boolean equals(Object otherCustomer){
    if (!(otherCustomer instanceof Customer)) {
      return false;
    } else {
      Customer newCustomer = (Customer) otherCustomer;
      return this.getName().equals(newCustomer.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO customers (name, table_id) VALUES (:name, :table_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("table_id", this.table_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Customer> all() {
    String sql = "SELECT * FROM customers";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Customer.class);
    }
  }

  public static Customer find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM customers where id=:id";
      Customer customer = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Customer.class);
      return customer;
    }
  }

  public void addMeal(Meal meal) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO customers_meals (customer_id, meal_id) VALUES (:customer_id, :meal_id)";
      con.createQuery(sql)
      .addParameter("customer_id", this.getId())
      .addParameter("meal_id", meal.getId())
      .executeUpdate();
    }
  }

  public List<Meal> getMeals() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT meal_id FROM customers_meals WHERE customer_id = :customer_id";
      List<Integer> mealIds = con.createQuery(joinQuery)
        .addParameter("customer_id", this.getId())
        .executeAndFetch(Integer.class);
      List<Meal> meals = new ArrayList<Meal>();
      for (Integer mealId : mealIds) {
        String mealQuery = "SELECT * FROM meals WHERE id = :mealId";
        Meal meal = con.createQuery(mealQuery)
          .addParameter("mealId", mealId)
          .executeAndFetchFirst(Meal.class);
        meals.add(meal);
      }
      return meals;
    }
  }
}
