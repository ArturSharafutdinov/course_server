package ru.kinopoisk.server.persistence.dto;


public class SectionDto extends LongIdEntityDto {
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
