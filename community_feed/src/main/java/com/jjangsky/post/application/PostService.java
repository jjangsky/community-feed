package com.jjangsky.post.application;

import com.jjangsky.post.application.dto.CreatePostRequestDto;
import com.jjangsky.post.application.dto.LikeRequestDto;
import com.jjangsky.post.application.dto.UpdatePostRequestDto;
import com.jjangsky.post.application.interfaces.LikeRepository;
import com.jjangsky.post.application.interfaces.PostRepository;
import com.jjangsky.post.domain.Post;
import com.jjangsky.user.application.UserService;
import com.jjangsky.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository,
                       LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = new Post(null, author, dto.content());
        return postRepository.publish(post);
    }

    public Post updatePost(UpdatePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        post.updateContent(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }
}
