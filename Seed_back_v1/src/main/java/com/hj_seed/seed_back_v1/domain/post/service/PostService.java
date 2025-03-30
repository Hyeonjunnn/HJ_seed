package com.hj_seed.seed_back_v1.domain.post.service;

import com.hj_seed.seed_back_v1.domain.post.data.entity.Post;

import java.util.List;

public interface PostService {


    int getTotalCount();

    List<Post> getPosts(int page, int numOfRows);
}
