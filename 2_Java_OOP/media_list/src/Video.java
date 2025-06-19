public class Video extends Media {
    private int duration;
    private String resolution;

    public Video (String name, int duration, String resolution) {
        super(name);
        this.duration = duration;
        this.resolution = resolution;
    }

    public int getDuration() {
        return duration;
    }

    public String getResolution() {
        return resolution;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    // abstract method to be overridden by subclasses
    public void play() {
        // "Playing video '[name]' using video player software"
        System.out.println( "Playing video '" + name + "', using video player software.\n");
    }

    @Override
    // abstract method to be overridden by subclasses
    public String getDescription() {
        return String.format("Description: " + "Video '" + getName()
                + "' - Duration: " + getDuration() + " minutes, Resolution: " + getResolution());
    }

    @Override
    public String toString() {
        return String.format("Video: " + getName());
    }
}
