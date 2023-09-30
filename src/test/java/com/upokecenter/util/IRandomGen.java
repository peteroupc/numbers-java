package com.upokecenter.util;

  /**
   * <p>Interface for random-number generators.</p>
   *
   */
  public interface IRandomGen {
    /**
     * <p>Randomly generates a set of bytes.</p>
     * <p>@param bytes </p>
     * <p>Byte buffer to store the random bytes.</p>
     * <p>@param offset </p>
     * <p>A zero-based index showing where the desired portion of
     * {@code bytes} begins.</p>
     * <p>@param length </p>
     * <p>The length, in bytes, of the desired portion of {@code
     * bytes} (but not more than {@code bytes} 's length).</p>
     * @return <p>Number of bytes returned.</p>
     *
     */
    int GetBytes(byte[] bytes, int offset, int length);
  }
