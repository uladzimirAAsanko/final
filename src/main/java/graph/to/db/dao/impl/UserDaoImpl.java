package graph.to.db.dao.impl;

import graph.to.db.dao.UserDao;
import graph.to.db.dao.pool.ConnectionPool;
import graph.to.db.entity.PlotData;
import graph.to.db.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl userDao;
    private static final String SELECT_DATA_BY_NAME = "SELECT plot_id, plot_location, plot_name, " +
            "param FROM plot WHERE plot_name=?";
    private static final String INPUT_DATA = "INSERT INTO plot(plot_location,plot_name,param) VALUES (?,?,?)";

    public static UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }

        return userDao;
    }

    @Override
    public boolean nameExists(String name) throws DaoException {
        ConnectionPool instance = ConnectionPool.getINSTANCE();
        ResultSet resultSet = null;
        boolean isExists = false;
        try(Connection connection = instance.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_DATA_BY_NAME)){
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            isExists = resultSet.next();
        }catch (SQLException exception){
            throw new DaoException("Exception while searching name",exception);
        }
        return isExists;
    }

    @Override
    public PlotData getPlotData(String name) throws DaoException{
        ConnectionPool instance = ConnectionPool.getINSTANCE();
        PlotData data = null;
        ResultSet resultSet = null;
        try(Connection connection = instance.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_DATA_BY_NAME);){
            statement.setString(1,name);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String plotLocation = resultSet.getString(2) + "\\1.png";
                String plotName = resultSet.getString(3);
                double param = resultSet.getDouble(4);

                data = new PlotData(param,0.0,0.0,0.0,plotLocation, plotName);
            }
        }catch (SQLException e){
            throw new DaoException("Error while adding data",e);
        }
        return data;
    }

    @Override
    public boolean writePlotData(PlotData data, String directoryName) throws DaoException {
        ConnectionPool instance = ConnectionPool.getINSTANCE();
        boolean isDataAdded = false;
        try(Connection connection = instance.getConnection();
            PreparedStatement statement = connection.prepareStatement(INPUT_DATA);){
            statement.setString(1,directoryName);
            statement.setString(2,data.getName());
            statement.setDouble(3,data.getX());
            isDataAdded = statement.executeUpdate() > 0;;
        }catch (SQLException e){
            throw new DaoException("Error while adding data",e);
        }
        return isDataAdded;
    }
}
