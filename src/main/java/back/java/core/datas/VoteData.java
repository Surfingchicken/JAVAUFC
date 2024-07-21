package back.java.core.datas;

import java.util.Date;
import java.util.List;
public class VoteData {
    private long id;
    private String title;
    private String mode;
    private String comment;
    private Date createdAt;
    private Date updatedAt;
    private UserData createdBy;
    private List<VoteQuestionData> questions;
    private List<VoteResponseData> responses;

    public VoteData() {
    }

    public VoteData(long id, String title, String mode, String comment, Date createdAt, Date updatedAt, UserData createdBy, List<VoteQuestionData> questions, List<VoteResponseData> responses) {
        this.id = id;
        this.title = title;
        this.mode = mode;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.questions = questions;
        this.responses = responses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserData getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserData createdBy) {
        this.createdBy = createdBy;
    }

    public List<VoteQuestionData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<VoteQuestionData> questions) {
        this.questions = questions;
    }

    public List<VoteResponseData> getResponses() {
        return responses;
    }

    public void setResponses(List<VoteResponseData> responses) {
        this.responses = responses;
    }
}
