package DAO;

import models.Ranger;
import models.Sighting;
import models.SightingEndangered;

import java.util.List;

public interface RangerDAO {

    List<Ranger> getAllRangers();

    void addRanger(Ranger ranger);

    Ranger getRangerById(int id);

    List<Sighting> getSightingsByRangerId(int id);

    List<SightingEndangered> getEndangeredSightingsByRangerId(int id);

}
