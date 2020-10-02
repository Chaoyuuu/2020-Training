public class PathHasExistedException extends RuntimeException {
    public PathHasExistedException(String path) {
        super(String.format("Cannot create %s, %s has existed", path, path));
    }
}
