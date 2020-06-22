package org.example;

import com.google.protobuf.ByteString;
import io.grpc.*;

import java.io.File;
import java.io.FileInputStream;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.protobuf.LiteralByteString;
import io.grpc.stub.StreamObserver;
import org.example.ProtoFileTransfer.TransferMsgProto;
import org.example.ProtoFileTransfer.TransferReplyProto;
import org.example.TransferMsg;
import org.example.TransferReply;



public class Client {

    private FileTransferGrpc.FileTransferStub asyncStubFlat;
    private ProtobufServiceGrpc.ProtobufServiceStub asyncStubProto;
    final long[] recv = new long[1];
    int partId = 0;

    public Client(Channel channel){
        asyncStubFlat = FileTransferGrpc.newStub(channel);
        asyncStubProto = ProtobufServiceGrpc.newStub(channel);
        recv[0] = 0;
    }

    public void execFlatClient() throws Exception{
        System.out.println("Starting streaming with Flatbuffers");
        StreamObserver<TransferMsg> requestObserver = asyncStubFlat.sendData(new StreamObserver<TransferReply>(){
            @Override
            public void onNext(TransferReply msg) {
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
        try{
            int i = 0;
            byte[] chunk = new byte[64 * 1024];
            int chunkLen = 0;
            while(i < 1000) {
                File file = new File("../bigtext.txt");
                FileInputStream is = new FileInputStream(file);
                while ((chunkLen = is.read(chunk)) != -1) {
                    partId++;
                    FlatBufferBuilder builder = new FlatBufferBuilder();
                    int dataOff = TransferMsg.createDataVector(builder, chunk);
                    int off = TransferMsg.createTransferMsg(builder, partId, dataOff);
                    builder.finish(off);
                    TransferMsg msg = TransferMsg.getRootAsTransferMsg(builder.dataBuffer());
                    requestObserver.onNext(msg);
                }
                i++;
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

    public void execProto() throws Exception{
        System.out.println("Starting streaming with Protobuffers");
        StreamObserver<TransferMsgProto> requestObserver = asyncStubProto.sendData(new StreamObserver<TransferReplyProto>(){

            @Override
            public void onNext(TransferReplyProto msg) {
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
        try{
            int i = 0;
            byte[] chunk = new byte[64 * 1024];
            int chunkLen = 0;
            while(i < 1000) {
                File file = new File("../bigtext.txt");
                FileInputStream is = new FileInputStream(file);
                while ((chunkLen = is.read(chunk)) != -1) {
                    partId++;
                    TransferMsgProto msg = TransferMsgProto.newBuilder().setPartId(partId).setData(ByteString.copyFrom(chunk)).build();
                    requestObserver.onNext(msg);
                }
                i++;
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

    public static void main(String[] args) throws Exception{
        String target = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        Client c = new Client(channel);
        if(args.length == 0){
            c.execFlatClient();
        } else{
            c.execProto();
        }
    }
}
// Refractor Required....