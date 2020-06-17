package org.example;

import io.grpc.*;
import org.example.FileTransferImpl;

public class Server
{
    public static void main( String[] args ) throws Exception
    {
        io.grpc.Server server = ServerBuilder.forPort(50051)
                .addService(new FileTransferImpl())
                .build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}