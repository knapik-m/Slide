package me.ccrama.redditslide.test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.dean.jraw.models.Comment;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.VoteDirection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import me.ccrama.redditslide.ActionStates;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ActionStatesTests {

    @InjectMocks
    private ActionStates actionStates = new ActionStates();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPublicContributionUpvoted() {
        ObjectNode upvoteNode = JsonNodeFactory.instance.objectNode();
        upvoteNode.put("name", "upvoteTestAuthor");
        Comment upvote = new Comment(upvoteNode);

        actionStates.setVoteDirection(upvote, VoteDirection.UPVOTE);

        assertThat(actionStates.getVoteDirection(upvote), is(VoteDirection.UPVOTE));
    }

    @Test
    public void testPublicContributionDownvoted() {
        ObjectNode downvoteNode = JsonNodeFactory.instance.objectNode();
        downvoteNode.put("name", "downvoteTestAuthor");
        Comment downvote = new Comment(downvoteNode);

        actionStates.setVoteDirection(downvote, VoteDirection.DOWNVOTE);

        assertThat(actionStates.getVoteDirection(downvote), is(VoteDirection.DOWNVOTE));
    }

    @Test
    public void testPublicContributionUnvoted() {
        ObjectNode unvoteNode = JsonNodeFactory.instance.objectNode();
        unvoteNode.put("name", "unvoteTestAuthor");
        Comment unvote = new Comment(unvoteNode);

        actionStates.setVoteDirection(unvote, VoteDirection.NO_VOTE);

        assertThat(actionStates.getVoteDirection(unvote), is(VoteDirection.NO_VOTE));
    }

    @Test
    public void testCommentWasUnsaved() {
        ObjectNode upvoteNode = JsonNodeFactory.instance.objectNode();
        upvoteNode.put("name", "upvoteTestAuthor");
        Comment upvote = new Comment(upvoteNode);

        actionStates.setSaved(upvote, false);

        assertThat(actionStates.isSaved(upvote), is(false));
    }

    @Test
    public void testCommentWasSaved() {
        ObjectNode upvoteNode = JsonNodeFactory.instance.objectNode();
        upvoteNode.put("name", "upvoteTestAuthor");
        Comment upvote = new Comment(upvoteNode);

        actionStates.setSaved(upvote, true);

        assertThat(actionStates.isSaved(upvote), is(true));
    }

    @Test
    public void testSubmissionUnsaved() {
        ObjectNode upvoteNode = JsonNodeFactory.instance.objectNode();
        upvoteNode.put("author", "upvoteTestAuthor");
        upvoteNode.put("saved", false);
        Submission upvote = new Submission(upvoteNode);

        actionStates.setSaved(upvote, false);

        assertThat(actionStates.isSaved(upvote), is(false));
    }

    @Test
    public void testSubmissionSaved() {
        ObjectNode upvoteNode = JsonNodeFactory.instance.objectNode();
        upvoteNode.put("author", "upvoteTestAuthor");
        upvoteNode.put("saved", false);
        Submission upvote = new Submission(upvoteNode);

        actionStates.setSaved(upvote, true);

        assertThat(actionStates.isSaved(upvote), is(true));
    }
}
