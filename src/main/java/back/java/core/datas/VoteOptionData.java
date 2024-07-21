package back.java.core.datas;

public class VoteOptionData {
    private long id;
    private String text;
    private VoteQuestionData question;

    public VoteOptionData() {
    }

    public VoteOptionData(long id, String text, VoteQuestionData question) {
        this.id = id;
        this.text = text;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public VoteQuestionData getQuestion() {
        return question;
    }

    public void setQuestion(VoteQuestionData question) {
        this.question = question;
    }
}
