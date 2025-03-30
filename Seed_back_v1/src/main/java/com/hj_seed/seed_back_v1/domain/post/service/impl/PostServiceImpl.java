package com.hj_seed.seed_back_v1.domain.post.service.impl;

import com.hj_seed.seed_back_v1.domain.post.data.entity.Post;
import com.hj_seed.seed_back_v1.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public List<Post> getPosts(int page, int numOfRows) {
        return null;
    }
}
