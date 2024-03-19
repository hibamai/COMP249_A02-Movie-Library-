package DriverA02;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class DriverA02 {

    //method to check if there are missing quotes
    public static boolean missingQuote(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                count++;
            }
        }
        if (count % 2 == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    //method to check if there's a coma in a substring of the title with double quotes
    public static boolean presenceOfComa(String line) {
        int count = 0;
        int indexStart = 0;
        int indexEnd = 0;
        boolean found = false;
        String substring = "";
        if (missingQuote(line) == false) { //check commas only if there are no missing quotes
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '"') {    
                     count++;
                }
                if (count == 1 && !found) {
                    found = true;
                    indexStart = (i+1);
                }
                else if (count == 2) {
                    indexEnd = i;
                    break;
                }
            }
            substring = line.substring(indexStart,indexEnd); //substring from the first quote  
        }
        if (substring.contains(",")) {
            return true;
        }
        else {
            return false;
        }
    }

    //method to check if the year field is an integer
    public static boolean validYear(String[] array) {
        try {
            int year = Integer.parseInt(array[0]);
            return true;  
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    //method to get the year after successfully checking if it's an integer
    public static int getYear(String[] array) {
        if (validYear(array) == true) {
            return Integer.parseInt(array[0]);
        }
        else {
            return 0;
        }
    }

    //method to check if the duration field is an integer
    public static boolean validDuration(String[] array, String line) {
        if ((presenceOfComa(line) == true) && !array[3].equals("")) {
            try {
                int duration = Integer.parseInt(array[3]);
                return true;  
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        else {
            try {
                int duration = Integer.parseInt(array[2]);
                return true;  
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
    }

    //method to get the duration after successfully checking if it's an integer
    public static int getDuration(String[] array, String line) {
        if (validDuration(array, line) == true && presenceOfComa(line) == true){
            return Integer.parseInt(array[3]);
        }
        else if (validDuration(array, line) == true) {
            return Integer.parseInt(array[2]);
        }
        else {
            return 0;
        }
    }

    public static void do_part1() {
        Scanner input = null;
        PrintWriter musicalMovie = null;
        PrintWriter comedyMovie = null;
        PrintWriter AnimationMovie = null;
        PrintWriter AdventureMovie = null;
        PrintWriter DramaMovie = null;
        PrintWriter CrimeMovie = null;
        PrintWriter BiographyMovie = null;
        PrintWriter HorrorMovie = null;
        PrintWriter ActionMovie = null;
        PrintWriter DocumentaryMovie = null;
        PrintWriter FantasyMovie = null;
        PrintWriter MysteryMovie = null;
        PrintWriter SciFiMovie = null;
        PrintWriter FamilyMovie = null;
        PrintWriter WesternMovie = null;
        PrintWriter RomanceMovie = null;
        PrintWriter ThrillerMovie = null;
        PrintWriter badMovie = null;
        String line = null;
        String genres;
     
        try {
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/COMP249_A02/src/DriverA02/Movies1990.csv"));
            badMovie = new PrintWriter(new FileOutputStream("bad_movie_records.txt"));
            musicalMovie = new PrintWriter(new FileOutputStream("musical.csv"));
            comedyMovie = new PrintWriter(new FileOutputStream("comedy.csv"));
            AnimationMovie = new PrintWriter(new FileOutputStream("animation.csv"));
            AdventureMovie = new PrintWriter(new FileOutputStream("adventure.csv"));
            DramaMovie = new PrintWriter(new FileOutputStream("drama.csv"));
            CrimeMovie = new PrintWriter(new FileOutputStream("crime.csv"));
            BiographyMovie = new PrintWriter(new FileOutputStream("biography.csv"));
            HorrorMovie = new PrintWriter(new FileOutputStream("horror.csv"));
            ActionMovie = new PrintWriter(new FileOutputStream("action.csv"));
            DocumentaryMovie = new PrintWriter(new FileOutputStream("documentary.csv"));
            FantasyMovie = new PrintWriter(new FileOutputStream("fantasy.csv"));
            MysteryMovie = new PrintWriter(new FileOutputStream("mystery.csv"));
            SciFiMovie = new PrintWriter(new FileOutputStream("sci-fi.csv"));
            FamilyMovie = new PrintWriter(new FileOutputStream("family.csv"));
            WesternMovie = new PrintWriter(new FileOutputStream("western.csv"));
            RomanceMovie = new PrintWriter(new FileOutputStream("romance.csv"));
            ThrillerMovie = new PrintWriter(new FileOutputStream("thriller.csv"));
            
            for (int x = 0; x < 22; x++) { 
                line = input.nextLine();
                String[] parts = line.split(",");
                boolean errorFound = false;
                boolean missingDuration = false;
                boolean missingGenres = false;

                //missing quotes
                if (missingQuote(line) == true) {
                    try {
                        throw new MissingQuotesException("Missing quotes");
                        }
                    catch (MissingQuotesException e)    {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Eror missing quote (syntax error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                    }
                }

                //missing fields
                if (parts.length < 10) {
                    try {
                        throw new MissingFieldsException("Missing fields");
                    }
                    catch (MissingFieldsException e) {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error missing fields (syntax error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                    }
                }
                if (presenceOfComa(line)) {
                    if (parts.length == 10) {
                        try {
                            throw new MissingFieldsException("Missing fields");
                        }
                        catch (MissingFieldsException e) {
                            errorFound = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing fields (syntax error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                        }
                    }
                }

                //excess fields
                if (parts.length > 10 && !presenceOfComa(line)) {                      
                        try {
                            throw new ExcessFieldsException("Excess fields");
                        }
                        catch (ExcessFieldsException e) {
                            errorFound = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error excess fields (syntax error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                        }    
                }   

                //missing year
                if (line.charAt(0) == ',') {
                    try {
                        throw new BadYearException("Missing year");
                    }
                    catch (BadYearException e) {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error missing year (semantic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                    }
                }

                //invalid year
                if ((!validYear(parts) || validYear(parts) && (getYear(parts) < 1990 || getYear(parts) > 1999)) && (line.charAt(0) != ',')){
                    try { 
                         throw new BadYearException("Invalid year");
                        }
                    catch (BadYearException e) {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error invalid year (semactic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                    }
                }

                String t = "Hello";


                //missing duration
                if (parts.length > 10 && presenceOfComa(line)) {
                    if (parts[3].equals("")) {
                        try {
                            throw new BadDurationException("Missing duration");
                        }
                        catch (BadDurationException e) {
                            errorFound = true;
                            missingDuration = true; //to be used as condition for invalid year error so that the line won't be checked for invalid duration if it's missing
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing duration (semantic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                        }
                    }
                }
                if (parts.length > 10 || parts.length <= 10) {
                    if (parts[2].equals("")) {
                        try {
                            throw new BadDurationException("Missing duration");
                        }
                        catch (BadDurationException e) {
                            errorFound = true;
                            missingDuration = true; //to be used as condition for invalid year error so that the line won't be checked for invalid duration if it's missing
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing duration (semantic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                        }
                    }
                }
                
                //invalid duration
                if ((!missingDuration) && (!validDuration(parts, line) || validDuration(parts, line) && ((getDuration(parts,line) > 300 || getDuration(parts,line) < 30)))){
                    try {
                        throw new BadDurationException("Invalid duration");
                    }
                    catch (BadDurationException e) {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error invalid duration (semantic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                    }
                }

                //missing genres
                if (parts.length > 10 && presenceOfComa(line)) {
                    if (parts[4].equals("")) {
                        try {
                            throw new BadDurationException("Missing genre");
                        }
                        catch (BadDurationException e) {
                            errorFound = true;
                            missingGenres = true; //to be used as condition for invalid genre error so that the line won't be checked for invalid genre if it's missing
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing genre (semantic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                        }
                    }
                }
                if (parts.length > 10 || parts.length <= 10) {
                    if (parts[3].equals("")) {
                        try {
                            throw new BadDurationException("Missing genre");
                        }
                        catch (BadDurationException e) {
                            errorFound = true;
                            missingGenres = true; //to be used as condition for invalid genre error so that the line won't be checked for invalid genre if it's missing
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing genre (semantic error): \n" + line + "\nFound in Movie1990.csv, line " + (x+1));
                        }
                    }
                }

                //invalid genres
                

                //missing rating

                //invalid rating

                //missing score

                //invalid score

                //missing names

                //missing title


                //if no error is found, the line will be written to the corresponding file
                if (!errorFound) {
                    if (parts.length > 10) { //won't be considered as excess field if there's a comma in the title, if it's not the case, the error would have been caught in the previous if statement
                        genres = parts[4];
                    }
                    else {
                        genres = parts[3]; 
                    }
                    switch (genres) {
                        case "Musical":
                        musicalMovie.println(line);
                        break;
                        
                        case "Comedy":
                        comedyMovie.println(line);
                        break;

                        case "Animation":
                        AnimationMovie.println(line);
                        break;

                        case "Adventure":
                        AdventureMovie.println(line);
                        break;

                        case "Drama":
                        DramaMovie.println(line);
                        break;

                        case "Crime":
                        CrimeMovie.println(line);
                        break;

                        case "Biography":
                        BiographyMovie.println(line);
                        break;

                        case "Horror":
                        HorrorMovie.println(line);
                        break;

                        case "Action":
                        ActionMovie.println(line);
                        break;

                        case "Documentary":
                        DocumentaryMovie.println(line);
                        break;

                        case "Fantasy":
                        FantasyMovie.println(line);
                        break;

                        case "Mystery":
                        MysteryMovie.println(line);
                        break;

                        case "Sci-fi":
                        SciFiMovie.println(line);
                        break;

                        case "Family":
                        FamilyMovie.println(line);
                        break;

                        case "Western":
                        WesternMovie.println(line);
                        break;

                        case "Romance":
                        RomanceMovie.println(line);
                        break;

                        case "Thriller":
                        ThrillerMovie.println(line);
                        break;
                    }
                }     
        }
        badMovie.close();
        comedyMovie.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        input.close();

}

    public static void main(String[] args){
        do_part1();

        // part 1's manifest file

        // part 2's manifest file 
        
        // part 3's manifest file

    }

}
