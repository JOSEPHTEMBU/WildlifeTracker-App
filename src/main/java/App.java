import DAO.Sql2oLocationDAO;
import DAO.Sql2oRangerDAO;
import models.Animal;
import models.EndangeredAnimal;
import models.Location;
import models.Ranger;
import org.sql2o.Connection;
import static spark.Spark.*;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;


public class App {

    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","Access");
        Sql2oLocationDAO locationDAO = new Sql2oLocationDAO(sql2o);
        Sql2oRangerDAO rangerDAO = new Sql2oRangerDAO(sql2o);
        Map<String, Object> model = new HashMap<>();



        get("/",(req, res)->{
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers",(req, res)->{
            model.put("rangers",rangerDAO.getAllRangers());
            return new ModelAndView(model,"rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations",(req, res)->{
            model.put("locations",locationDAO.getAllLocations());
            return new ModelAndView(model,"locations.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addranger",(req, res)->{
            return new ModelAndView(model,"ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addlocation",(req, res)->{
            return new ModelAndView(model,"location-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addranger",(req, res)->{
            String name = req.queryParams("name");
            int badge = Integer.parseInt(req.queryParams("badge"));
            String contact = req.queryParams("contact");
            Ranger newRanger = new Ranger(name,badge,contact);
            rangerDAO.addRanger(newRanger);
            model.put("rangers", rangerDAO.getAllRangers());
            return new ModelAndView(model,"rangers.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addlocation",(req, res)->{
            String name = req.queryParams("name");
            Location newLocation = new Location(name);
            locationDAO.addLocation(newLocation);
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model,"locations.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
