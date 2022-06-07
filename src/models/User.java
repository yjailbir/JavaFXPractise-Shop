package models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class User {
    private String login;
    private  String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTheUserExists() throws FileNotFoundException {
        FileReader reader = new FileReader("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\UsersData.txt");
        Scanner scanner = new Scanner(reader);
        String loginFromText, passwordFromText;
       while (scanner.hasNextLine()){
           loginFromText = scanner.nextLine();
           passwordFromText = scanner.nextLine();
           if(loginFromText.equals(getLogin())&&passwordFromText.equals(getPassword())){
               scanner.close();
               return true;
           }
       }
        scanner.close();
       return false;
    }
}
