package graph.to.db.controller.command.impl;

import graph.to.db.controller.command.Command;
import graph.to.db.controller.command.MappingJSP;
import graph.to.db.entity.PlotData;
import graph.to.db.exception.ServiceException;
import graph.to.db.service.FileService;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetPlotByName implements Command {
    public final String NAME_PARAM = "name";
    public final String LOCO_ATT = "loc";
    public final String NAME_ATT = "name";
    private static final String ERROR_PARAM = "error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FileService fileService = FileService.getInstance();
        String responseFile = MappingJSP.PLOT_PROFILE;
        String name =  req.getParameter(NAME_PARAM);
        try {
            if(fileService.plotExist(name)){
                PlotData data = fileService.getPlot(name);
                req.setAttribute(LOCO_ATT, data.getLocation());
                req.setAttribute(NAME_ATT, data.getName());
            }
        } catch (ServiceException e) {
            responseFile = MappingJSP.ERROR_PAGE;
            req.setAttribute(ERROR_PARAM,e.getMessage());

        }
        req.getRequestDispatcher(responseFile).forward(req, resp);
    }
}
