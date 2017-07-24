import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

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
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
