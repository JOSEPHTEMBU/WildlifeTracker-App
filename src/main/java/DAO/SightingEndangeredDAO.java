package DAO;

import models.SightingEndangered;

import java.util.List;

public interface SightingEndangeredDAO {

    List<SightingEndangered> getAllEndangered();

    void addEndangered(SightingEndangered sightingEndangered);
}
