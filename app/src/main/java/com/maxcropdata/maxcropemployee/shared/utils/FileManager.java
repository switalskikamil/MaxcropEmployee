package com.maxcropdata.maxcropemployee.shared.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /*
    Zapis do pamięci zewnetrznej
     */
    public static void writeFileToExternal(Context context, String fileName, String dataToWrite, String directory) {

        if (FileManager.isExternalStorageWritable()) {
            File path = new File(context.getExternalFilesDir(null), directory);

            if (!path.mkdirs()) {
                //Log.e("MCM", "Directory not created: " + directory);
            }

            File file = new File(path, fileName);


            try {
                FileOutputStream fOut = new FileOutputStream(file);

                fOut.write(dataToWrite.getBytes());
                fOut.close();
                //Log.d("MCM", "File written externally : " + file.getAbsolutePath().toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     Wczytanie danych z pamięci zewnętrznej
      */
    public static String readFileFromExternal(Context context, String fileName, String directory) {
        try {
            File path = new File(context.getExternalFilesDir(null), directory + fileName);
            FileInputStream fin = new FileInputStream(path);//context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //tworzy plik jesli nei istnieje
            File file = new File(context.getFilesDir(), fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                //nie udalo się utworzyć nowego pliku
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }


    /*
    Wczytanie danych z pamięci wewnętrznej
     */
    public static String readFileFromStorage(Context context, String fileName) {
        try {
            FileInputStream fin = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //tworzy plik jesli nei istnieje
            File file = new File(context.getFilesDir(), fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                //nie udalo się utworzyć nowego pliku
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    /*
    Zapis do pamięci wewnętrznej
     */
    public static void writeFileToStorage(Context context, String fileName, String dataToWrite) {
        try {
            FileOutputStream fOut = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            try {

                fOut.write(dataToWrite.getBytes());
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    Kopiowanei danych danego pliku do pamieci zewnętrznej
     */
    public static void extractFileToExternal(Context context, String fileName, String directory) {
        String dataReadFromStorage = readFileFromStorage(context, fileName);
        FileManager.writeFileToExternal(context, fileName, dataReadFromStorage, directory);
    }

    public static boolean deleteDirectory(File path) {
        Log.d("MCM", "Deleting dir: " + path.getAbsolutePath());
        if( path.exists() ) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }

    public static boolean ifDirectoryExists(String path, Context c) {
        File folder = new File(c.getExternalFilesDir(null) + File.separator + path);
        return folder.exists();
    }

    public static File[] listDirectoryContent(String dir, Context c) {
        File path = new File(c.getExternalFilesDir(null) + File.separator + dir);
        if( path.exists() ) {
            return  path.listFiles();
        }
        return null;
    }
}
