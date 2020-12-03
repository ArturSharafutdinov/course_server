package ru.kinopoisk.server.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import ru.kinopoisk.server.persistence.domain.Game;
import ru.kinopoisk.server.persistence.dto.ArticleDto;
import ru.kinopoisk.server.persistence.dto.GameDto;
import ru.kinopoisk.server.persistence.interfaces.Parser;
import ru.kinopoisk.server.services.mappers.GameMapper;
import ru.kinopoisk.server.utils.Constraints;
import ru.kinopoisk.server.utils.DateParser;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class IgromaniaGamesParser implements Parser {


    private int pageNumber;

    public IgromaniaGamesParser(int pageNumber) {
        this.pageNumber = pageNumber;
    }



    //Parse games id's from page with number `pageNumber`
    public List<GameDto> getGamesInfoFromPageWithNumber() throws ParseException {
         List<GameDto> allGamesDto = new LinkedList<>();
        boolean isParsed = false;
        Document doc=null;
        while(!isParsed){
            try {
                doc = Jsoup.parse(new URL(String.format(Constraints.templateForGamesLink,pageNumber)), 10000);
                isParsed=true;
            }catch (Exception ex){
                ex.getStackTrace();
            }
        }

        Element gamesBlock = doc.selectFirst(String.format(Constraints.templateForDiv,"games-block"));

        for(Element game : gamesBlock.select(String.format(Constraints.templateForDiv,"game-card"))){
            // Parse metacritic rating
            Element metacritic = game.selectFirst(String.format(Constraints.templateForDiv,"metacritic"));
            int metacriticRating = Integer.parseInt(metacritic.selectFirst(String.format(Constraints.templateForSpan,"value")).text());

            if(metacriticRating<=0){
                continue;
            }

            String imageLink = game.selectFirst(String.format(Constraints.templateForImage,"image")).attr("src");
            int gameId = Integer.parseInt(game.attr("data-id"));
            GameDto newGame = getGameInfo(gameId);
            if(newGame!=null) {
                newGame.setImageLink(imageLink);
                newGame.setMetacriticRating(metacriticRating);
                allGamesDto.add(newGame);
            }
        }
return allGamesDto;
    }

    //Parse all info from page about game with id `gameId`
    public GameDto getGameInfo(int gameId) throws ParseException {

        String link = String.format(Constraints.templateForGame,gameId);


        boolean isParsed = false;
        Document doc=null;
        while(!isParsed){
            try {
                doc = Jsoup.parse(new URL(link), 10000);
                isParsed=true;
            }catch (Exception ex){
                ex.getStackTrace();
            }
        }

        // Get game content block with releaseDate, gamesSeries, developers, platforms, description, originalName
        Element gameContent = doc.selectFirst(String.format(Constraints.templateForDiv,"game-content"));

        List<String> genres = new ArrayList<>();
        Element gameTags = gameContent.selectFirst(String.format(Constraints.templateForDiv,"game-tags"));
        for(Element value :  gameTags.select("a")){
            String tag = value.text();
            if(Constraints.genres.contains(tag)){
                genres.add(tag);
            }
        }

        // Get full description without tags
        Element description = gameContent.selectFirst(String.format(Constraints.templateForDiv,"description"));
        String fullDescription="";
       if(description!=null){
           fullDescription = Jsoup.clean(description.toString(), Whitelist.none()).replaceAll("&nbsp;"," ");
       }

        // Get original name
        String originalName = gameContent.selectFirst(String.format(Constraints.templateForH1,"name")).text();

        // Get block which contains 3 columns with platforms, developers, gamesSeries
        Element gameData = gameContent.selectFirst(String.format(Constraints.templateForDiv,"game-data"));

        List<String> platforms = new ArrayList<>();
        List<String> developers = new ArrayList<>();

        String releaseDate="";
        String gamesSeries="";

        for(Element divNoClassElem : gameData.select("div:not([class])")){
           for(Element divTitleElem : divNoClassElem.select(String.format(Constraints.templateForDiv,"title"))){

               if(divTitleElem.text().contains("Платформы:")){
                for(Element value :  divTitleElem.nextElementSibling().select("a")){
                    platforms.add(value.text());
                }
               }
               if(divTitleElem.text().contains("Дата выхода:")){
                   releaseDate = divTitleElem.nextElementSibling().text();
               }
               if(divTitleElem.text().contains("Разработчики:")){
                   for(Element value :  divTitleElem.nextElementSibling().select("a")){
                       developers.add(value.text());
                   }
               }
               if(divTitleElem.text().contains("Серия игр:")){
                  gamesSeries = divTitleElem.nextElementSibling().text();
               }
           }

        }


        return new GameDto(originalName,
              releaseDate,
                gamesSeries,
                fullDescription,
                "",
                link,
                0,
                developers,
                platforms,
                genres);

    }


    public static void main(String[] args) throws IOException, ParseException {
        IgromaniaGamesParser gamesParser;
        int i=25000;
        long startTime = System.currentTimeMillis();
      try{
          for(;i<25548;i++){
              gamesParser = new IgromaniaGamesParser(i);
              List<GameDto> tempList = gamesParser.getGamesInfoFromPageWithNumber();
              if(tempList.size()!=0){
                  List<Game> gamesList = new ArrayList<>();
                  GameMapper gameMapper = new GameMapper();
                  for(GameDto gameDto : tempList){
                      gamesList.add(gameMapper.mapToEntity(gameDto));
                  }
              }
              if(tempList.size()>0){
                  System.out.println(tempList.size());
              }
          }
      }catch (Exception ex){
          ex.printStackTrace();
      }
        long timeSpent = System.currentTimeMillis() - startTime;

        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");


    }
}

