package ru.kinopoisk.server.mappers;

import org.springframework.stereotype.Component;
import ru.kinopoisk.server.entities.Author;
import ru.kinopoisk.server.entities.Section;
import ru.kinopoisk.server.models.AuthorDto;
import ru.kinopoisk.server.models.SectionDto;

@Component
public class SectionMapper {
    public SectionDto mapToDto(Section section){
       SectionDto sectionDto = new SectionDto();
       sectionDto.setId(section.getId());
       sectionDto.setName(section.getSectionName());

        return sectionDto;
    }

    public Section mapToEntity(SectionDto sectionDto){
     Section section = new Section();
     section.setId(sectionDto.getId());
     section.setSectionName(sectionDto.getName());
     return section;
    }
}
