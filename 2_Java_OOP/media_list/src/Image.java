public class Image extends Media {
    String dimensions;
    String fileFormat;

    public Image (String name, String dimensions, String fileFormat) {
        super(name);
        this.dimensions = dimensions;
        this.fileFormat = fileFormat;
    }


    public String getDimensions() {
        return dimensions;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    // abstract method to be overridden by subclasses
    public void play() {
        // "Displaying image '[name]' using image viewer software"
        System.out.println( "Displaying image '" + name + "', using image viewer software.\n");
    }

    @Override
    // abstract method to be overridden by subclasses
    public String getDescription() {
        return String.format("Description: " + "Image '" + getName()
                + "' - Dimensions: " + getDimensions() + " px, File format: " + getFileFormat());
    }

    @Override
    public String toString() {
        return String.format("Image: " + getName());
    }
}
