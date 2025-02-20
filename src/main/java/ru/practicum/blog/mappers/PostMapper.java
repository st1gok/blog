package ru.practicum.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;
import ru.practicum.blog.dto.BASE64DecodedMultipartFile;
import ru.practicum.blog.dto.PostDto;
import ru.practicum.blog.models.Post;

import java.io.IOException;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface PostMapper extends EntityMapper<PostDto, Post> {

    @Mapping(target = "img", qualifiedByName = "convertImgToBytes", source = "img")
    Post toEntity(PostDto postDto);

    @Mapping(target = "img", qualifiedByName = "convertBytesToImg", source = "img")
    PostDto toDto(Post post);

    @Named("convertImgToBytes")
    default byte[] convertImgToBytes(MultipartFile img) throws IOException {
        if (img != null) {
            return img.getBytes();
        }
        return null;
    }


    @Named("convertBytesToImg")
    default MultipartFile convertBytesToImg(byte[] img) throws IOException {
        return new BASE64DecodedMultipartFile(img);
    }
}
