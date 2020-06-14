package om.example.android.androidproject.model;

public class Poem {
    private String text;
    private String author;


    public Poem(String text, String author) {
        this.text = text;
        this.author = author;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
