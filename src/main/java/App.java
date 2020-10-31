import DAO.Sql2oLocationDAO;
import DAO.Sql2oRangerDAO;
import DAO.Sql2oSightingDAO;
import DAO.Sql2oSightingEndangeredDAO;
import models.Location;
import models.Ranger;

import static spark.Spark.*;

import models.Sighting;
import models.SightingEndangered;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","Access");
        Sql2oLocationDAO locationDAO = new Sql2oLocationDAO(sql2o);
        Sql2oRangerDAO rangerDAO = new Sql2oRangerDAO(sql2o);
        Sql2oSightingEndangeredDAO sightingEndangeredDAO = new Sql2oSightingEndangeredDAO(sql2o);
        Sql2oSightingDAO sightingDAO = new Sql2oSightingDAO(sql2o);

        Map<String, Object> model = new HashMap<>();

//        ---h2 db---
//        String connectionString = "jdbc:h2:~/HeroesSquad-App.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");

//        ---Local Database---
//        String connectionString = "jdbc:postgresql://localhost:5432/heroapp"; // local db connection string
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access"); // local db sql2o instance

//        ---heroku Database---
//        String connectionString = "jdbc:postgresql://ec2-23-22-156-110.compute-1.amazonaws.com/dca403p0rj7rd1"; // heroku db connection string
//        Sql2o sql2o = new Sql2o(connectionString, "pawsdbhpnhsqno", "c3d09ad0e163678f9c64ceddef7f58e08083fdb434c10925ae9b9bcef355ff3d"); // heroku db sql2o instance




        get("/",(req, res)->{
            model.put("endangeredSightings", sightingEndangeredDAO.getAllEndangered());
            model.put("normalSightings", sightingDAO.getNormal());
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

        get("/sightnormal",(req, res)->{
            model.put("rangers", rangerDAO.getAllRangers());
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model,"sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightendangered",(req, res)->{
            model.put("endangered",true);
            model.put("rangers", rangerDAO.getAllRangers());
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model,"sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightnormal",(req, res)->{
            String animalName = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("ranger"));
            int locationId = Integer.parseInt(req.queryParams("location"));
            Sighting newSighting = new Sighting(animalName,rangerId,locationId);
            sightingDAO.addNormal(newSighting);
            model.put("endangeredSightings", sightingEndangeredDAO.getAllEndangered());
            model.put("normalSightings", sightingDAO.getNormal());
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightendangered",(req, res)->{
            String animalName = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("ranger"));
            int locationId = Integer.parseInt(req.queryParams("location"));
            String animalHealth = req.queryParams("health");
            int animalAge = Integer.parseInt(req.queryParams("age"));
            SightingEndangered newSightingEndangered = new SightingEndangered(animalName,animalAge,animalHealth,rangerId,locationId);
            sightingEndangeredDAO.addEndangered(newSightingEndangered);
            model.put("endangeredSightings", sightingEndangeredDAO.getAllEndangered());
            model.put("normalSightings", sightingDAO.getNormal());
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers/:id",(req, res)->{
            int id = Integer.parseInt(req.params("id"));
            model.put("ranger", rangerDAO.getRangerById(id));
            model.put("endangeredSightings", rangerDAO.getEndangeredSightingsByRangerId(id));
            model.put("normalSightings", rangerDAO.getSightingsByRangerId(id));
            return new ModelAndView(model,"ranger-details.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
