package exception;

/**
 * StorageException is a subclass of Exception, which is thrown when there is an error related to containers
 *
 * @author dmai0919/group3
 * @version 1.0
 * @since 2019-12-08
 * @see container
 */
public class StorageException extends Exception {
    public StorageException(String errorMessage) {
        super(errorMessage);
    }

}
