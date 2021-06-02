package graph.to.db.dao;

import graph.to.db.entity.PlotData;
import graph.to.db.exception.DaoException;

import java.util.List;

public interface UserDao {
    boolean nameExists(String name) throws DaoException;

    PlotData getPlotData(String name) throws DaoException;

    boolean writePlotData(PlotData data,String directoryName) throws DaoException;

    List<String> getAllPlotsName() throws DaoException;
}
