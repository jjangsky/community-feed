package com.jjangsky.post.application;

import com.jjangsky.post.application.dto.CreatePostRequestDto;
import com.jjangsky.post.application.dto.LikePostRequestDto;
import com.jjangsky.post.application.interfaces.LikeRepository;
import com.jjangsky.post.application.interfaces.PostRepository;
import com.jjangsky.post.domain.Post;
import com.jjangsky.post.domain.content.Content;
import com.jjangsky.post.domain.content.PostContent;
import com.jjangsky.user.application.UserService;
import com.jjangsky.user.domain.User;

public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;


    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }


    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Post CreatePost(CreatePostRequestDto dto){
        User author = userService.getUser(dto.userId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(Long id, CreatePostRequestDto dto){
        Post post = getPost(id);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikePostRequestDto dto){
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        // 좋아요를 누루기 전에 이미 좋아요를 누른 경우를 확인해야 한다.
        if(likeRepository.checkLike(post, user)){
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikePostRequestDto dto){
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        // 좋아요를 누른 경우에만 좋아요를 취소할 수 있다.
        if(!likeRepository.checkLike(post, user)){
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }


}
