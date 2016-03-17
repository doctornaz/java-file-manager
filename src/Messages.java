/**
 * Created by Jorge Sarmiento on 3/9/2016.
 * Example of a String enum.
 */
public enum Messages {
    FILE_EXISTS("File Already Exists"),
    FILE_EDITING("Editing File. Type '>exit' to finish."),
    FILE_REPLACE("File Already Exists. Would you like to replace it? Y/N"),
    CREATE_SUCCESS("File Created Successfully."),
    EDIT_SUCCESS("File Edited Successfully."),
    ERROR_CREATING("File couldn't be created."),
    ERROR_NOTFOUND("File not found."),
    ERROR_NOTATEXTFILE("Not a text file."),
    ERROR_INVALID("Invalid Option."),
    ERROR_NOTADIRECTORY("Either source or destination are not folders"),
    DELETE_CONFIRM("Are you sure you want to delete this file? Y/N: "),
    DELETE_SUCCESS("File Deleted Successfully."),
    DELETE_NOSUCCESS("File not Deleted."),
    COPY_PREFIX("Please write copied file prefix. Leave blank for 'Evil Copy'"),
    COPY_SUCCESS("File copied successfully."),
    COPY_NOTCOPIED("File not copied."),
    MOVE_SUCESS("File Moved successfully."),
    MOVE_NOSUCCESS("File not Moved.");

    private final String msg;

    Messages(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
