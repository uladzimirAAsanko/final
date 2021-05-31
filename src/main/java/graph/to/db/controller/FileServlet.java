package graph.to.db.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileServlet extends HttpServlet {


    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        String fileId = (String) request.getAttribute("path");
        File file = new File(fileId);

        // Check if file is actually retrieved from database.
        if (file == null) {
            // Do your thing if the file does not exist in database.
            // Throw an exception, or send 404, or show default/warning page, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        // Open streams.
        input = new BufferedInputStream( new FileInputStream(file), DEFAULT_BUFFER_SIZE);
        output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

        // Write file contents to response.
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
    }


}