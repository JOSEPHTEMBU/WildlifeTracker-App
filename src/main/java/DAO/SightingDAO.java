package DAO;

import models.Sighting;

import java.util.List;

public interface SightingDAO {

    List<Sighting> getNormal();
    void addNormal(Sighting sighting);
}
