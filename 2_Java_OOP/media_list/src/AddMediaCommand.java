public class AddMediaCommand {
    private final MediaService mediaService;

    public AddMediaCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void execute() {
        TerminalUtils.displayMessage("Select media type:\n" +
                                    "1. Video\n" +
                                    "2. Audio\n" +
                                    "3. Image\n" +
                                    "4. Book\n");

        String mediaChoice;

        while (true) {
            mediaChoice = TerminalUtils.getString("Choose type (1-4): ");
            if (mediaChoice.matches("[1-4]")) {
                break;
            }
            TerminalUtils.displayMessage("Error: Invalid media choice.");
        }

        while (true) {
            String name = TerminalUtils.getString("Enter name: ");
            Media media = null;

            if (mediaService.findMediaByName(name) != null) {
                TerminalUtils.displayMessage(name + " already exists in the library!\n");
                break;
            }

            switch (mediaChoice) {
                case "1":
                    int videoDuration = TerminalUtils.getInt("Enter duration (in minutes): ");
                    String resolution = TerminalUtils.getString("Enter resolution: ");

                    Video video = new Video(name, videoDuration, resolution);
                    media = video;
                    break;
                case "2":
                    int audioDuration = TerminalUtils.getInt("Enter duration (in minutes): ");
                    String artist = TerminalUtils.getString("Enter artist name: ");

                    Audio audio = new Audio(name, audioDuration, artist);
                    media = audio;
                    break;
                case "3":
                    String dimension = TerminalUtils.getString("Enter dimension (e.g. 900 x 1260): ");
                    String fileFormat = TerminalUtils.getString("Enter file format: ");

                    Image image = new Image(name, dimension, fileFormat);
                    media = image;
                    break;
                case "4":
                    String author = TerminalUtils.getString("Enter author name: ");
                    int pageCount = TerminalUtils.getInt("Enter page count: ");

                    Book book = new Book(name, author, pageCount);
                    media = book;
                    break;
                default:
                    TerminalUtils.displayMessage("Error: Invalid media type.");
                    return;
            }
            media.setName(name);
            mediaService.addMedia(media);
            TerminalUtils.displayMessage(media.getClass().getSimpleName() + " added successfully!\n");
            return;
        }
    }
}
