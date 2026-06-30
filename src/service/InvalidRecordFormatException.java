package service;

/**
 * Demonstrates custom exception handling for malformed text-file rows.
 */
public class InvalidRecordFormatException extends Exception {
    public InvalidRecordFormatException(String message) {
        super(message);
    }
}
