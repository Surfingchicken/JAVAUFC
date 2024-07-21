package back.java.core.dto;

import java.util.List;

public class VoteQuestionDTO {
    private long id;
    private String questionText;
    private VoteDTO vote;
    private List<VoteOptionDTO> options;

    public VoteQuestionDTO() {
    }

    public VoteQuestionDTO(long id, String questionText, VoteDTO vote, List<VoteOptionDTO> options) {
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

    public VoteDTO getVote() {
        return vote;
    }

    public void setVote(VoteDTO vote) {
        this.vote = vote;
    }

    public List<VoteOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOptionDTO> options) {
        this.options = options;
    }

}
