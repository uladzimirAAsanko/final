package graph.to.db.controller;

import graph.to.db.controller.command.Command;
import graph.to.db.controller.command.CommandProvider;
import graph.to.db.dao.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    public void destroy() {
        ConnectionPool.getINSTANCE().destroyPool();
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String commandName = req.getParameter(COMMAND);
        CommandProvider provider = CommandProvider.getInstance();
        Command command = provider.defineCommand(commandName);
        command.execute(req,resp);
    }

}
