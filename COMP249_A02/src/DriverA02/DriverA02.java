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
        String genres;
       

        try {
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/COMP249_A02/src/DriverA02/Movies1990.csv"));

            while (input.hasNextLine()) {
                line = input.nextLine();
                int count = 0;
                int index = 0;

                String[] parts = line.split(",");

                try {
                    output = new PrintWriter(new FileOutputStream("bad_movie_records.csv", true));
                    int year = Integer.parseInt(parts[2]);
                }
                catch (NumberFormatException e) {
                    if (parts[1].charAt(0) != '"' || parts[1].charAt(parts[1].length() - 1) != '"') {
                        try {
                            throw new MissingQuotesException("Missing quotes");
                        }
                        catch (MissingQuotesException ex) {
                            output.println("Missing Quote");
                        }
                        finally {
                            output.close();
                        }
                    }
                }


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
                            System.out.println("hello");
                            output = new PrintWriter(new FileOutputStream("comedy.csv", true));
                            output.println(line);
                            break;
                        }
                    }
                }
                output.close();  
                
            }
        }
        input.close();
       
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        
    }
        





    public static void main(String[] args){
        do_part1();
    }

    
}
