package pr.chat;

public class HelloMessage {

    private String text;

    public HelloMessage() {
    }

    public HelloMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setName(String text) {
        this.text = text;
    }
}