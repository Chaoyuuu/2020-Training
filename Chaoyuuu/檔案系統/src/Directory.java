import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Directory extends AbstractItem {
    private final List<Item> childItems = new ArrayList<>();

    public Directory(String name, Directory parentDir) {
        super(name, parentDir);
    }

    public Item findChildByName(String name) {
        return childItems.stream()
                .filter(i -> name.equals(i.getName()))
                .findAny()
                .orElseThrow(() -> new NullItemException(name + "not exist"));
    }

    public void add(Item item) {
        childItems.add(item);
    }

    public void delete(Item item) {
        childItems.remove(item);
    }

    //search !!!! return item contains with keyword
    public List<Item> search(String keyword) {

        List<Item> findItems = new ArrayList<>();
        //if item == directory, keep searching
        List<Directory> directories = getDirectoryChildren();
        for (Directory directory : directories) {
            findItems.addAll(directory.search(keyword));
        }

        List<Item> itemsContainWithKeyword = getItemsContainWithKeyword(keyword);
        findItems.addAll(itemsContainWithKeyword);

        return findItems;
    }

    private List<Directory> getDirectoryChildren() {
        return childItems.stream()
                .filter(item -> item instanceof Directory)
                .map(item -> (Directory) item)
                .collect(Collectors.toList());
    }

    private List<Item> getItemsContainWithKeyword(String keyword) {
        return childItems.stream()
                .filter(item -> item.getName().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return childItems.stream()
                .map(Item::getName)
                .collect(Collectors.joining("\n"));
    }
}
