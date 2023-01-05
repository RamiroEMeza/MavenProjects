package threads.connections;

import java.util.concurrent.CompletableFuture;

public class ClientsRunner {

    public static void main(String[] args) {
        ConnectionPool conectionP = new ConnectionPool();
        CompletableFuture<Connection> request = new CompletableFuture<>();
    }
}
