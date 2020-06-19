package org.example;

import io.grpc.*;
import org.example.FileTransferImpl;

import java.util.concurrent.TimeUnit;

public class Server
{
    public static void main( String[] args ) throws Exception
    {
        BindableService service;
        if(args.length == 0){
            service = new FileTransferImpl();
        } else{
            service = new ProtobufServiceImpl();
        }
        io.grpc.Server server = ServerBuilder.forPort(50051)
                .addService(service)
                .build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination(10, TimeUnit.MINUTES);
    }
}