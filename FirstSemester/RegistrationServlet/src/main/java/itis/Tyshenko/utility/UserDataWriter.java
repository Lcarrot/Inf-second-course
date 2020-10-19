package itis.Tyshenko.utility;

import com.opencsv.CSVWriter;
import itis.Tyshenko.entity.User;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataWriter {

    private static final String path = "C:\\Users\\olga1\\Desktop\\projects\\info\\FirstSemester\\RegistrationServlet\\src\\main\\webapp\\data\\data.csv";
    public static void writeUserInCSV(User user) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(path,true));
            String gender = user.isGender() ? "1" : "0";
            String[] data = new String[]{user.getName(), user.getEmail(), user.getCountry(), String.valueOf(user.getHashPassword()), gender};
            writer.writeNext(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
