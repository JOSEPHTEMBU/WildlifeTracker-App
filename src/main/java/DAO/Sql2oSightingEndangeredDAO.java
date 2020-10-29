package DAO;

import models.SightingEndangered;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSightingEndangeredDAO implements SightingEndangeredDAO{
    private final Sql2o sql2o;

    public Sql2oSightingEndangeredDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<SightingEndangered> getAllEndangered() {
        String sql = "SELECT * FROM sightings WHERE type ='Endangered'";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(SightingEndangered.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addEndangered(SightingEndangered sightingEndangered) {
        String sql = "INSERT INTO sightings (animalName, animalAge, animalHealth, rangerId, type, locationId) values (:animalName, :animalAge, :animalHealth, :rangerId, :type, :locationId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(sightingEndangered)
                    .executeUpdate()
                    .getKey();
            sightingEndangered.setId(id);        }
        catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}
