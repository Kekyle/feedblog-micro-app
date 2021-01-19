package by.feedblog.post.service;

import by.feedblog.post.data.TagData;
import by.feedblog.post.domain.Tag;
import by.feedblog.post.service.dto.TagDto;
import by.feedblog.post.service.exception.TagIsAlreadyExistException;
import by.feedblog.post.service.exception.TagNotFoundException;
import by.feedblog.post.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagData tagData;

    @Autowired
    private TagMapper tagMapper;

    public Tag save(TagDto tag) {
        if (tagData.existsByName(tag.getName())) {
            throw new TagIsAlreadyExistException("Tag is already exist");
        } else {
            Tag currentTag = tagMapper.dtoToDomain(tag);
            return tagData.save(currentTag);
        }
    }

    public Tag update(TagDto tag) {
        if (tagData.existsById(tag.getId())) {
            Tag currentTag = tagMapper.dtoToDomain(tag);
            return tagData.save(currentTag);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public void deleteById(long id) {
        if (tagData.existsById(id)) {
            tagData.deleteById(id);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public void deleteByName(String name) {
        if (tagData.existsByName(name)) {
            tagData.deleteByName(name);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public List<Tag> findAll() {
        return tagData.findAll();
    }

    public Tag findById(long id) {
        if (tagData.existsById(id)) {
            return tagData.findById(id);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public Tag findByName(String name) {
        if (tagData.existsByName(name)) {
            return tagData.findByName(name);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }
}
