import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Directory root = new Directory("", null);
        FileSystem fileSystem = new FileSystem(root);

        Scanner in = new Scanner(System.in);
        String opCode, path, content, keyword, targetPath, linkPath;

        System.out.print(fileSystem + "> ");
        while (in.hasNext()) {
            opCode = in.next();
            try {
                switch (opCode) {
                    case "cd":
                        path = in.next();
                        fileSystem.changeDir(path);
                        break;
                    case "mkdir":
                        path = in.next();
                        fileSystem.makeDirectory(path);
                        break;
                    case "ls":
                        fileSystem.listFiles();
                        break;
                    case "rm":
                        path = in.next();
                        fileSystem.remove(path);
                        break;
                    case "touch":
                        path = in.next();
                        content = in.next();
                        fileSystem.touchFile(path, content);
                        break;
                    case "cat":
                        path = in.next();
                        fileSystem.catFile(path);
                        break;
                    case "search":
                        keyword = in.next();
                        fileSystem.searchKeyword(keyword);
                        break;
                    case "link":
                        linkPath = in.next();
                        targetPath = in.next();
                        fileSystem.link(linkPath, targetPath);
                        break;
                    default:
                        System.out.println("The command is incorrect");
                        break;
                }
            } catch (PathHasExistedException | PathHasNotExistedException | IsNotDirectoryException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.print(fileSystem + "> ");
            }
        }
    }
}

