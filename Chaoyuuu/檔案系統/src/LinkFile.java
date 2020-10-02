public class LinkFile extends AbstractItem{
    private String targetPath;

    public LinkFile(String name,  Directory parentDir, String targetPath) {
        super(name, parentDir);
        this.targetPath = targetPath;
    }

    public String getTargetPath() {
        return targetPath;
    }
}
