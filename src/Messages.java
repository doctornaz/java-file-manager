/**
 * Created by Jorge Sarmiento on 3/9/2016.
 * Example of a String enum.
 */
public enum Messages {
    //COMMON MESSAGES
    FILE_EXISTS("File Already Exists"),
    FILE_REPLACE("File Already Exists. Would you like to replace it? Y/N"),
    CREATE_SUCCESS("File Created Successfully."),

    //EDIT MESSAGES
    EDIT_EDITING("Editing File. Type '>exit' to finish."),
    EDIT_SUCCESS("File Edited Successfully."),

    //ERROR MESSAGES
    ERROR_CREATING("File couldn't be created."),
    ERROR_NOTFOUND("File not found."),
    ERROR_NOTATEXTFILE("Not a text file."),
    ERROR_INVALID("Invalid Option."),
    ERROR_INVALIDPARAMETERS("Number of parameters invalid"),
    ERROR_NOTADIRECTORY("Either source or destination are not folders"),

    //DELETE MESSAGES
    DELETE_CONFIRM("Are you sure you want to delete this file? Y/N: "),
    DELETE_SUCCESS("File Deleted Successfully."),
    DELETE_NOSUCCESS("File not Deleted."),

    //COPE MESSAGES
    COPY_PREFIX("Please write copied file prefix. Leave blank for 'Evil Copy'"),
    COPY_SUCCESS("File copied successfully."),
    COPY_NOTCOPIED("File not copied."),

    //MOVE MESSAGES
    MOVE_SUCESS("File Moved successfully."),
    MOVE_NOSUCCESS("File not Moved."),

    //MAIN MENU MESSAGES
    WELCOME_MSG("Input command to be executed. Type 'help' if you need help."),
    HELP_MSG("create\t<filename> <directory> (Forces file to be created as .txt)\n" +
             "delete\t<path to file> (You must confirm if you want to delete the file)\n" +
             "edit\t<path to file> Edits file ONLY IF IT IS A .txt FILE.\n" +
             "copy\t<filename> <source folder> <destination folder>\n\n"),
    EXIT_MSG("Thank you for using this utility, " + System.getProperty("user.name"));

    private final String msg;

    Messages(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
