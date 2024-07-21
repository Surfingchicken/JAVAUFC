package back.java.core.dto;

import java.util.Date;
import java.util.List;
public class VoteDTO {
    private long id;
    private String title;
    private String mode;
    private String comment;
    private Date createdAt;
    private Date updatedAt;
    private UserDTO createdBy;
    private List<VoteQuestionDTO> questions;
    private List<VoteResponseDTO> responses;

    public VoteDTO() {
    }

    public VoteDTO(long id, String title, String mode, String comment, Date createdAt, Date updatedAt, UserDTO createdBy, List<VoteQuestionDTO> questions, List<VoteResponseDTO> responses) {
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

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public List<VoteQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<VoteQuestionDTO> questions) {
        this.questions = questions;
    }

    public List<VoteResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<VoteResponseDTO> responses) {
        this.responses = responses;
    }
}
