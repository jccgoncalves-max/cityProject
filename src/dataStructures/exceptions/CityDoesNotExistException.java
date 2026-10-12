package dataStructures.exceptions;

public class CityDoesNotExistException extends RuntimeException {
    public CityDoesNotExistException(String message) {
        super(message);
    }
    public CityDoesNotExistException() {
        super();
    }
}
