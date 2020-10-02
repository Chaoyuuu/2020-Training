import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    private Directory currentDir;
    private final Directory root;
    private String errorPath = "errorPath!!!";
    private final PathTool pathTool;

    public FileSystem(Directory root) {
        this.currentDir = root;
        this.root = root;
        pathTool = new PathTool(root);
    }

    private void readFile(String name, Directory directory) {
        if (isItemExisted(name, directory)) {
            Item item = directory.findChildByName(name);
            if (item instanceof LinkFile) {
                String filePath = ((LinkFile) item).getTargetPath();
                item = getFileItem(filePath, directory);
            }
            System.out.println(item);
        } else {
            throw new PathHasNotExistedException(errorPath);
        }
    }

    private boolean isItemExisted(String name, Directory directory) {
        try {
            directory.findChildByName(name);
            return true;
        } catch (NullItemException e) {
            return false;
        }
    }

    private void createFile(String name, String content, Directory directory) {
        if (!isItemExisted(name, directory)) {
            FileItem newFile = new FileItem(name, directory, content);
            directory.add(newFile);
        } else {
            throw new PathHasExistedException(errorPath);
        }
    }

    private void createDir(String name, Directory directory) {
        if (!isItemExisted(name, directory)) {
            Directory newDir = new Directory(name, directory);
            directory.add(newDir);
        } else {
            throw new PathHasExistedException(errorPath);
        }
    }

    private void createLink(String name, String targetPath, Directory directory) {
        if (!isItemExisted(name, directory)) {
            LinkFile newLinkFile = new LinkFile(name, directory, targetPath);
            directory.add(newLinkFile);
        } else {
            throw new PathHasExistedException(errorPath);
        }
    }

    private void deleteItem(String name, Directory directory) {
        if (isItemExisted(name, directory)) {
            Item item = directory.findChildByName(name); ///????????
            directory.delete(item);
        } else {
            throw new PathHasNotExistedException(errorPath);
        }
    }

    //mkdir 走完那個路徑, 建立Dir
    public void makeDirectory(String inputPath) throws PathHasNotExistedException, PathHasExistedException {
        errorPath = inputPath;
        String name = pathTool.splitItemName(inputPath);
        Directory directory = pathTool.traversePath(inputPath, currentDir);
        createDir(name, directory);
    }

    //cd
    //最後一個是不是dir，先在inputPath的最後加上空白的東西，讓traversePat可以讀到全部的inputPath，好爛的設計。
    //但不會印出not dir
    public void changeDir(String inputPath) throws PathHasNotExistedException, IsNotDirectoryException {
        String path = inputPath.concat("\\.");
        currentDir = pathTool.traversePath(path, currentDir);
    }

    //ls
    public void listFiles() {
        System.out.println(currentDir);
    }

    //rm 走完path, 刪除item
    public void remove(String inputPath) throws PathHasNotExistedException {
        errorPath = inputPath;
        String name = pathTool.splitItemName(inputPath);
        Directory directory = pathTool.traversePath(inputPath, currentDir);
        deleteItem(name, directory);
    }

    //touch 走完path, 建立file
    public void touchFile(String inputPath, String content) throws PathHasExistedException, PathHasNotExistedException {
        errorPath = inputPath;
        String name = pathTool.splitItemName(inputPath);
        Directory directory = pathTool.traversePath(inputPath, currentDir);
        createFile(name, content, directory);
    }

    //cat 走完path, 如果file不存在 丟出PathHasNotExisted
    public void catFile(String inputPath) throws PathHasNotExistedException {
        errorPath = inputPath;
        String name = pathTool.splitItemName(inputPath);
        Directory directory = pathTool.traversePath(inputPath, currentDir);
        readFile(name, directory);
    }

    public void searchKeyword(String keyword) {
        Directory directory = currentDir;

        List<Item> findItems = directory.search(keyword);
        findItems.forEach(item -> System.out.println(pathTool.createPath(item)));
    }

    /*
    //search disadvantage!!!! 想要保護childItem??? 不想被全部拿到
    public void searchKeyword(String keyword) {
        Directory directory = currentDir;
        List<Item> childItems = directory.getChildItems();

        //print item contains with keyword
        childItems.stream()
                .filter(item -> item.getName().contains(keyword))
                .forEach(item -> System.out.println(item.getName()));

        //if item == directory, keep searching
        List<Directory> findDirectories = childItems.stream()
                .filter(item -> item instanceof Directory)
                .map(item -> (Directory) item)
                .collect(Collectors.toList());

        for (Directory d : findDirectories) {
            directory = d;
            searchKeyword(keyword);
        }
    }
     */

    //link
    public void link(String linkPath, String targetPath) throws PathHasNotExistedException, PathHasExistedException {
        //走完targetPath，找到那個file，拿到完整的path
        errorPath = targetPath;
        String filePath = getFilePath(targetPath, currentDir);

        //走完linkPath, 建立linkFile
        errorPath = linkPath;
        String linkName = pathTool.splitItemName(linkPath);
        Directory directory = pathTool.traversePath(linkPath, currentDir);
        createLink(linkName, filePath, directory);
    }

    private String getFilePath(String targetPath, Directory directory) {
        FileItem fileItem = getFileItem(targetPath, directory);
        return pathTool.createPath(fileItem);
    }

    private FileItem getFileItem(String targetPath, Directory directory) {
        Directory targetDir = pathTool.traversePath(targetPath, directory);
        String name = pathTool.splitItemName(targetPath);

        if (isItemExisted(name, targetDir)) {
            return (FileItem) targetDir.findChildByName(name);
        } else {
            throw new PathHasNotExistedException(errorPath);
        }
    }

    @Override
    public String toString() {
        String outputPath = pathTool.createPath(currentDir);
        if (outputPath.length() == 0) {
            return "\\";
        } else {
            return outputPath;
        }
    }
}
