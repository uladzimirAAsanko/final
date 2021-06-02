package graph.to.db.controller;

import graph.to.db.entity.PlotData;
import graph.to.db.exception.ServiceException;
import graph.to.db.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileServlet extends HttpServlet {


    private static final int DEFAULT_BUFFER_SIZE = 30240;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        FileService fileService = FileService.getInstance();
        String fileId = request.getParameter("name");
        String param = request.getParameter("end");
        PlotData data = null;
        try {
             data = fileService.getPlot(fileId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        String location = data.getLocation().substring(0,data.getLocation().length() - 5) + param;
        File file = new File(location);

        if (file == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        input = new BufferedInputStream( new FileInputStream(file), DEFAULT_BUFFER_SIZE);
        output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.close();
    }


}