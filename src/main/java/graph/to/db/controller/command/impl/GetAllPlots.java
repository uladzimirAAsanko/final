package graph.to.db.controller.command.impl;

import graph.to.db.controller.command.Command;
import graph.to.db.controller.command.MappingJSP;
import graph.to.db.entity.PlotData;
import graph.to.db.exception.ServiceException;
import graph.to.db.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllPlots implements Command {
    private static final String ERROR_PARAM = "error";
    private static final String LIST_PARAM = "list";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FileService fileService = FileService.getInstance();
        String responseFile = MappingJSP.ALL_PLOT;
        try {
            List<String> list = fileService.getAllNames();
            req.setAttribute(LIST_PARAM,list);
        } catch (ServiceException e) {
            responseFile = MappingJSP.ERROR_PAGE;
            req.setAttribute(ERROR_PARAM,e.getMessage());
        }
        req.getRequestDispatcher(responseFile).forward(req, resp);
    }
}
