package DAO;

import models.Ranger;

import java.util.List;

public interface RangerDAO {

    List<Ranger> getAllRangers();

    void addRanger(Ranger ranger);

}
