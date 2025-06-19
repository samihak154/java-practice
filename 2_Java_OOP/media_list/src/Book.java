public class Book extends Media {
    private String author;
    private int pageCount;

    public Book (String name, String author, int pageCount) {
        super(name);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void play() {
        System.out.println( "Opening book '" + name + "', using e-reader software.\n");
    }

    @Override
    public String getDescription() {
        return String.format("Description: " + "Book '" + getName()
                + "' - Page Count: " + getPageCount() + " pages, Author: " + getAuthor());
    }

    @Override
    public String toString() {
        return String.format("Book: " + getName());
    }
}
