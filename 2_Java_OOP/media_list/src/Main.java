public class Main {
    public static void main(String[] args) {
        MediaService mediaService = new MediaService();
        TerminalUtils.displayMenu();
        boolean running = true;

        do {
            String choice = TerminalUtils.getMenuChoice();
            switch (choice) {
                case "1":
                    new AddMediaCommand(mediaService).execute();
                    break;
                case "2":
                    new RemoveMediaCommand(mediaService).execute();
                    break;
                case "3":
                    new PlayMediaCommand(mediaService).execute();
                    break;
                case "4":
                    new ListAllMediaCommand(mediaService).execute();
                    break;
                case "5":
                    System.out.println("Goodbye!\n");
                    running = false;
                    break;
            }
        } while (running);
    }
}
