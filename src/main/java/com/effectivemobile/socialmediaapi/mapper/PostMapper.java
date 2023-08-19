package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.PostDto;
import com.effectivemobile.socialmediaapi.model.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {

    PostDto map(Post post);

    Post map(PostDto postDto);
}
