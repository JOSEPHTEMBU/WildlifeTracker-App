package DAO;

import models.Location;
import models.Ranger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oLocationDAO implements LocationDAO {

    private final Sql2o sql2o;

    public Sql2oLocationDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM locations";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(Location.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addLocation(Location location) {
        String sql = "INSERT INTO locations (name) values (:name)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(location)
                    .executeUpdate()
                    .getKey();
            location.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex);

        }
    }
}
