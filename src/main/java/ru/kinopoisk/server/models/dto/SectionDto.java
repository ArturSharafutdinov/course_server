package ru.kinopoisk.server.models.dto;


public class SectionDto extends LongIdEntityDto {
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
