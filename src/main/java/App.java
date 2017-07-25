import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("tables", Table.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tables", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String tableName = request.queryParams("name");
      int guestCount = Integer.parseInt(request.queryParams("guestCount"));
      Table newTable = new Table(tableName, guestCount);
      newTable.save();
      model.put("name", tableName);
      model.put("guestCount", guestCount);
      model.put("tables", Table.all());
      for (int i = 1; i <= guestCount; i++) {
        String custName = String.format("customer%d", i);
        Customer customer = new Customer(custName, newTable.getId());
        customer.save();
      }
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/table/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Table table = Table.find(Integer.parseInt(request.params("id")));
      model.put("table", table);
      model.put("template", "templates/table.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/table/:table_id/customer/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Integer tableId = Integer.parseInt(request.params("table_id"));
      Customer customer = Customer.find(Integer.parseInt(request.params("id")));
      List<Meal> menu = Meal.all();
      model.put("meals", menu);
      model.put("customer", customer);
      model.put("template", "templates/customer.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/menu/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Float price = Float.parseFloat(request.queryParams("price"));
      Meal meal = new Meal(name, price);
      meal.save();
      response.redirect("/menu");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/menu", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      //try/catch
      List<Meal> menu = Meal.all();
      model.put("menu", menu);
      model.put("template", "templates/menu.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/menu/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Meal meal = Meal.find(Integer.parseInt(request.params("id")));
      model.put("meal", meal);
      model.put("template", "templates/meal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/menu/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Meal meal = Meal.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      Float price = Float.parseFloat(request.queryParams("price"));
      meal.update(name, price);
      meal.save();
      model.put("template", "templates/menu.vtl");
      response.redirect("/menu");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/menu/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Meal meal = Meal.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      Float price = Float.parseFloat(request.queryParams("price"));
      meal.update(name, price);
      response.redirect("/menu");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/menu/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Meal meal = Meal.find(Integer.parseInt(request.params("id")));
      meal.delete();
      response.redirect("/menu");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/table/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Table table = Table.find(Integer.parseInt(request.params("id")));
    //   table.delete();
    //   response.redirect("/");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


  }
}
