import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

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
      model.put("customer", customer);
      model.put("template", "templates/customer.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //
    // post("/menu", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   String name = request.queryParams("name");
    //   Float price = Float.parseFloat(request.queryParams("price"));
    //   Meal meal = new Meal(name, price);
    //   response.redirect("/menu");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/menu", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   //try/catch
    //   List<Meal> menu = Meal.all();
    //   model.put("menu", menu);
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

  }
}
