package edu.coursera.distributed;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.file.Files;

/**
 * A basic and very limited implementation of a file server that responds to GET
 * requests from HTTP clients.
 */
public final class FileServer {
    /**
     * Main entrypoint for the basic file server.
     *
     * @param socket Provided socket to accept connections on.
     * @param fs A proxy filesystem to serve files from. See the PCDPFilesystem
     *           class for more detailed documentation of its usage.
     * @throws IOException If an I/O error is detected on the server. This
     *                     should be a fatal error, your file server
     *                     implementation is not expected to ever throw
     *                     IOExceptions during normal operation.
     */
    public void run(final ServerSocket socket, final PCDPFilesystem fs)
            throws IOException {
        /*
         * Enter a spin loop for handling client requests to the provided
         * ServerSocket object.
         */
        while (true) {
            Socket clientSocket = socket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            if (line != null && line.startsWith("GET")){
                String path = line.split(" ")[1];
                String filePath = fs.readFile(new PCDPPath(path));
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                if (filePath != null){
                    printWriter.write("HTTP/1.0 200 OK\r\n");
                    printWriter.write("Server: FileServer\r\n");
                    printWriter.write("\r\n");
                    printWriter.write(fs.readFile(new PCDPPath(path)));
                } else {
                    printWriter.write("HTTP/1.0 404 Not Found\r\n");
                    printWriter.write("Server: FileServer\r\n");
                    printWriter.write("\r\n");
                }
                printWriter.close();
                outputStream.close();
            }
        }
    }
}
