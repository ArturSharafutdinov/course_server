package ru.kinopoisk.server.models;


import ru.kinopoisk.server.entities.LongIdEntity;

public class SectionDto extends LongIdEntityDto {
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
