package com.hope.engine;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;

public class Sound {

    public static Clips prontera = load("/ragProntera.wav",1);

    private static Clips load(String name, int index) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataInputStream dataInputStream = new DataInputStream(Sound.class.getResourceAsStream(name));

            byte[] data = new byte[1024];
            int read = 0;

            while ((read = dataInputStream.read(data)) >= 0)
                byteArrayOutputStream.write(data, 0, read);

            dataInputStream.close();

            byte[] newData = byteArrayOutputStream.toByteArray();

            return new Clips(newData, index);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
