package back.java.core.datas;

import java.util.List;

public class VoteQuestionData {
    private long id;
    private String questionText;
    private VoteData vote;
    private List<VoteOptionData> options;

    public VoteQuestionData() {
    }

    public VoteQuestionData(long id, String questionText, VoteData vote, List<VoteOptionData> options) {
        this.id = id;
        this.questionText = questionText;
        this.vote = vote;
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public VoteData getVote() {
        return vote;
    }

    public void setVote(VoteData vote) {
        this.vote = vote;
    }

    public List<VoteOptionData> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOptionData> options) {
        this.options = options;
    }

}
