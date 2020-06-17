package org.example;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import com.google.flatbuffers.FlatBufferBuilder;


public class FileTransferImpl extends org.example.FileTransferGrpc.FileTransferImplBase {
    @Override
    public StreamObserver<org.example.TransferMsg> sendData(final StreamObserver<org.example.TransferReply> responseObserver){
        return new StreamObserver<org.example.TransferMsg>() {
            long x = 0;
            @Override
            public void onNext(org.example.TransferMsg msg){
                x += msg.dataLength();
                FlatBufferBuilder builder = new FlatBufferBuilder();
                int off = org.example.TransferReply.createTransferReply(builder, msg.partId(), builder.createString("OK"));
                builder.finish(off);
                org.example.TransferReply rep = org.example.TransferReply.getRootAsTransferReply(builder.dataBuffer());
                responseObserver.onNext(rep);
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.println(status);
                System.out.println("Finished streaming with errors");
            }

            @Override
            public void onCompleted(){
                System.out.println(x);
                responseObserver.onCompleted();
            }
        };
    }
}