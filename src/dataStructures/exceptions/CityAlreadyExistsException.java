package dataStructures.exceptions;

public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String message) {
        super(message);
    }
    public CityAlreadyExistsException() {
        super();
    }
}
