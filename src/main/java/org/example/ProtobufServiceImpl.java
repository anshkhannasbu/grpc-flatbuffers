package org.example;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.example.ProtoFileTransfer.TransferReplyProto;
import org.example.ProtoFileTransfer.TransferMsgProto;

public class ProtobufServiceImpl extends ProtobufServiceGrpc.ProtobufServiceImplBase {
    @Override
    public StreamObserver<TransferMsgProto> sendData(final StreamObserver<TransferReplyProto> responseObserver){
        return new StreamObserver<TransferMsgProto>() {
            long x = 0;
            @Override
            public void onNext(TransferMsgProto msg){
                x += msg.getData().size();
                TransferReplyProto rep = TransferReplyProto.newBuilder().setPartId(msg.getPartId()).setMessage("OK").build();
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