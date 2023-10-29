import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TCPClient {
	private HttpURLConnection httpConn;

    public TCPClient() {}

    public void getDir(String dir, String saveDir) throws IOException {
    	String encodedDir = URLEncoder.encode(dir, "UTF-8");
    	String encodedFilter = URLEncoder.encode(".txt", "UTF-8");
    			
     	URL url = new URL("http://localhost:8080/ArchiveServlet/ArchiveServlet?" + "dir=" + encodedDir + "&filter=" + encodedFilter);
     	httpConn = (HttpURLConnection)url.openConnection();
     	 
     	int responseCode = httpConn.getResponseCode();
     	 
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpConn.getInputStream();
            
            unzip(inputStream, saveDir);
 
            inputStream.close();
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        
        httpConn.disconnect();
    }
    
    public void unzip(InputStream inputStream, String destDir) throws IOException {
        File dest = new File(destDir);

        byte[] buffer = new byte[1024];
        ZipInputStream zipIn = new ZipInputStream(inputStream);
        ZipEntry zipEntry = zipIn.getNextEntry();
        
        while (zipEntry != null) {
        	File newFile = newFile(dest, zipEntry);
    	    if (zipEntry.isDirectory()) {
    	        if (!newFile.isDirectory() && !newFile.mkdirs()) {
    	            throw new IOException("Failed to create directory " + newFile);
    	        }
    	    } else {
    	        File parent = newFile.getParentFile();
    	        
    	        if (!parent.isDirectory() && !parent.mkdirs()) {
    	            throw new IOException("Failed to create directory " + parent);
    	        }

    	        FileOutputStream fileOut = new FileOutputStream(newFile);
    	        int length;
    	        while ((length = zipIn.read(buffer)) > 0) {
    	        	fileOut.write(buffer, 0, length);
    	        }
    	        
    	        fileOut.close();
    	    }
    	    
    	    zipEntry = zipIn.getNextEntry();
         }

         zipIn.closeEntry();
         zipIn.close();
    }
    
    public File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static void main(String[] args) throws IOException {
    	TCPClient remoteFileClient = new TCPClient();

        remoteFileClient.getDir("E://homework/dir1", "E://homework/downloaded");
        remoteFileClient.getDir("E://homework/dir2", "E://homework/downloaded");
        remoteFileClient.getDir("E://homework/dir3", "E://homework/downloaded");
    }
}
