
package Fantasy_Arena.levels;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Fantasy_Arena.core.*;

public class LevelDatabase {

    public static final String DATABASE_PATH = "database.txt";
    private ArrayList<Champion[][]> database;

    public LevelDatabase() {
        load();
    }

    public void load() {
        try {
            Scanner sc = new Scanner(new FileInputStream(DATABASE_PATH));
            int count = sc.nextInt();
            database = new ArrayList<Champion[][]>(count);
            sc.nextLine();
            for (int i = 0; i < count; i++) {
                int teamLength = sc.nextInt();
                Champion[][] teams = new Champion[2][teamLength];
                sc.nextLine();
                for(int j = 0; j < 2; j++){
                    for(int h = 0; h < teamLength; h++){
                        teams[j][h] = Champion.generateFromString(sc.nextLine());
                    }
                }
                    database.add(teams);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file cannot be found.");
            System.exit(0);
        }
    }

    public int getSize() {
        return database.size();
    }

    public Champion[][] getTeams(int i) {
        return database.get(i);
    }

    public void printDatabase(){
        for(int i = 0; i < database.size(); i++){
            System.out.println();
            System.out.println("---Level " + (i+1));
            for(int j = 0; j < 2; j++){
                System.out.println();
                System.out.println("Team " + (j+1));
                for(int h = 0; h < database.get(i)[j].length; h++){
                    System.out.println(database.get(i)[j][h].toString());
                }
            }
        }
    }
}

