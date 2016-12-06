package com.upokecenter.util;

    /**
     * Interface for random-number generators.
     */
 public interface IRandomGen {
    /**
     * Randomly generates a set of bytes.
     * @return Number of bytes returned.
     */
    int GetBytes(byte[] bytes, int offset, int length);
  }
