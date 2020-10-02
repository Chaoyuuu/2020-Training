public class FileItem extends AbstractItem {
    private String content;

    public FileItem(String name, Directory parentDir, String content) {
        super(name, parentDir);
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
