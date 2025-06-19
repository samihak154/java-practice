import java.util.ArrayList;

public class MediaService {
    ArrayList<Media> mediaList = new ArrayList<>();
    Media media = null;

    public MediaService() {}

    public void addMedia(Media media) {
        if (media != null) {
            mediaList.add(media);
        } else {
            System.out.println("Error: media can't be empty!");
        }
    }

    public boolean removeMedia(String name) {
        for (int i = 0; i < mediaList.size(); i++) {
            media = mediaList.get(i);
            if (media.getName().equalsIgnoreCase(name)) {
                mediaList.remove(media);
                return true;
            }
        }
        System.out.println("Error: Media was not found!");
        return false;
    }

    public Media findMediaByName(String name) {
        if (name == null) return null;
        for (int i = 0; i < mediaList.size(); i++) {
            Media media = mediaList.get(i);
            if (media.getName().equalsIgnoreCase(name)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList getAllMedia() {
        return new ArrayList<>(mediaList);
    }

    public int getMediaCount() {
        return mediaList.size();
    }

    public boolean isEmpty() {
        return mediaList.isEmpty();
    }
}
