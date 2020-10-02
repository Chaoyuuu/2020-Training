public class NullItemException extends RuntimeException{
    public NullItemException(String path) {
        super(String.format("Null Item %s", path));
    }
}
