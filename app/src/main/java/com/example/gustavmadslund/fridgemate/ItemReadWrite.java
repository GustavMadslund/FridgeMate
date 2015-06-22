package com.example.gustavmadslund.fridgemate;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by gugun_000 on 22/06/2015.
 */
public class ItemReadWrite {

    public static ArrayList<FoodItem> read(Context context, String filepath) throws IOException, ClassNotFoundException {

        FileInputStream inputStream = new FileInputStream(filepath);

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return (ArrayList<FoodItem>) objectInputStream.readObject();

        } finally {
            objectInputStream.close();
        }
    }

    public static void write(String filepath, ArrayList<FoodItem> data){
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
