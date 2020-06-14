package om.example.android.androidproject.retrofit;

import java.util.List;

import om.example.android.androidproject.model.Poem;

public class PoemResponse {
    private List<String> lines;
    private String author;

    public Poem getPoem() {
        String text = "";
        for (String line : lines) {
            text += line;
        }

        return new Poem(text, author);
    }

}
