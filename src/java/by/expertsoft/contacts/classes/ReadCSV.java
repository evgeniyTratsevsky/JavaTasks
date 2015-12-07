package by.expertsoft.contacts.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCSV {

    public List<User> readCSV(String fileLocation) {
        List<User> contacts = null;
        try {
            contacts = new ArrayList<User>();
            Scanner inputStream = new Scanner(new FileReader(fileLocation));
            while (inputStream.hasNext()) {
                //row = inputStream.next();
                String[] user=inputStream.next().split(",");
                contacts.add(new User(user[0],user[1],user[2],user[3],user[4],user[5]));
                //String newString = Arrays.toString(inputStream.next().split(","));
                //newString = newString.substring(1, newString.length() - 1);
                //contacts.add(newString);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
        }
        return contacts;
    }
    
    public String read(String file) throws FileNotFoundException, IOException {
        String line = null;
        BufferedReader stream = null;
        List<List<String>> csvData = new ArrayList<List<String>>();

        try {
            stream = new BufferedReader(new FileReader(file));
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(",");
                List<String> dataLine = new ArrayList<String>(splitted.length);
                for (String data : splitted) {
                    dataLine.add(data);
                    line = data;
                }
                csvData.add(dataLine);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return line;
    }
}
