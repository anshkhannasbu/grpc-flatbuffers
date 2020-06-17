// automatically generated by the FlatBuffers compiler, do not modify

package org.example;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class TransferReply extends Table {
  public static TransferReply getRootAsTransferReply(ByteBuffer _bb) { return getRootAsTransferReply(_bb, new TransferReply()); }
  public static TransferReply getRootAsTransferReply(ByteBuffer _bb, TransferReply obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public TransferReply __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public long partId() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }
  public boolean mutatePartId(long partId) { int o = __offset(4); if (o != 0) { bb.putInt(o + bb_pos, (int)partId); return true; } else { return false; } }
  public String message() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer messageAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer messageInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }

  public static int createTransferReply(FlatBufferBuilder builder,
      long partId,
      int messageOffset) {
    builder.startObject(2);
    TransferReply.addMessage(builder, messageOffset);
    TransferReply.addPartId(builder, partId);
    return TransferReply.endTransferReply(builder);
  }

  public static void startTransferReply(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addPartId(FlatBufferBuilder builder, long partId) { builder.addInt(0, (int)partId, (int)0L); }
  public static void addMessage(FlatBufferBuilder builder, int messageOffset) { builder.addOffset(1, messageOffset, 0); }
  public static int endTransferReply(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
