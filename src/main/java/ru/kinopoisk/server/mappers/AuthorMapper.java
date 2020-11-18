package ru.kinopoisk.server.mappers;

import org.springframework.stereotype.Component;
import ru.kinopoisk.server.entities.Author;
import ru.kinopoisk.server.models.AuthorDto;

@Component
public class AuthorMapper {

    public AuthorDto mapToDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getAuthorName());
        authorDto.setId(author.getId());

        return authorDto;
    }

    public Author mapToEntity(AuthorDto authorDto){
        Author author = new Author();

        author.setAuthorName(authorDto.getName());
        author.setId(authorDto.getId());

        return author;
    }


}
