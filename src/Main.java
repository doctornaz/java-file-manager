import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String[] comm = {""};

        //TODO Create 2 modes: cmd-style and fail safe (foolproof)
        while (!comm[0].equals("exit")) {
            System.out.println(Messages.WELCOME_MSG);
            comm = scan.nextLine().split(" ");
            if (comm.length >= 1) switch (comm[0].toLowerCase()) {
                case "help":
                    System.out.println(Messages.HELP_MSG);
                    break;
                case "create":
                    if (comm.length != 3) {
                        System.out.println(Messages.ERROR_INVALIDPARAMETERS);
                        break;
                    }
                    Commands.create(comm[1], comm[2]);
                    break;
                case "delete":
                    if (comm.length != 2) {
                        System.out.println(Messages.ERROR_INVALIDPARAMETERS);
                        break;
                    }
                    Commands.delete(comm[1]);
                    break;
                case "edit":
                    if (comm.length != 2) {
                        System.out.println(Messages.ERROR_INVALIDPARAMETERS);
                        break;
                    }
                    Commands.edit(comm[1]);
                    break;
                case "copy":
                    if (comm.length != 4) {
                        System.out.println(Messages.ERROR_INVALIDPARAMETERS);
                        break;
                    }
                    Commands.copy(comm[1], comm[2], comm[3]);
                    break;
                case "move":
                    if (comm.length != 4) {
                        System.out.println(Messages.ERROR_INVALIDPARAMETERS);
                        break;
                    }
                    Commands.move(comm[1], comm[2], comm[3]);
                    break;
                case "exit":
                    continue;
                default:
                    System.out.println(Messages.ERROR_INVALID);
            }//Switch
        }
        System.out.println(Messages.EXIT_MSG);
    }
}