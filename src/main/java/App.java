import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.Map;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/customer/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Customer customer = new Customer.find(request.params("id"));
    model.put("customer", customer);
    model.put("template", "templates/customer.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/menu", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    Float price = Float.parseFloat(request.queryParams("price"));
    Meal meal = new Meal(name, price);
    response.redirect("/menu");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  get("/menu", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    //try/catch
    List<Meal> menu = Meal.all();
    model.put("menu", menu);
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
