public class PathHasNotExistedException extends RuntimeException {
    public PathHasNotExistedException(String path) {
        super(String.format("Cannot find %s", path));
    }
}
