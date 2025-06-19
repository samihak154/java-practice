import java.util.List;

public class RemoveMediaCommand {
    private final MediaService mediaService;

    public RemoveMediaCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void execute() {
        List<Media> allMediaList = mediaService.getAllMedia();
        Media media = null;

        if (allMediaList.isEmpty()) {
            TerminalUtils.displayMessage("Error: Library is empty.\n");
            return;
        }

        TerminalUtils.displayMessage("\nSelect media to remove:");
        TerminalUtils.displayMediaList(allMediaList);

        int choice = TerminalUtils.getInt("Choose media: ") - 1;
        if (choice >= allMediaList.size() || choice < 0) {
            TerminalUtils.displayMessage("Error: Please choose a valid media from the list.\n");
            return;
        }

        media = allMediaList.get(choice);
        String name = media.getName();
        mediaService.removeMedia(name);
        TerminalUtils.displayMessage(media.getClass().getSimpleName() + " removed successfully!\n");
    }
}
