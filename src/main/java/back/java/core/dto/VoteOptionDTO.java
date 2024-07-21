package back.java.core.dto;

public class VoteOptionDTO {
    private long id;
    private String text;
    private VoteQuestionDTO question;

    public VoteOptionDTO() {
    }

    public VoteOptionDTO(long id, String text, VoteQuestionDTO question) {
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

    public VoteQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(VoteQuestionDTO question) {
        this.question = question;
    }
}
