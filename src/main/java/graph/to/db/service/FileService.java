package graph.to.db.service;

import graph.to.db.dao.DaoFactory;
import graph.to.db.dao.UserDao;
import graph.to.db.entity.PlotData;
import graph.to.db.exception.DaoException;
import graph.to.db.exception.ServiceException;
import graph.to.db.utility.Drawer;
import graph.to.db.utility.Validator;

import java.io.File;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class FileService {
    private UserDao userDao = DaoFactory.getInstance().getUserDao();
    private static FileService fileService;

    public static FileService getInstance() {
        if (fileService == null) {
            fileService = new FileService();
        }
        return fileService;
    }
    public Boolean drawAnPlot(String name, Double param, Double y, Double z, Double h ) throws ServiceException {
        String directoryName = Drawer.fileLocations + "\\" + name;
        PlotData data = new PlotData(param,y,z,h, directoryName, name);
        if(Validator.validatePlotData(data) == FALSE){
            throw new ServiceException("Data can not pass validation");
        }
        try {
            if(userDao.nameExists(data.getName()) == TRUE){
                throw new ServiceException("Plot with this name is already exists");
            }
            new File(directoryName).mkdirs();
            if(Drawer.drawPlot(data, directoryName) == FALSE){
                throw new ServiceException("Exception while drawing and savin plot");
            }
            return userDao.writePlotData(data,directoryName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public Boolean plotExist(String name) throws ServiceException {
        try {
            return userDao.nameExists(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public PlotData getPlot(String name) throws ServiceException{
        try {
            return userDao.getPlotData(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
