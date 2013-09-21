package org.jbulletin.dao;

import org.jbulletin.model.Post;

public interface PostDao {

    public void savePost(Post post);

    public Post getPost(int id);

    Post mostRecentPostBySubSection(int subSectionId);

}
