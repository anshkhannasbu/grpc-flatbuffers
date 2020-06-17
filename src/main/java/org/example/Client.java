package org.example;

import io.grpc.*;

import java.io.File;
import java.io.FileInputStream;
import com.google.flatbuffers.FlatBufferBuilder;
import io.grpc.stub.StreamObserver;


public class Client {
    public static void main(String[] args) throws Exception{
        String target = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        org.example.FileTransferGrpc.FileTransferStub asyncStub = org.example.FileTransferGrpc.newStub(channel);
        final long[] recv = new long[1];
        recv[0] = 0;
        StreamObserver<org.example.TransferMsg> requestObserver = asyncStub.sendData(new StreamObserver<org.example.TransferReply>(){
            @Override
            public void onNext(org.example.TransferReply msg) {
                System.out.println(msg.partId());
                recv[0]++;
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.println(status);
                System.out.println("Finished streaming with errors");
            }

            @Override
            public void onCompleted() {
                System.out.println("Finished streaming");
            }
        });
        long partId = 0;
        try{
            File file = new File("../bigtext.txt");
            FileInputStream is = new FileInputStream(file);
            byte[] chunk = new byte[64*1024];
            int chunkLen = 0;
            while ((chunkLen = is.read(chunk)) != -1) {
               partId++;
                FlatBufferBuilder builder = new FlatBufferBuilder();
                int dataOff = org.example.TransferMsg.createDataVector(builder, chunk);
                int off = org.example.TransferMsg.createTransferMsg(builder, partId, dataOff);
                builder.finish(off);
                org.example.TransferMsg msg = org.example.TransferMsg.getRootAsTransferMsg(builder.dataBuffer());
                requestObserver.onNext(msg);
            }

        } catch (Exception e){
            System.out.println(e);
        }
        requestObserver.onCompleted();
        Thread.sleep(1000*100);
        if(recv[0] == partId){
            System.out.println("Transfer Successfull....");
        } else{
            System.out.println("Some error occurred...");
        }
    }
}