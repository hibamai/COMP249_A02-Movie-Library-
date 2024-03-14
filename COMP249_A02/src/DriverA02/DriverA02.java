package DriverA02;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DriverA02 {

    

    public static void do_part1() {
        Scanner input = null;
        PrintWriter output = null;
        String line = null;
        int year;
        String title;
        int duration;
        String genres;
        String rating;
        double score;
        String director;
        String actor1;
        String actor2;
        String actor3;

        try {
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/COMP249_A02/src/DriverA02/Movies1990.csv"));
            output = new PrintWriter(new FileOutputStream("musical.csv"));
            while (input.hasNextLine()) {
                line = input.nextLine();
                int count = 0;
                int index = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',') {
                        count++;
                        if (count == 3) {
                            index = i;
                        }
                    }
                    if (count == 4) {
                        genres = line.substring(index + 1, i);
                        switch (genres) {
                            case "Comedy":
                            output.println(line);
                            break;
                        }
                        break;
                    }
                }
                
                /*switch (genres) {
                    case "Unserwood":
                    output = new PrintWriter(new FileOutputStream("musical.csv"));
                    output.println(genres);
                    break;

                    case "Comedy":
                    break;

                    case "Animation":
                    break;

                    case "Adventure":
                    break;

                    case "Drama":
                    break;

                    case "Crime":
                    break;

                    case "Biography":
                    break;

                    case "Horror":
                    break;

                    case "Action":
                    break;

                    case "Documentary":
                    break;

                    case "Fantasy":
                    break;

                    case "Mystery":
                    break;

                    case "Sci-fi":
                    break;

                    case "Family":
                    break;

                    case "Western":
                    break;

                    case "Romance":
                    break;

                    case "Thriller":
                    break;
                }
            }
            */    
        }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        input.close();
       output.close();  

        
        
    }



    public static void main(String[] args){
        do_part1();
    }
}
