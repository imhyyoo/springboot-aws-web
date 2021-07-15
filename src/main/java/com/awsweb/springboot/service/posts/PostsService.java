package com.awsweb.springboot.service.posts;

import com.awsweb.springboot.domain.posts.Posts;
import com.awsweb.springboot.domain.posts.PostsRepository;
import com.awsweb.springboot.web.dto.PostResponseDto;
import com.awsweb.springboot.web.dto.PostUpdateRequestDto;
import com.awsweb.springboot.web.dto.PostsSaveRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(
                        "해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        // entity에 해당하는 PostResponseDto를 보내준다.
        return new PostResponseDto(entity);
    }
}
