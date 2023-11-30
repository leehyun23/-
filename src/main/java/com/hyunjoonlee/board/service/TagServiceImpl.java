package com.hyunjoonlee.board.service;

import com.hyunjoonlee.board.dto.TagDto;
import com.hyunjoonlee.board.entity.BoardDef;
import com.hyunjoonlee.board.entity.Tag;
import com.hyunjoonlee.board.exception.TagNotFoundException;
import com.hyunjoonlee.board.repository.BoardDefRepository;
import com.hyunjoonlee.board.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.tokens.TagToken;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Tag ServiceImpl
 */
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {
    private final ModelMapper modelMapper;
    private final TagRepository tagRepository;
    private final BoardDefRepository boardDefRepository;

    @Override
    public int register(TagDto tagDto) {
        BoardDef boardDef = boardDefRepository.findById(tagDto.getBoardCd())
                .orElseThrow(()-> new NoSuchElementException());
        Tag tag = modelMapper.map(tagDto, Tag.class);
        tag.setBoardDef(boardDef);
        tag = tagRepository.save(tag);
        int tagNo = tag.getTagNo();
        return tagNo;
    }

    @Override
    public TagDto readOne(int tagNo) {

        Optional<Tag> result = tagRepository.findById(tagNo);
        Tag tag = result.orElseThrow(() -> new TagNotFoundException(tagNo));
        TagDto tagDto = modelMapper.map(tag, TagDto.class);

        return tagDto;
    }

    @Override
    public int modify(TagDto tagDto) {
        Optional<Tag> result = tagRepository.findById(tagDto.getTagNo());
        Tag tag = result.orElseThrow(() -> new TagNotFoundException(tagDto.getTagNo()));
        tag.change(tagDto.getTag());

        return tagRepository.save(tag).getTagNo();
    }

    @Override
    public int remove(int tagNo) {
        tagRepository.deleteById(tagNo);
        return tagNo;
    }

    @Override
    public List<Tag> list() {
        return tagRepository.findAll();
    }
}
