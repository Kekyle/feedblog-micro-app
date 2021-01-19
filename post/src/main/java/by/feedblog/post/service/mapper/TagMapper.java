package by.feedblog.post.service.mapper;

import by.feedblog.post.domain.Tag;
import by.feedblog.post.service.dto.TagDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {

    public Tag dtoToDomain(TagDto tagDto){
        return getTag(tagDto);
    }

    public TagDto domainToDto(Tag tag){
        return getTagDto(tag);
    }

    public List<Tag> dtoListToDomainList(List<TagDto> tagDtoList){
        List<Tag> tags = new ArrayList<>();
        for (TagDto tagDto : tagDtoList) {
            Tag tag = getTag(tagDto);
            tags.add(tag);
        }
        return tags;
    }

    public List<TagDto> domainListToDroList(List<Tag> tags){
        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag tag : tags) {
            TagDto tagDto = getTagDto(tag);
            tagDtoList.add(tagDto);
        }
        return tagDtoList;
    }

    private Tag getTag(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        return tag;
    }

    private TagDto getTagDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }
}
