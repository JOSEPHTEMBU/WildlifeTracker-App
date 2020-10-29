package DAO;

import models.Location;

import java.util.List;

public interface LocationDAO {

    List<Location> getAllLocations();

    void addLocation(Location location);
}
