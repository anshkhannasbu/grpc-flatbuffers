package org.example;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: ProtoFileTransfer.proto")
public final class ProtobufServiceGrpc {

  private ProtobufServiceGrpc() {}

  public static final String SERVICE_NAME = "org.example.ProtobufService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.example.ProtoFileTransfer.TransferMsgProto,
      org.example.ProtoFileTransfer.TransferReplyProto> METHOD_SEND_DATA =
      io.grpc.MethodDescriptor.<org.example.ProtoFileTransfer.TransferMsgProto, org.example.ProtoFileTransfer.TransferReplyProto>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "org.example.ProtobufService", "sendData"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.example.ProtoFileTransfer.TransferMsgProto.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.example.ProtoFileTransfer.TransferReplyProto.getDefaultInstance()))
          .setSchemaDescriptor(new ProtobufServiceMethodDescriptorSupplier("sendData"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProtobufServiceStub newStub(io.grpc.Channel channel) {
    return new ProtobufServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProtobufServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ProtobufServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProtobufServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ProtobufServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ProtobufServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<org.example.ProtoFileTransfer.TransferMsgProto> sendData(
        io.grpc.stub.StreamObserver<org.example.ProtoFileTransfer.TransferReplyProto> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_SEND_DATA, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SEND_DATA,
            asyncBidiStreamingCall(
              new MethodHandlers<
                org.example.ProtoFileTransfer.TransferMsgProto,
                org.example.ProtoFileTransfer.TransferReplyProto>(
                  this, METHODID_SEND_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class ProtobufServiceStub extends io.grpc.stub.AbstractStub<ProtobufServiceStub> {
    private ProtobufServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProtobufServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProtobufServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProtobufServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<org.example.ProtoFileTransfer.TransferMsgProto> sendData(
        io.grpc.stub.StreamObserver<org.example.ProtoFileTransfer.TransferReplyProto> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_SEND_DATA, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ProtobufServiceBlockingStub extends io.grpc.stub.AbstractStub<ProtobufServiceBlockingStub> {
    private ProtobufServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProtobufServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProtobufServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProtobufServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class ProtobufServiceFutureStub extends io.grpc.stub.AbstractStub<ProtobufServiceFutureStub> {
    private ProtobufServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ProtobufServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProtobufServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ProtobufServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SEND_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProtobufServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProtobufServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendData(
              (io.grpc.stub.StreamObserver<org.example.ProtoFileTransfer.TransferReplyProto>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProtobufServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProtobufServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.ProtoFileTransfer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProtobufService");
    }
  }

  private static final class ProtobufServiceFileDescriptorSupplier
      extends ProtobufServiceBaseDescriptorSupplier {
    ProtobufServiceFileDescriptorSupplier() {}
  }

  private static final class ProtobufServiceMethodDescriptorSupplier
      extends ProtobufServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProtobufServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProtobufServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProtobufServiceFileDescriptorSupplier())
              .addMethod(METHOD_SEND_DATA)
              .build();
        }
      }
    }
    return result;
  }
}
