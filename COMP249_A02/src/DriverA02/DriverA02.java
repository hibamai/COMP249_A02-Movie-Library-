package DriverA02;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class DriverA02 {

    

    public static void do_part1() {
        //What's missing: catching bad movies AND crea
        Scanner input = null;
        PrintWriter outputMusical = null;
        PrintWriter outputComedy = null;
        PrintWriter outputAnimation = null;
        PrintWriter outputAdventure = null;
        PrintWriter outputDrama = null;
        PrintWriter outputCrime = null;
        PrintWriter outputBiography = null;
        PrintWriter outputHorror = null;
        PrintWriter outputAction = null;
        PrintWriter outputDocumentary = null;
        PrintWriter outputFantasy = null;
        PrintWriter outputMystery = null;
        PrintWriter outputSciFi = null;
        PrintWriter outputFamily = null;
        PrintWriter outputWestern = null;
        PrintWriter outputRomance = null;
        PrintWriter outputThriller = null;
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
            input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1990.csv"));
            int fileDone = 0; 
            outputMusical = new PrintWriter(new FileOutputStream("musical.csv"));
            System.out.println(outputAction);
        
            outputComedy = new PrintWriter(new FileOutputStream("comedy.csv"));
            outputAnimation = new PrintWriter(new FileOutputStream("animation.csv"));
            outputAdventure = new PrintWriter(new FileOutputStream("adventure.csv"));
            outputDrama = new PrintWriter(new FileOutputStream("drama.csv"));
            outputCrime = new PrintWriter(new FileOutputStream("crime.csv"));
            outputBiography = new PrintWriter(new FileOutputStream("biography.csv"));
            outputHorror = new PrintWriter(new FileOutputStream("horror.csv"));
            outputAction = new PrintWriter(new FileOutputStream("action.csv"));
            outputDocumentary = new PrintWriter(new FileOutputStream("documentary.csv"));
            outputFantasy = new PrintWriter(new FileOutputStream("fantasy.csv"));
            outputMystery = new PrintWriter(new FileOutputStream("mystery.csv"));
            outputSciFi = new PrintWriter(new FileOutputStream("sciFi.csv"));
            outputFamily = new PrintWriter(new FileOutputStream("family.csv"));
            outputWestern = new PrintWriter(new FileOutputStream("western.csv"));
            outputRomance = new PrintWriter(new FileOutputStream("romance.csv"));
            outputThriller = new PrintWriter(new FileOutputStream("thriller.csv"));

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
                            case "Musical":
                            outputMusical.println(line);
                            break;
                            case "Comedy":
                            outputComedy.println(line);
                            break;
                            case "Animation":
                            outputAnimation.println(line);
                            break;
                            case "Adventure":
                            outputAdventure.println(line);
                            break;
                            case "Drama":
                            outputDrama.println(line);
                            break;
                            case "Crime":
                            outputCrime.println(line);
                            break;
                            case "Biography":  outputBiography.println(line);
                            break;
                            case "Horror":  outputHorror.println(line);
                            break;
                            case "Action":  outputAction.println(line);
                            break;
                            case "Documentary":  outputDocumentary.println(line);
                            break;
                            case "Fantasy":  outputFantasy.println(line);
                            break;
                            case "Mystery":  outputMystery.println(line);
                            break;
                            case "Sci-fi":  outputSciFi.println(line);
                            break;
                            case "Family":  outputFamily.println(line);
                            break;
                            case "Western":  outputWestern.println(line);
                            break;
                            case "Romance":  outputRomance.println(line);
                            break;
                            case "Thriller":  outputThriller.println(line);
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
            // switching files
            if (!input.hasNextLine() && fileDone==0){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1991.csv"));
                fileDone ++;
            } 
            else if (!input.hasNextLine() && fileDone==1){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1992.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==2){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1993.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==3){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1994.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==4){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1995.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==5){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1996.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==6){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1997.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==7){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1998.csv"));
                fileDone ++;
            }
            else if (!input.hasNextLine() && fileDone==8){
                input = new Scanner (new FileInputStream("/Users/lammai/Documents/GitHub/COMP249_A02/COMP249_A02/COMP249_A02/src/DriverA02/Movies1999.csv"));
                fileDone ++;
            }
        }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        input.close();
       outputMusical.close();  
       outputComedy.close();
       outputAnimation.close();
       outputAdventure.close();
       outputDrama.close();
       outputCrime.close();
       outputBiography.close();
       outputHorror.close();
       outputAction.close();
       outputDocumentary.close();
       outputFantasy.close();
       outputMystery.close();
       outputSciFi.close();
       outputFamily.close();
       outputWestern.close();
       outputRomance.close();
       outputThriller.close();



        
        

}

    



    public static void main(String[] args){
        do_part1();

        // part 1's manifest file

        // part 2's manifest file 
        
        // part 3's manifest file

    }

}
