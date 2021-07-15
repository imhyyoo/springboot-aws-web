package com.awsweb.springboot.service.posts;

import com.awsweb.springboot.domain.posts.Posts;
import com.awsweb.springboot.domain.posts.PostsRepository;
import com.awsweb.springboot.web.dto.PostResponseDto;
import com.awsweb.springboot.web.dto.PostUpdateRequestDto;
import com.awsweb.springboot.web.dto.PostsListResponseDto;
import com.awsweb.springboot.web.dto.PostsSaveRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // 등록, 수정, 삭제 가 없이 조회 기능만하는 메서드에 readOnly 속성을 주어서
    // 조회 속도 향상시킨다
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        postsRepository.delete(posts);
    }
}
