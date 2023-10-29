import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FunctionClass {
    public static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);

        if(!dir.exists()) {
            dir.mkdirs();
        }

        FileInputStream fileInputStreams;
        byte[] buffer = new byte[1024];

        try {
            fileInputStreams = new FileInputStream(zipFilePath);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStreams);
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while(zipEntry != null){
                String fileName = zipEntry.getName();
                File newFile = new File(destDir + File.separator + fileName);

                new File(newFile.getParent()).mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);

                int length;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }

                fileOutputStream.close();
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }

            zipInputStream.closeEntry();
            zipInputStream.close();
            fileInputStreams.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void merge(String[] filesToBeMerged, String mergedFile) throws IOException {
        PrintWriter pw = new PrintWriter(mergedFile);

        for (String fileName : filesToBeMerged) {
            File f = new File("src", fileName);
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line = br.readLine();
            while (line != null) {
                pw.println(line);
                line = br.readLine();
            }

            pw.flush();
        }
    }

    public static void zip(String[] filesToBeArchived, String destZipPath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(destZipPath);
        ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);

        for (String fileName : filesToBeArchived) {
            File fileToZip = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(fileToZip);

            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fileInputStream.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            fileInputStream.close();
        }

        zipOut.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        String zipFilePath = "src/how_to_kill_a_mockingbird.zip";
        String destDir = "src";
        unzip(zipFilePath, destDir);

        String[] filesToBeMerged = new String[] {
                 "first_file.txt", "second_file.txt", "third_file.txt" };
        String mergedFile = "src/merged_file.txt";
        merge(filesToBeMerged, mergedFile);

        String[] filesToBeArchived = new String[] {
                "src/first_file.txt", "src/second_file.txt", "src/third_file.txt", "src/merged_file.txt" };
        String destZipPath = "src/merged.zip";
        zip(filesToBeArchived, destZipPath);
    }
}
