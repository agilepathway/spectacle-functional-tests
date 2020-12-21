package com.thoughtworks.gauge.test.common;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketUtils {

    public static int findFreePortForApi() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            return socket.getLocalPort();
        } catch (IOException e) {

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        return -1;
    }

}