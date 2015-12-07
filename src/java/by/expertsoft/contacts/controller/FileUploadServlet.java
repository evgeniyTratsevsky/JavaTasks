package by.expertsoft.contacts.controller;

import by.expertsoft.contacts.classes.Constants;
import by.expertsoft.contacts.classes.FileUpload;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class FileUploadServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        String destinationDirPath = (String) getServletContext().getInitParameter("destinationDirPath");
        File destinationDir = new File(destinationDirPath);

        if (!destinationDir.isDirectory()) {
            destinationDir.mkdirs();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream in = request.getInputStream();
        String savePath = (String) getServletContext().getInitParameter("destinationDirPath");
        FileUpload fileUpload=new FileUpload();
        fileUpload.fileUpload(savePath,in);
        String filename=fileUpload.getFileName();
               
        
       /* String savePath = (String) getServletContext().getInitParameter("destinationDirPath");
        String filename = "";

        ServletInputStream in = request.getInputStream();

        byte[] line = new byte[128];
        int i = in.readLine(line, 0, 128);
        int boundaryLength = i - 2;
        String boundary = new String(line, 0, boundaryLength);

        while (i != -1) {

            String newLine = new String(line, 0, i);

            if (newLine.startsWith("Content-Disposition: form-data; name=\"")) {

                String s = new String(line, 0, i - 2);
                int pos = s.indexOf("filename=\"");

                if (pos != -1) {
                    String filepath = s.substring(pos + 10, s.length() - 1);
                    pos = filepath.lastIndexOf("\\");
                    if (pos != -1) {
                        filename = filepath.substring(pos + 1);
                    } else {
                        filename = filepath;
                    }
                }

                i = in.readLine(line, 0, 128);
                i = in.readLine(line, 0, 128);
                i = in.readLine(line, 0, 128);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                newLine = new String(line, 0, i);

                while (i != -1 && !newLine.startsWith(boundary)) {
                    buffer.write(line, 0, i);
                    i = in.readLine(line, 0, 128);
                    newLine = new String(line, 0, i);
                }

                try {
                    RandomAccessFile f = new RandomAccessFile(savePath + File.separator + filename, "rw");
                    byte[] bytes = buffer.toByteArray();
                    f.write(bytes, 0, bytes.length - 2);
                    f.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            i = in.readLine(line, 0, 128);
        }
*/
        if (!filename.isEmpty()) {
            HttpSession session = request.getSession();
            String fileLocation = savePath + filename;
            request.setAttribute(Constants.FILE_LOCATION, fileLocation);
            forward(Constants.CONTACTS_CONTROLLER, request, response);
        } else {
            request.setAttribute(Constants.FILE_ERROR, Constants.FILE_MESSAGE_ERROR);
            forward(Constants.IMPORT_PAGE, request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
