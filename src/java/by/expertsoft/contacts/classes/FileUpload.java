package by.expertsoft.contacts.classes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.servlet.ServletInputStream;

public class FileUpload {

    String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public FileUpload() {
    }

    public void fileUpload(String savePath,ServletInputStream in) throws IOException
    {
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
                        this.fileName = filepath.substring(pos + 1);
                    } else {
                        this.fileName = filepath;
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
                    RandomAccessFile f = new RandomAccessFile(savePath + File.separator + this.fileName, "rw");
                    byte[] bytes = buffer.toByteArray();
                    f.write(bytes, 0, bytes.length - 2);
                    f.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            i = in.readLine(line, 0, 128);
        }
    }
}
