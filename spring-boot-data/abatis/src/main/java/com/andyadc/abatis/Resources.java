package com.andyadc.abatis;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class Resources {

    public static Reader getResourceAsReader(String resource) {
        return new InputStreamReader(Objects.requireNonNull(getResourceAsStream(resource)));
    }

    private static InputStream getResourceAsStream(String resource) {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (inputStream != null) {
                return inputStream;
            }
        }
        return null;
    }

    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }
}
