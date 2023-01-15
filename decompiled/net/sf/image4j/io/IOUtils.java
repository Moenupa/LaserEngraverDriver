/*
 * Decompiled with CFR 0.150.
 */
package net.sf.image4j.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    public static int skip(InputStream in, int count, boolean strict) throws IOException {
        int b;
        int skipped;
        for (skipped = 0; skipped < count && (b = in.read()) != -1; ++skipped) {
        }
        if (skipped < count && strict) {
            throw new EOFException("Failed to skip " + count + " bytes in input");
        }
        return skipped;
    }
}

