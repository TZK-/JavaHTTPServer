package granadat.httpserver.server;

import granadat.httpserver.html.FormDetails;
import granadat.httpserver.html.XMLToHTMLParser;
import granadat.httpserver.students.Student;
import granadat.httpserver.students.StudentsManager;
import granadat.httpserver.students.XMLStudentExporter;
import granadat.httpserver.students.XMLStudentLoader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPServer {

    private Mode mode;
    private ServerSocket serverSocket;
    private BufferedReader inputRequest;
    private BufferedWriter outputRequest;
    private Map<String, String> headers;
    private URL currentUrl;
    private StudentsManager studentsManager;
    private XMLToHTMLParser parser;

    public HTTPServer(int port, Mode mode) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server running on port " + port);

        studentsManager = new StudentsManager(new XMLStudentLoader(), "students.xml");
        parser = new XMLToHTMLParser(studentsManager);

        inputRequest = null;
        outputRequest = null;
        currentUrl = null;

        this.mode = mode;
    }

    public static void main(String[] args) throws Exception {
        new HTTPServer(8080, Mode.VERBOSE).run();
    }

    private void run() throws IOException {
        while (true) {
            Socket clientSocket = openConnection();

            initRequestHeaders();

            writeHeaders();
            
            outputRequest.write("Hello world");

            closeConnection(clientSocket);
        }
    }

    private Socket openConnection() throws IOException {
        Socket clientSocket = serverSocket.accept();

        inputRequest = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outputRequest = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        return clientSocket;
    }

    private void initRequestHeaders() throws IOException {
        Map<String, String> headers = new HashMap<>();

        String line;
        int nbHeaders = 0;
        while ((line = inputRequest.readLine()) != null) {
            if (mode == Mode.VERBOSE) {
                System.out.println(line);
            }
            if (line.isEmpty()) {
                break;
            }

            if (nbHeaders == 0) {
                headers.put("Url", line.split(" ")[1]);
            } else {
                String[] headerParts = line.split(":", 2);
                String headerName = headerParts[0];
                String headerValue = headerParts[1].replaceFirst("^\\s*", "");
                headers.put(headerName, headerValue);
            }

            nbHeaders++;
        }

        this.headers = headers;
    }

    private void writeHeaders() throws IOException {
        outputRequest.write("HTTP/1.0 200 OK\r\n");
        outputRequest.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
        outputRequest.write("Server: Apache/0.8.4\r\n");
        outputRequest.write("Content-Type: text/html\r\n");
        outputRequest.write("Content-Length: 59\r\n");
        outputRequest.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
        outputRequest.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
        outputRequest.write("\r\n");
    }

    private void closeConnection(Socket clientSocket) throws IOException {
        outputRequest.close();
        inputRequest.close();

        clientSocket.close();
    }

}
