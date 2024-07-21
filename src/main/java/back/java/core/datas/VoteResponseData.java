package back.java.core.datas;

import java.util.List;

public class VoteResponseData {
    private long id;
    private List<String> responses;
    private VoteData vote;
    private UserData user;

    public VoteResponseData() {
    }

    public VoteResponseData(long id, List<String> responses, VoteData vote, UserData user) {
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

    public VoteData getVote() {
        return vote;
    }

    public void setVote(VoteData vote) {
        this.vote = vote;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
