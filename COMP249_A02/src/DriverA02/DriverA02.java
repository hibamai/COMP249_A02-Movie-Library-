package DriverA02;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DriverA02 {
    //creating the file part1_manifest
    public static void createFirstFile(){
        PrintWriter firstFile=null;
        try{
            firstFile = new PrintWriter(new FileOutputStream("part1_manifest.txt"));
            firstFile.println("Movies1990.csv");
            firstFile.println("Movies1991.csv");
            firstFile.println("Movies1992.csv");
            firstFile.println("Movies1993.csv");
            firstFile.println("Movies1994.csv");
            firstFile.println("Movies1995.csv");
            firstFile.println("Movies1996.csv");
            firstFile.println("Movies1997.csv");
            firstFile.println("Movies1998.csv");
            firstFile.println("Movies1999.csv");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
            firstFile.close();
     
    }

    //method to check if there are quotes in the line
    public static boolean presenceOfQuote(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                return true;
            }
        }
        return false;
    }

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

    //method to check where the double quotes are
    public static int indexOfQuote(String line, String[] array) {
        int index = -1;
        if (presenceOfQuote(line) == true && !missingQuote(line)) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].contains("\"")) {
                    index = i;
                    break;
                }
                else {
                    index = -1;
                } 
            }
            return index;
        }
        else {
            return index;
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

    //method to check if the score field is a double
    public static boolean validScore(String[] array, String line) {
        if ((presenceOfComa(line) == true) && !array[6].equals("")) {
            try {
                double score = Double.parseDouble(array[6]);
                return true;  
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        
        else if (indexOfQuote(line, array) == 5) {
            String subString = array[5].substring(1, array[5].length()-1);
            try {
                double score = Double.parseDouble(subString);
                return true;  
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        else {
            try {
                double score = Double.parseDouble(array[5]);
                return true;  
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
    }

    //method to get the score after successfully checking if it's a double
    public static double getScore(String[] array, String line) {
        if (validScore(array, line) == true && presenceOfComa(line) == true){
            return Double.parseDouble(array[6]);
        }
        else if (validScore(array, line) == true && indexOfQuote(line, array) == 5) {
            String subString = array[5].substring(1, array[5].length()-1);
            return Double.parseDouble(subString);
        }
        else if (validScore(array, line) == true) {
            return Double.parseDouble(array[5]);
        }
        else {
            return 0;
        }
    }

    public static String do_part1(String file) {
        Scanner input = null;
        Scanner selectFile = null;
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
        PrintWriter part2 = null;
        String line = null;
        String genres;
        int fileDone = 0;
     
        try {
            selectFile = new Scanner(new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+file));
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
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

            part2 = new PrintWriter (new FileOutputStream("part2_manifest.txt"));
            part2.println("musical.csv");
            part2.println("comedy.csv");
            part2.println("animation.csv");
            part2.println("adventure.csv");
            part2.println("drama.csv");
            part2.println("crime.csv");
            part2.println("biography.csv");
            part2.println("horror.csv");
            part2.println("action.csv");
            part2.println("documentary.csv");
            part2.println("fantasy.csv");
            part2.println("mystery.csv");
            part2.println("sci-fi.csv");
            part2.println("family.csv");
            part2.println("western.csv");
            part2.println("romance.csv");
            part2.println("thriller.csv");
   
        while (input.hasNextLine()){
             for (int x = 0;input.hasNextLine() ; x++){
                line = input.nextLine();
                String[] parts = line.split(",");
                boolean errorFound = false;
                boolean missingDuration = false;
                boolean missingGenres = false;
                boolean missingRating = false;
                boolean missingScore = false;
                

                //missing quotes
                if (missingQuote(line) == true) {
                    try {
                        throw new MissingQuotesException("Missing quotes");
                        }
                    catch (MissingQuotesException e)    {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error missing quote (syntax error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
                        badMovie.println("Error missing fields (syntax error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
                            badMovie.println("Error missing fields (syntax error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
                            System.out.println(x);
                            System.out.println(e.getMessage());
                            badMovie.println("Error excess fields (syntax error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }    
                }   

                //missing year
                if (parts[0].equals("")) {
                    try {
                        throw new BadYearException("Missing year");
                    }
                    catch (BadYearException e) {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error missing year (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                    }
                }

                //invalid year
                if ((!validYear(parts) || (validYear(parts) && (getYear(parts) < 1990 || getYear(parts) > 1999)) && (line.charAt(0) != ','))){
                    System.out.println(x);
                    try { 
                         throw new BadYearException("Invalid year");
                        }
                    catch (BadYearException e) {
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error invalid year (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                    }
                }
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
                            badMovie.println("Error missing duration (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
                            badMovie.println("Error missing duration (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
                        badMovie.println("Error invalid duration (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                    }
                }

                //missing genres
                if (parts.length > 10 && presenceOfComa(line)) {
                    if (parts[4].equals("")) {
                        try {
                            throw new BadGenreException("Missing genre");
                        }
                        catch (BadGenreException e) {
                            errorFound = true;
                            missingGenres = true; //to be used as condition for invalid genre error so that the line won't be checked for invalid genre if it's missing
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing genre (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                if (parts.length == 10) {
                    if (parts[3].equals("")) {
                        try {
                            throw new BadGenreException("Missing genre");
                        }
                        catch (BadGenreException e) {
                            errorFound = true;
                            missingGenres = true; //to be used as condition for invalid genre error so that the line won't be checked for invalid genre if it's missing
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing genre (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }

                //invalid genres
                if(!missingGenres){
                    if (parts.length > 10 && presenceOfComa(line)) {
                        if (!(parts[4].equals("Action")||parts[4].equals("Adventure")||parts[4].equals("Animation")||parts[4].equals("Biography")||parts[4].equals("Comedy")||parts[4].equals("Crime")||parts[4].equals("Documentary")||parts[4].equals("Drama")||parts[4].equals("Family")||parts[4].equals("Fantasy")||parts[4].equals("Horror")||parts[4].equals("Musical")||parts[4].equals("Mystery")||parts[4].equals("Romance")||parts[4].equals("Sci-fi")||parts[4].equals("Thriller")||parts[4].equals("Western"))) {
                            try {
                                throw new BadGenreException("Invalid genre");
                            }
                            catch (BadGenreException e) {
                                errorFound = true;
                                System.out.println(e.getMessage());
                                badMovie.println("Error invalid genre (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                            }
                        }
                    }
                    else if (parts.length == 10) {
                        if (indexOfQuote(line, parts) == 3) {
                            String subString = parts[3].substring(1, parts[3].length()-1);
                            if (!(subString.equals("Action") || subString.equals("Adventure") || subString.equals("Animation") || subString.equals("Biography") || subString.equals("Comedy") || subString.equals("Crime") || subString.equals("Documentary") || subString.equals("Drama") || subString.equals("Family") || subString.equals("Fantasy") || subString.equals("Horror") || subString.equals("Musical") || subString.equals("Mystery") || subString.equals("Romance") || subString.equals("Sci-fi") || subString.equals("Thriller") || subString.equals("Western"))) {
                                try {
                                    throw new BadGenreException("Invalid genre");
                                }
                                catch (BadGenreException e) {
                                    errorFound = true;
                                    System.out.println(e.getMessage());
                                    badMovie.println("Error invalid genre (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                                }
                            }
                        }
                        else if (!(parts[3].equals("Action")||parts[3].equals("Adventure")||parts[3].equals("Animation")||parts[3].equals("Biography")||parts[3].equals("Comedy")||parts[3].equals("Crime")||parts[3].equals("Documentary")||parts[3].equals("Drama")||parts[3].equals("Family")||parts[3].equals("Fantasy")||parts[3].equals("Horror")||parts[3].equals("Musical")||parts[3].equals("Mystery")||parts[3].equals("Romance")||parts[3].equals("Sci-fi")||parts[3].equals("Thriller")||parts[3].equals("Western"))) {
                            try {
                                throw new BadGenreException("Invalid genre");
                            }
                            catch (BadGenreException e) {
                                errorFound = true;
                                System.out.println(e.getMessage());
                                badMovie.println("Error invalid genre (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                            }
                        }
                    }
                }
                

                //missing rating
                if (parts.length > 10 && presenceOfComa(line)){
                    if(parts[5].equals("")){
                        try{
                            throw new BadRatingException("Missing rating");
                        }
                        catch (BadRatingException e){
                            errorFound = true;
                            missingRating = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                if (parts.length == 10){
                    if(parts[4].equals("")){
                        try{
                            throw new BadRatingException("Missing rating");
                        }
                        catch (BadRatingException e){
                            errorFound = true;
                            missingRating = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }

                //invalid rating
                if (!missingRating) {
                    if (parts.length > 10 && presenceOfComa(line)){
                        if(!(parts[5].equals("PG")||parts[5].equals("Unrated")||parts[5].equals("G")||parts[5].equals("R")||parts[5].equals("PG-13")||parts[5].equals("NC-17"))){
                            try{
                                throw new BadRatingException("Invalid rating");
                            }
                            catch (BadRatingException e){
                                errorFound = true;
                                System.out.println(e.getMessage());
                                badMovie.println("Error invalid rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                            }
                        }
                    }
                    if (parts.length==10){
                        if (indexOfQuote(line, parts) == 4) {
                            String subString = parts[4].substring(1, parts[4].length()-1);
                            if(!(subString.equals("PG")||subString.equals("Unrated")||subString.equals("G")||subString.equals("R")||subString.equals("PG-13")||subString.equals("NC-17"))){
                                try{
                                    throw new BadRatingException("Invalid rating");
                                }
                                catch (BadRatingException e){
                                    errorFound = true;
                                    System.out.println(e.getMessage());
                                    badMovie.println("Error invalid rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                                }
                            }
                        }
                        else if(!(parts[4].equals("PG")||parts[4].equals("Unrated")||parts[4].equals("G")||parts[4].equals("R")||parts[4].equals("PG-13")||parts[4].equals("NC-17"))){
                            try{
                                throw new BadRatingException("Invalid rating");
                            }
                            catch (BadRatingException e){
                                errorFound = true;
                                System.out.println(e.getMessage());
                                badMovie.println("Error invalid rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                            }
                        }
                    }
                }

                //missing score
                if (parts.length > 10 && presenceOfComa(line)){
                    if(parts[6].equals("")){
                        try{
                            throw new BadScoreException("Missing score");
                        }
                        catch (BadScoreException e){
                            errorFound = true;
                            missingScore = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing score (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                if (parts.length==10 && !presenceOfComa(line)){
                    if(parts[5].equals("")){
                        try{
                            throw new BadScoreException("Missing score");
                        }
                        catch (BadScoreException e){
                            errorFound = true;
                            missingScore = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing score (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }

                //invalid score
                if ((!missingScore && !validScore(parts, line)) || (validScore(parts, line) && ((getScore(parts, line) < 0 || getScore(parts, line) > 10)))){
                    try{
                        throw new BadScoreException("Invalid score");
                    }
                    catch (BadScoreException e){
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error invalid score (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                    }
                }

                //missing names
                if (parts.length > 10 && presenceOfComa(line)){
                    if(parts[7].equals("")||parts[8].equals("")||parts[9].equals("")||parts[10].equals("")){
                        try{
                            throw new BadNameException("Missing names");
                        }
                        catch (BadNameException e){
                            errorFound = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing name(s) (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                else if (parts.length==10){
                    if(parts[6].equals("")||parts[7].equals("")||parts[8].equals("")||parts[9].equals("")){
                        try{
                            throw new BadNameException("Missing names");
                        }
                        catch (BadNameException e){
                            errorFound = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing name(s) (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                //missing title
                if (parts[1].equals("")){
                    try{
                        throw new BadTitleException("Missing Title");
                    }
                    catch(BadTitleException e){
                        errorFound = true;
                        System.out.println(e.getMessage());
                        badMovie.println("Error missing title (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                    }
                }

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
        
        // switching files
        if (!input.hasNextLine() && fileDone==0){ //switching to file 1991
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        } 
        else if (!input.hasNextLine() && fileDone==1){ //switching to file 1992
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/COMP249_A02//src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==2){ //switching to file 1993
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==3){ //switching to file 1994
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==4){ //switching to file 1995
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==5){ //switching to file 1996
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==6){ //switching to file 1997
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==7){ //switching to file 1998
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
        else if (!input.hasNextLine() && fileDone==8){ //switching to file 1999
            input = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02//COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
        }
}
        badMovie.close();
        comedyMovie.close();
        musicalMovie.close();
        AnimationMovie.close();
        AdventureMovie.close();
        DramaMovie.close();
        CrimeMovie.close();
        BiographyMovie.close();
        HorrorMovie.close();
        ActionMovie.close();
        DocumentaryMovie.close();
        FantasyMovie.close();
        MysteryMovie.close();
        SciFiMovie.close();
        FamilyMovie.close();
        WesternMovie.close();
        RomanceMovie.close();
        ThrillerMovie.close();
        part2.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        input.close();
        return "part2_manifest.txt";
    }
    public static String do_part2(String file){
        Scanner input = null;
        Scanner currentFile = null;

        

        /*PrintWriter outputMusical =null;
        PrintWriter outputComedy = null;
        PrintWriter outputAnimation =null;
        PrintWriter outputAdventure = null;
        PrintWriter outputDrama=null;
        PrintWriter outputCrime = null;
        PrintWriter outputBiography =null;
        PrintWriter outputHorror = null;
        PrintWriter outputAction =null;
        PrintWriter outputDocumentary = null;
        PrintWriter outputFantasy =null;
        PrintWriter outputMystery = null;
        PrintWriter outputSciFi =null;
        PrintWriter outputFamily = null;
        PrintWriter outputWestern =null;
        PrintWriter outputRomance = null;
        PrintWriter outputThriller =null;*/
        PrintWriter part3 = null;

        Movie[] trialArr  = new Movie[1];
        trialArr[0] = new Movie (2009, "The Trial", 1993, "Drama", "PG-13", 7.8, "Steven Spielberg", "Tom Hanks", "Leonardo DiCaprio", "Meryl Streep");
        
        

        try{
            input = new Scanner(new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+file));
            String line1 = input.nextLine();
            currentFile = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+line1));

            ObjectOutputStream trial = new ObjectOutputStream(new FileOutputStream("trial.ser"));
            trial.writeObject(trialArr); 
            trial.close();

            ObjectOutputStream outputMusical = new ObjectOutputStream(new FileOutputStream("musical.ser"));
            ObjectOutputStream outputComedy = new ObjectOutputStream(new FileOutputStream("comedy.ser"));
            ObjectOutputStream outputAnimation = new ObjectOutputStream(new FileOutputStream("animation.ser"));
            ObjectOutputStream outputAdventure = new ObjectOutputStream(new FileOutputStream("adventure.ser"));
            ObjectOutputStream outputDrama = new ObjectOutputStream(new FileOutputStream("drama.ser"));
            ObjectOutputStream outputCrime = new ObjectOutputStream(new FileOutputStream("crime.ser"));
            ObjectOutputStream outputBiography = new ObjectOutputStream(new FileOutputStream("biography.ser"));
            ObjectOutputStream outputHorror = new ObjectOutputStream(new FileOutputStream("horror.ser"));
            ObjectOutputStream outputAction = new ObjectOutputStream(new FileOutputStream("action.ser"));
            ObjectOutputStream outputDocumentary = new ObjectOutputStream(new FileOutputStream("documentary.ser"));
            ObjectOutputStream outputFantasy = new ObjectOutputStream(new FileOutputStream("fantasy.ser"));
            ObjectOutputStream outputMystery = new ObjectOutputStream(new FileOutputStream("mystery.ser"));
            ObjectOutputStream outputSciFi = new ObjectOutputStream(new FileOutputStream("sci-fi.ser"));
            ObjectOutputStream outputFamily = new ObjectOutputStream(new FileOutputStream("family.ser"));
            ObjectOutputStream outputWestern = new ObjectOutputStream(new FileOutputStream("western.ser"));
            ObjectOutputStream outputRomance = new ObjectOutputStream(new FileOutputStream("romance.ser"));
            ObjectOutputStream outputThriller = new ObjectOutputStream(new FileOutputStream("thriller.ser"));

            /*outputMusical = new PrintWriter(new FileOutputStream("musical.ser"));
            outputComedy = new PrintWriter(new FileOutputStream("comedy.ser"));
            outputAnimation = new PrintWriter(new FileOutputStream("animation.ser"));
            outputAdventure = new PrintWriter(new FileOutputStream("adventure.ser"));
            outputDrama = new PrintWriter(new FileOutputStream("drama.ser"));
            outputCrime = new PrintWriter(new FileOutputStream("crime.ser"));
            outputBiography = new PrintWriter(new FileOutputStream("biography.ser"));
            outputHorror = new PrintWriter(new FileOutputStream("horror.ser"));
            outputAction = new PrintWriter(new FileOutputStream("action.ser"));
            outputDocumentary = new PrintWriter(new FileOutputStream("documentary.ser"));
            outputFantasy = new PrintWriter(new FileOutputStream("fantasy.ser"));
            outputMystery = new PrintWriter(new FileOutputStream("mystery.ser"));
            outputSciFi = new PrintWriter(new FileOutputStream("sci-fi.ser"));
            outputFamily = new PrintWriter(new FileOutputStream("family.ser"));
            outputWestern = new PrintWriter(new FileOutputStream("western.ser"));
            outputRomance = new PrintWriter(new FileOutputStream("romance.ser"));
            outputThriller = new PrintWriter(new FileOutputStream("thriller.ser"));*/

            part3 = new PrintWriter (new FileOutputStream("part3_manifest.txt"));
            part3.println("musical.ser");
            part3.println("comedy.ser");
            part3.println("animation.ser");
            part3.println("adventure.ser");
            part3.println("drama.ser");
            part3.println("crime.ser");
            part3.println("biography.ser");
            part3.println("horror.ser");
            part3.println("action.ser");
            part3.println("documentary.ser");
            part3.println("fantasy.ser");
            part3.println("mystery.ser");
            part3.println("sci-fi.ser");
            part3.println("family.ser");
            part3.println("western.ser");
            part3.println("romance.ser");
            part3.println("thriller.ser");
        
            int fileDone = 0;
            while (input.hasNextLine()){  
                Movie currentMovie = new Movie();
                Movie[] currentMovieArr = new Movie[200];
                
                //total number of movies = 607
                //total number of valid movies = 579

                for (int i = 0; i<currentMovieArr.length && currentFile.hasNextLine(); i++){  
                    if (currentFile.hasNextLine()){
                        for (int j = 0; currentFile.hasNextLine(); j++){
                            String line = currentFile.nextLine();
                            String[] parts = line.split(",");
                            if (parts.length == 10 && indexOfQuote(line, parts) == 5) {
                                String subString = parts[5].substring(1, parts[5].length()-1);
                                currentMovie = new Movie(Integer.parseInt(parts[0]),parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], Double.parseDouble(subString), parts[6], parts[7], parts[8], parts[9]) ;
                            }

                            else if (parts.length == 10){
                                currentMovie = new Movie(Integer.parseInt(parts[0]),parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], Double.parseDouble(parts[5]), parts[6], parts[7], parts[8], parts[9]) ;
                            }
                            else{
                                currentMovie = new Movie(Integer.parseInt(parts[0]),parts[1]+", "+parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], Double.parseDouble(parts[6]), parts[7], parts[8], parts[9], parts[10]) ;
                            }
                            
                            currentMovieArr[j] = currentMovie;
                        }

                        //placing the array created in its respective file
                        switch(fileDone){
                            case 0: outputMusical.writeObject(currentMovieArr);
                            outputMusical.close();
                            break;
                            case 1: outputComedy.writeObject(currentMovieArr);
                            outputComedy.close();
                            break;
                            case 2: outputAnimation.writeObject(currentMovieArr);
                            outputAnimation.close();
                            break;
                            case 3: outputAdventure.writeObject(currentMovieArr);
                            outputAdventure.close();
                            break;
                            case 4: outputDrama.writeObject(currentMovieArr);
                            outputDrama.close();
                            break;
                            case 5: outputCrime.writeObject(currentMovieArr);
                            outputCrime.close();
                            break;
                            case 6: outputBiography.writeObject(currentMovieArr);
                            outputBiography.close();
                            break;
                            case 7: outputHorror.writeObject(currentMovieArr);
                            outputHorror.close();
                            break;
                            case 8: outputAction.writeObject(currentMovieArr);
                            outputAction.close();
                            break;
                            case 9: outputDocumentary.writeObject(currentMovieArr);
                            outputDocumentary.close();
                            break;
                            case 10: outputFantasy.writeObject(currentMovieArr);
                            outputFantasy.close();
                            break;
                            case 11: outputMystery.writeObject(currentMovieArr);
                            outputMystery.close();
                            break;
                            case 12: outputSciFi.writeObject(currentMovieArr);
                            outputSciFi.close();
                            break;
                            case 13: outputFamily.writeObject(currentMovieArr);
                            outputFamily.close();
                            break;
                            case 14: outputWestern.writeObject(currentMovieArr);
                            outputWestern.close();
                            break;
                            case 15: outputRomance.writeObject(currentMovieArr);
                            outputRomance.close();
                            break;
                            case 16: outputThriller.writeObject(currentMovieArr);
                            outputThriller.close();
                            break; 
                        }
                    }
                }
                if (!currentFile.hasNextLine() && input.hasNextLine()){
                    String line = input.nextLine();
                    currentFile = new Scanner (new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+line));
                    fileDone++;
                }
            }
            
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
            System.exit(0);
        }
        catch(IOException e){
            System.out.println("IO Exception");
            System.exit(0);
        }

        input.close();
        currentFile.close();
        part3.close();

        return "part3_manifest.txt";
    }

    

    public static Movie[][] do_part3(String file){
        //Deserialization
        
        Scanner input = null;
        Scanner currentFile = null;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        String line = null;
        Movie[][] all_movies = new Movie[17][200]; //create a 2D array to store all the movies

        try {
            input = new Scanner(new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+file)); //read from manifest file 3
            line = input.nextLine(); //read the first line
            fileIn = new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+line); //read from the file in the manifest file
            in = new ObjectInputStream(fileIn); //create an object input stream
            for (int i = 0; i < all_movies.length; i++) {
                if (input.hasNextLine()) {
                    while (fileIn.available() > 0 && fileIn != null) {
                        Movie[] movieArr = (Movie[]) in.readObject(); //cast the object to a movie array
                        all_movies[i] = movieArr; //store the movie array in the 2D array
                    }
                }
                if (fileIn.available() == 0 && input.hasNextLine()) {
                    line = input.nextLine();
                    fileIn = new FileInputStream("/Users/thilanthiduong/Documents/GitHub/COMP249_A02/"+line); //read from the file in the manifest file
                    in = new ObjectInputStream(fileIn); //create an object input stream
                }
            }
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            System.exit(0);
        }
        catch(ClassNotFoundException e){
            System.out.println("Class not found");
        }
        catch(IOException e) {
            System.out.println("This file has no data");
        }

        input.close();
        return all_movies; 
    }


    public static void menuCreation() {
        
    }



    public static void main(String[] args){
        createFirstFile();

        // part 1's manifest file
        String part1_manifest = "part1_manifest.txt";
        // part 2's manifest file
        String part2_manifest = do_part1(part1_manifest);
        // part 3's manifest file
        String part3_manifest = do_part2(part2_manifest);
        
        do_part3(part3_manifest);

    }

}
