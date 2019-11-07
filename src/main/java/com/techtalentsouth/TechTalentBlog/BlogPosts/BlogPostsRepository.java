package com.techtalentsouth.TechTalentBlog.BlogPosts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostsRepository extends CrudRepository<BlogPost, Long>{

}
