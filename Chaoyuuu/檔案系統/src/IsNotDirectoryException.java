public class IsNotDirectoryException extends RuntimeException {
    public IsNotDirectoryException(String path) {
        super(String.format("%s is not a directory ", path));
    }
}
