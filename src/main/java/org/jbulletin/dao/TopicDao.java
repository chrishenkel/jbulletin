package org.jbulletin.dao;

import java.util.Collection;

import org.jbulletin.model.Post;
import org.jbulletin.model.Topic;
import org.springframework.stereotype.Repository;

public interface TopicDao {
    public Topic getTopic(int topicId);

    public void saveTopic(Topic topic);

    public Collection<Topic> getTopicsFromSubSection(int subSectionId,
	    int start, int length);

    public Collection<Post> getPostsFromTopic(int topicId, int start, int length);
}
