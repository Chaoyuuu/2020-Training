import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PathTool {
    private final Directory root;

    public PathTool(Directory root) {
        this.root = root;
    }

    public String createPath(Item item) {
        List<String> outputPath = new ArrayList<>();
        outputPath.add(item.getName());

        Directory parentDir = item.getParent();
        while (parentDir != null){
            outputPath.add(parentDir.getName());
            parentDir = parentDir.getParent();
        }

        Collections.reverse(outputPath);
        return String.join("\\", outputPath);
    }

    public String splitItemName(String inputPath) {
        String[] paths = splitPath(inputPath);
        return paths[paths.length - 1];
    }

    //都不走最後一個，最後一個當作Item name
    public Directory traversePath(String inputPath, Directory currentDir) {
        String[] paths = splitPath(inputPath);
        paths = Arrays.copyOfRange(paths, 0, paths.length - 1);
        Directory directory = currentDir;

        try {
            for (String path : paths) {
                Item item = analyzeToken(path, directory);
                if (!(item instanceof Directory)) {
                    throw new PathHasNotExistedException(inputPath);
                }
                directory = (Directory) item;
            }
        } catch (NullItemException e) {
            throw new PathHasNotExistedException(inputPath);
        }
        return directory;
    }

    private String[] splitPath(String inputPath) {
        //absolute path, start with "\", splitPaths[0] == ""
        return inputPath.split("\\\\");
    }

    private Item analyzeToken(String token, Directory currentDir) {
        Item item = null;
        switch (token) {
            case "..":  //find parent
                item = currentDir.getParent();
                break;
            case "":    //form root
                item = root;
                break;
            case ".":   //from this dir
                item = currentDir;
                break;
            default:    //else, file/dir Name
                item = currentDir.findChildByName(token);
                break;
        }
        return item;
    }
}
