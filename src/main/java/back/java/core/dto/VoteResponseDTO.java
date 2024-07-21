package back.java.core.dto;

import java.util.List;

public class VoteResponseDTO {
    private long id;
    private List<String> responses;
    private VoteDTO vote;
    private UserDTO user;

    public VoteResponseDTO() {
    }

    public VoteResponseDTO(long id, List<String> responses, VoteDTO vote, UserDTO user) {
        this.id = id;
        this.responses = responses;
        this.vote = vote;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public VoteDTO getVote() {
        return vote;
    }

    public void setVote(VoteDTO vote) {
        this.vote = vote;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
