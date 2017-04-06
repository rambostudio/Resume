package com.harit.chathep.resume.util;

import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rambo on 6/4/2560.
 */

public class JsonUtil {

    public static Object jsonToModel(Object object, String filepath) {
        Gson gson = new Gson();
        return gson.fromJson(loadJSONFromAsset(filepath), (Class<Object>) object);
    }

    private static String loadJSONFromAsset(String filename) {
        String json = null;
        try {

            InputStream is = Contextor.getInstance().getContext().getAssets().open(filename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
