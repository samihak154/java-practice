import java.util.List;

public class PlayMediaCommand {
    private final MediaService mediaService;

    public PlayMediaCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void execute() {
        List<Media> allMediaList = mediaService.getAllMedia();
        Media media = null;

        if (allMediaList.isEmpty()) {
            TerminalUtils.displayMessage("Error: Library is empty.\n");
            return;
        }

        TerminalUtils.displayMessage("Select media to play:");
        for (int i = 0; i < allMediaList.size(); i++) {
            media = allMediaList.get(i);
            TerminalUtils.displayMessage((i + 1) + ". " + media.getName());
        }

        int choice = TerminalUtils.getInt("\nChoose media: ") - 1;
        if (choice >= allMediaList.size() || choice < 0) {
            TerminalUtils.displayMessage("Error: Invalid selection.\n");
            return;
        }

        media = allMediaList.get(choice);
        media.play();
    }
}
