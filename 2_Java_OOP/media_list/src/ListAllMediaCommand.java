import java.util.List;

public class ListAllMediaCommand {
    private final MediaService mediaService;

    public ListAllMediaCommand(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void execute() {
        List<Media> allMediaList = mediaService.getAllMedia();

        TerminalUtils.displayMessage("All Media in Library:");
        TerminalUtils.displayMediaList(allMediaList);
    }
}
