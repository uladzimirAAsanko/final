package graph.to.db.controller.command.impl;

import graph.to.db.controller.command.Command;
import graph.to.db.controller.command.MappingJSP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WrongAction implements Command {
    public static final String MESSAGE = "message";
    private static final String COMMAND_DO_NOT_UNDERSTAND = "Command do not understand";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(MESSAGE, COMMAND_DO_NOT_UNDERSTAND);
        try {
            req.getRequestDispatcher(MappingJSP.ERROR_PAGE).forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println("can't upload error page");
        }
    }
}