public class Audio extends Media {
    int duration;
    String artist;

    public Audio (String name, int duration, String artist) {
        super(name);
        this.duration = duration;
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    // abstract method to be overridden by subclasses
    public void play() {
        //  "Playing audio '[name]' using audio player software"
        System.out.println( "Playing audio '" + name + "', using audio player software.\n");
    }

    @Override
    // abstract method to be overridden by subclasses
    public String getDescription() {
        return String.format("Description: " + "Audio '" + getName()
                + "' - Duration: " + duration + " minutes, Artist: " + getArtist());
    }

    @Override
    public String toString() {
        return String.format("Audio: " + getName());
    }
}
