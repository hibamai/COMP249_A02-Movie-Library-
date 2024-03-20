package DriverA02;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;


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
            selectFile = new Scanner(new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/"+file));
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
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
             for (int x=0;input.hasNextLine() ;x++){
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
                 if (parts.length == 10) {
                    if (!(parts[3].equals("Action")||parts[3].equals("Adventure")||parts[3].equals("Animation")||parts[3].equals("Biography")||parts[3].equals("Comedy")||parts[3].equals("Crime")||parts[3].equals("Documentary")||parts[3].equals("Drama")||parts[3].equals("Family")||parts[3].equals("Fantasy")||parts[3].equals("Horror")||parts[3].equals("Musical")||parts[3].equals("Mystery")||parts[3].equals("Romance")||parts[3].equals("Sci-fi")||parts[3].equals("Thriller")||parts[3].equals("Western"))) {
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
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                if (parts.length==10){
                    if(parts[4].equals("")){
                        try{
                            throw new BadRatingException("Missing rating");
                        }
                        catch (BadRatingException e){
                            errorFound = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                //invalid rating
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
                else if (parts.length==10){
                    if(!(parts[4].equals("PG")||parts[4].equals("Unrated")||parts[4].equals("G")||parts[4].equals("R")||parts[4].equals("PG-13")||parts[4].equals("NC-17"))){
                        try{
                            throw new BadRatingException("Missing rating");
                        }
                        catch (BadRatingException e){
                            errorFound = true;
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing rating (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
                            System.out.println(e.getMessage());
                            badMovie.println("Error missing score (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                        }
                    }
                }
                //invalid score
                if (parts.length > 10 && presenceOfComa(line)){
                    double score = -1;
                    try{
                        try{
                        score = Double.parseDouble(parts[6]);
                        }
                        catch(NumberFormatException e){
                            throw new BadScoreException("Invalid score");
                        }
                    if(score<0||score>10){
                        throw new BadScoreException("Invalid score");
                    }
                    }catch (BadScoreException f){
                        errorFound = true;
                        System.out.println(f.getMessage());
                        badMovie.println("Error missing score (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
                    }
                }
                else if (parts.length==10){
                    double score = -1;
                    try{
                        try{
                        score = Double.parseDouble(parts[5]);
                        }
                        catch(NumberFormatException e){
                            throw new BadScoreException("Invalid score");
                        }
                    if(score<0||score>10){
                        throw new BadScoreException("Invalid score");
                    }
                    }catch (BadScoreException f){
                        errorFound = true;
                        System.out.println(f.getMessage());
                        badMovie.println("Error missing score (semantic error): \n" + line + "\nFound in Movie199"+fileDone+".csv, line " + (x+1));
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
        
        // switching files
        if (!input.hasNextLine() && fileDone==0){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        } 
        else if (!input.hasNextLine() && fileDone==1){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==2){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==3){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==4){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==5){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==6){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==7){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
        else if (!input.hasNextLine() && fileDone==8){
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/"+selectFile.nextLine()));
            fileDone ++;
            x=0;
        }
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
        PrintWriter outputMusical =null;
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
        PrintWriter outputThriller =null;


        try{
            input = new Scanner(new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/"+file));
            currentFile = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/"+input.nextLine()));

            outputMusical = new PrintWriter(new FileOutputStream("musical.ser"));
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
            outputThriller = new PrintWriter(new FileOutputStream("thriller.ser"));

            Movie currentMovie = new Movie();
            Movie[] currentMovieArr = new Movie[200];
            
            

            int fileDone = 0;

            for (int i = 0; i<currentMovieArr.length; i++){
                
                if (currentFile.hasNextLine()){
                    for (int j = 0; currentFile.hasNextLine(); j++){
                        String line = currentFile.nextLine();
                        String[] parts = line.split(",");
                       
                        if (parts.length == 10){
                            currentMovie = new Movie(Integer.parseInt(parts[0]),parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], Double.parseDouble(parts[5]), parts[6], parts[7], parts[8], parts[9]) ;
                        }
                        else{
                            currentMovie = new Movie(Integer.parseInt(parts[0]),parts[1]+", "+parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], Double.parseDouble(parts[6]), parts[7], parts[8], parts[9], parts[10]) ;
                        }
                        
                        currentMovieArr[j] = currentMovie;
                    }
                        //placing the array created in its respective file
                    switch(fileDone){
                        case 0: outputMusical.println(currentMovieArr);
                        break;
                        case 1: outputComedy.println(currentMovieArr);
                        break;
                        case 2: outputAnimation.println(currentMovieArr);
                        break;
                        case 3: outputAdventure.println(currentMovieArr);
                        break;
                        case 4: outputDrama.println(currentMovieArr);
                        break;
                        case 5: outputCrime.println(currentMovieArr);
                        break;
                        case 6: outputBiography.println(currentMovieArr);
                        break;
                        case 7: outputHorror.println(currentMovieArr);
                        break;
                        case 8: outputAction.println(currentMovieArr);
                        break;
                        case 9: outputDocumentary.println(currentMovieArr);
                        break;
                        case 10: outputFantasy.println(currentMovieArr);
                        break;
                        case 11: outputMystery.println(currentMovieArr);
                        break;
                        case 12: outputSciFi.println(currentMovieArr);
                        break;
                        case 13: outputFamily.println(currentMovieArr);
                        break;
                        case 14: outputWestern.println(currentMovieArr);
                        break;
                        case 15: outputRomance.println(currentMovieArr);
                        break;
                        case 16: outputThriller.println(currentMovieArr);
                        break; 
                    }
                }
                //switching current file
                else if (!currentFile.hasNextLine() && input.hasNextLine()){
                    currentFile = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/"+input.nextLine()));
                    fileDone++;
                    i=0;
                }

            }
            
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
            System.exit(0);
        }
        input.close();
        currentFile.close();
        outputAction.close();
        outputAdventure.close();
        outputAnimation.close();
        outputBiography.close();
        outputComedy.close();
        outputCrime.close();
        outputDocumentary.close();
        outputFamily.close();
        outputDrama.close();
        outputFantasy.close();
        outputHorror.close();
        outputMusical.close();
        outputMystery.close();
        outputRomance.close();
        outputSciFi.close();
        outputThriller.close();
        outputWestern.close();

        return "part3_manifest";
    }

    public static void main(String[] args){
        createFirstFile();

        // part 1's manifest file
        String part1_manifest = "part1_manifest.txt";
        // part 2's manifest file
        String part2_manifest = do_part1(part1_manifest);
        // part 3's manifest file
        String part3_manifest = do_part2(part2_manifest);
    }

}
