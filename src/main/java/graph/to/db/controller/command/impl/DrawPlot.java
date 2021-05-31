package graph.to.db.controller.command.impl;

import graph.to.db.controller.command.Command;
import graph.to.db.controller.command.MappingJSP;
import graph.to.db.exception.ServiceException;
import graph.to.db.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DrawPlot  implements Command {
    private final String NAME_PARAM = "name";
    private final String X_PARAM = "x";
    private final String Y_PARAM = "y";
    private final String Z_PARAM = "z";
    private final String H_PARAM = "h";
    private final String ERROR_PARAM = "error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        double x = Double.valueOf( req.getParameter(X_PARAM));
        double y = Double.valueOf( req.getParameter(Y_PARAM));
        double z = Double.valueOf( req.getParameter(Z_PARAM));
        double h = Double.valueOf( req.getParameter(H_PARAM));
        String name =  req.getParameter(NAME_PARAM);
        String responseFile = MappingJSP.ERROR_PAGE;
        try {
            if (FileService.getInstance().drawAnPlot(name, x,y,z,h)){
                System.out.println("ANSWER IS GOOD");
                req.setAttribute(NAME_PARAM, name);
                responseFile = MappingJSP.SUCCESS;
            }
        } catch (ServiceException e) {
            System.out.println("ANSWER IS BAD");

        }

        req.getRequestDispatcher(responseFile).forward(req, resp);
    }
}
