
package DriverA02;
/**
 * @author Lan Thi Duong (40276821), Hiba Maifi (40289223)
 * <br> COMP-249 - Assignment #2
 * <br> Due Date: 27 March, 2024
 */
public class BadYearException extends Exception {
    /**
     * 
     * @param message: Indicates which type of error has been found in the movie record
     */
    public BadYearException(String message) {
        super(message);
    }
}
