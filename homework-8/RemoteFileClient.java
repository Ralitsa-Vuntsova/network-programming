import java.io.*;
import java.net.*;

public class RemoteFileClient {
    protected BufferedReader socketReader;
    protected PrintWriter socketWriter;
    protected String hostIp;
    protected int hostPort;

    public RemoteFileClient(String aHostIp, int aHostPort) {
        hostIp = aHostIp;
        hostPort = aHostPort;
    }

    public String getFile(String fileNameToGet) {
        StringBuffer fileLines = new StringBuffer();

        try {
            socketWriter.println(fileNameToGet);
            socketWriter.flush();

            String line = null;
            while ((line = socketReader.readLine()) != null)
                fileLines.append(line + "\n");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + fileNameToGet);
        }

        return fileLines.toString();
    }

    public static void main(String[] args) {
        RemoteFileClient remoteFileClient = new RemoteFileClient("127.0.0.1", 3000);
        remoteFileClient.setUpConnection();

        String fileContents = remoteFileClient.getFile("E://downloads/Harmless-entertainment/UNI/Магистър/МП/домашни/домашно-8/Sockets/src/RemoteFile.txt");
        remoteFileClient.tearDownConnection();

        System.out.println(fileContents);
    }

    public void setUpConnection() {
        try {
            Socket client = new Socket(hostIp, hostPort);

            socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            socketWriter = new PrintWriter(client.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println("Error setting up socket connection: unknown host at " + e);
        }
        catch (IOException e) {
            System.out.println("Error setting up socket connection: " + e);
        }
    }

    public void tearDownConnection() {
        try {
            socketWriter.close();
            socketReader.close();
        } catch (IOException e) {
            System.out.println("Error tearing down socket connection: " + e);
        }
    }
}