public abstract class AbstractItem implements Item{
    protected String name;
    protected Directory parentDir;

    public AbstractItem(String name, Directory parentDir) {
        this.name = name;
        this.parentDir = parentDir;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Directory getParent() {
        return parentDir;
    }
}
