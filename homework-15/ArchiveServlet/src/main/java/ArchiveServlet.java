import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ArchiveServlet")
public class ArchiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArchiveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dir = request.getParameter("dir");
		String filter = request.getParameter("filter");

        FileOutputStream fileOut = new FileOutputStream(dir + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fileOut);

        File fileToZip = new File(dir);
        zip(fileToZip, fileToZip.getName(), zipOut, filter);
        
        zipOut.close();
        fileOut.close();
        
        String fileName = dir + ".zip";
        File file = new File(fileName);
        
        response.setContentType("application/zip");
        response.setContentLength((int)file.length());
        response.addHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
        
        byte[] buffer = new byte[1024];
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream servletOut = response.getOutputStream();
        
        int count;
        while ((count = fileIn.read(buffer)) > 0) {
        	servletOut.write(buffer, 0, count);     
        }
        
        fileIn.close();
        servletOut.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public void zip(File fileToZip, String fileName, ZipOutputStream zipOut, String filter) throws IOException {
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {	
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
            	if(!childFile.getAbsolutePath().contains(filter)) {
            		continue;
            	}
            	
                zip(childFile, fileName + "/" + childFile.getName(), zipOut, filter);
            }
            
            return;
        }
        
        FileInputStream fileIn = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileIn.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        
        fileIn.close();
    }
}
