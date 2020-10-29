package DAO;

import models.Ranger;
import models.Sighting;
import models.SightingEndangered;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oRangerDAO implements RangerDAO{

    private final Sql2o sql2o;

    public Sql2oRangerDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Ranger> getAllRangers() {
        String sql = "SELECT * FROM rangers";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(Ranger.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addRanger(Ranger ranger) {
        String sql = "INSERT INTO rangers (name, badgeNo, contact) values (:name, :badgeNo, :contact)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(ranger)
                    .executeUpdate()
                    .getKey();
            ranger.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex);

        }
    }

    @Override
    public Ranger getRangerById(int id) {
        String sql = "SELECT * FROM rangers WHERE id = :id";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsByRangerId(int id) {
        String sql = "SELECT * FROM sightings WHERE type ='Non-Endangered' AND rangerId = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<SightingEndangered> getEndangeredSightingsByRangerId(int id) {
        String sql = "SELECT * FROM sightings WHERE type ='Endangered' AND rangerId = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(SightingEndangered.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }
}
