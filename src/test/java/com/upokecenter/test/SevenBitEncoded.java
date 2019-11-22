package com.upokecenter.test;

import java.io.*;
import com.upokecenter.numbers.*;

  public final class SevenBitEncoded {
private SevenBitEncoded() {
}
    private static void Write7BitEncoded(OutputStream outputStream, EInteger ei) throws java.io.IOException {
      if (outputStream == null) {
        throw new NullPointerException("outputStream");
      }
      if (ei.signum() < 0) {
        throw new IllegalArgumentException("ei");
      }
      if (ei.isZero()) {
        outputStream.write(0);
        return;
      }
      byte[] tmp = new byte[4];
      while (!ei.isZero()) {
        int chunk = ei.ToInt32Unchecked() & 0xfffffff;
        ei = ei.ShiftRight(7);
        if (ei.isZero()) {
          int chunksize = 0;
          tmp[chunksize++] = (byte)chunk;
          if (chunk <= 0x7f) {
            outputStream.write((byte)chunk);
          } else if (chunk > 0x3fff) {
            tmp[0] = (byte)(0x80 | (chunk & 0x7f));
            tmp[1] = (byte)((chunk >> 7) & 0x7f);
            outputStream.write(tmp, 0, 2);
          } else if (chunk > 0x1fffff) {
            tmp[0] = (byte)(0x80 | (chunk & 0x7f));
            tmp[1] = (byte)(0x80 | ((chunk >> 7) & 0x7f));
            tmp[2] = (byte)((chunk >> 14) & 0x7f);
            outputStream.write(tmp, 0, 3);
          } else {
            tmp[0] = (byte)(0x80 | (chunk & 0x7f));
            tmp[1] = (byte)(0x80 | ((chunk >> 7) & 0x7f));
            tmp[2] = (byte)(0x80 | ((chunk >> 14) & 0x7f));
            tmp[3] = (byte)((chunk >> 21) & 0x7f);
            outputStream.write(tmp, 0, 4);
          }
        } else {
          tmp[0] = (byte)(0x80 | (chunk & 0x7f));
          tmp[1] = (byte)(0x80 | ((chunk >> 7) & 0x7f));
          tmp[2] = (byte)(0x80 | ((chunk >> 14) & 0x7f));
          tmp[3] = (byte)(0x80 | ((chunk >> 21) & 0x7f));
          outputStream.write(tmp, 0, 4);
        }
      }
    }

    private static EInteger Read7BitEncoded(InputStream inputStream, EInteger
      maxValue) throws java.io.IOException {
      if (inputStream == null) {
        throw new NullPointerException("inputStream");
      }
      EInteger ei = EInteger.FromInt32(0);
      EInteger shift = EInteger.FromInt32(0);
      boolean endOfValue = false;
      boolean haveMaxValue = maxValue != null && maxValue.signum() >= 0;
      while (!endOfValue) {
        int tmp = 0;
        int b = 0;
        int smallshift = 0;
        for (int i = 0; i < 4; ++i) {
          b = inputStream.read();
          if (b < 0) {
            throw new IOException("End of stream");
          }
          tmp += (b & 0x7f) << smallshift;
          if ((b & 0x80) == 0) {
            endOfValue = true;
            break;
          }
          smallshift += 7;
        }
        ei = ei.Add(EInteger.FromInt32(tmp).ShiftLeft(shift));
        if (haveMaxValue && ei.compareTo(maxValue) > 0) {
          throw new IOException("Value read is too high");
        }
        shift = shift.Add(28);
      }
      return ei;
    }
  }
