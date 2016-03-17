import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * A final class cannot be extended. Why would another class in this project inherit from Commands?
 * Also, final methods cannot be Overriden. And final vars cannot be reassigned to another value.
 */
final class Commands {

    private final static String sep = "\\";

    /**
     * Create a simple and empty .txt file.
     *
     * @param filename The name of the file. ex: "drnaz"
     * @param path     La ruta a donde se creara del archivo. Ej: C:\Users\Jorge\Documents\Java
     */
    static void create(String filename, String path) {
        File f = getFileExt(filename).equals("txt") ?
                new File(path + sep + filename) :              //If file ends with .txt, don't add it.
                new File(path + sep + filename + ".txt");     //If file doesn't, add .txt
        try {
            System.out.println(f.createNewFile() ? //Ok since createNewFile returns true/false, if it returns true:
                    Messages.CREATE_SUCCESS :  //It means file ahs been created. Show "success" message.
                    Messages.FILE_EXISTS);       //If file does not exist, returns false. Show proper message.
        } catch (Exception e) {
            //Show user there has been an error creating the file.
            System.out.println(Messages.ERROR_CREATING + " " + e.getClass().getSimpleName());
        }
    }

    /**
     * Edit any .txt file. Checks if said file is actually a .txt file.
     *
     * @param file Path to file to edit.
     */
    static void edit(String file) {
        if (getFileExt(file).equals("txt")) {
            /* Try-with-resources statement. Checks if file exists. */
            try (PrintWriter out = new PrintWriter(file)) {
                System.out.println(Messages.EDIT_EDITING);
                StringBuilder sb = new StringBuilder();
                Scanner scan = new Scanner(System.in);
                String txt = scan.nextLine();
                while (!txt.equals(">exit")) {
                    sb.append(txt).append("\r\n");
                    txt = scan.nextLine();
                }
//                System.out.println(sb.toString());
                out.println(sb.toString());
                out.close();
                System.out.println(Messages.EDIT_SUCCESS);
            } catch (FileNotFoundException e) {
                System.out.println(Messages.ERROR_NOTFOUND);
            }
        } else {
            System.out.println(Messages.ERROR_NOTATEXTFILE);
        }
    }

    /**
     * Deletes a file. Confirms deletion.
     *
     * @param filename Full path to file to be deleted.
     */
    static void delete(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            System.out.println(Messages.DELETE_CONFIRM);
            switch (new Scanner(System.in).nextLine().toUpperCase()) {
                case "Y":
                    System.out.println(f.delete() ?
                            Messages.DELETE_SUCCESS :
                            Messages.DELETE_NOSUCCESS);
                    break;
                case "N":
                    System.out.println(Messages.DELETE_NOSUCCESS);
                    break;
                default:
                    System.out.println(Messages.ERROR_INVALID);
            }
        } else {
            System.out.println(Messages.ERROR_NOTFOUND);
        }
    }

    /**
     * Copy file. Creates an EXACT copy of a file, asking user how is said copy going to be named. If user does not
     * input a prefix, it will default to "Evil Copy of" file
     *
     * @param file Name of file to be copied.
     * @param orig Path to file to be copied.
     * @param dest Path to said copy of file
     */
    static void copy(String file, String orig, String dest) {
        File f = new File(orig + sep + file);
        if (f.exists()) {
            System.out.println(Messages.COPY_PREFIX);
            String prefix = new Scanner(System.in).nextLine();
            prefix = !prefix.equals("") ? prefix : "Evil Copy of ";
            System.out.println(dest + sep + prefix + file);
            try {
                Files.copy(f.toPath(), Paths.get(dest + sep + prefix + file));
                System.out.println(Messages.COPY_SUCCESS);
            } catch (FileAlreadyExistsException e) {
                System.out.println(Messages.FILE_REPLACE);
                switch (new Scanner(System.in).nextLine().toUpperCase()) {
                    case "Y":
                        try {
                            Files.copy(f.toPath(), Paths.get(dest + sep + prefix + file), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println(Messages.COPY_SUCCESS);
                        } catch (IOException e1) {
                            System.out.println("ERROR. " + e.getClass().getSimpleName());
                        }
                        break;
                    case "N":
                        System.out.println(Messages.COPY_NOTCOPIED);
                        break;
                    default:
                        System.out.println(Messages.ERROR_INVALID);
                        break;
                }
            } catch (Exception e) {
                System.out.println("ERROR. " + e.getClass().getSimpleName());
            }
        } else {
            System.out.println(Messages.ERROR_NOTFOUND);
        }
    }

    /**
     * Moves a file from one folder to another. Asks user if he/she wants to replace said file in case it already
     * exists.
     *
     * @param file File to be moved.
     * @param src  Source of file.
     * @param dest Destination of file.
     */
    static void move(String file, String src, String dest) {
        File f = new File(src + sep + file);
        File d = new File(dest + sep + file);
        if (f.exists() && Paths.get(src).toFile().isDirectory() && Paths.get(dest).toFile().isDirectory()) {
            /* Must check first if both orig and dest are directories,
             *  in case some idiot decides to move C:/letter.txt to C:/virus.exe */
            try {
                Files.move(f.toPath(), d.toPath());
                System.out.println(Messages.MOVE_SUCESS);
            } catch (FileAlreadyExistsException e) {
                System.out.println(Messages.FILE_REPLACE);
                switch (new Scanner(System.in).nextLine().toUpperCase()) {
                    case "Y":
                        try {
                            Files.move(f.toPath(), d.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println(Messages.MOVE_SUCESS);
                        } catch (IOException e1) {
                            System.out.println("ERROR. " + e.getClass().getSimpleName());
                        }
                        break;
                    case "N":
                        System.out.println(Messages.MOVE_NOSUCCESS);
                        break;
                    default:
                        System.out.println(Messages.ERROR_INVALID);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!f.exists()) {
            System.out.println(Messages.ERROR_NOTFOUND);
        } else if (!Paths.get(src).toFile().isDirectory() || Paths.get(dest).toFile().isDirectory()) {
            System.out.println(Messages.ERROR_NOTADIRECTORY);
        }
    }

    static String getFileExt(String filename) {
        int i = filename.lastIndexOf('.');
        return (i >= 0) ? filename.substring(i + 1) : filename;
    }

}