public class Result {
    private final boolean success;
    private final String message;

    public Result(boolean success, String message) {

        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
