package ru.kinopoisk.server.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import ru.kinopoisk.server.persistence.dto.ArticleDto;
import ru.kinopoisk.server.persistence.dto.GameDto;
import ru.kinopoisk.server.persistence.interfaces.Parser;
import ru.kinopoisk.server.utils.Constraints;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IgromaniaGamesParser implements Parser {

    //List of games
    private List<GameDto> allGamesDto = new LinkedList<>();

    private int pageNumber;

    public IgromaniaGamesParser(int pageNumber) {
        this.pageNumber = pageNumber;
    }



    //Parse games id's from page with number `pageNumber`
    public void getGamesInfoFromPageWithNumber(){
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
            int gameId = Integer.parseInt(game.attr("data-id"));
            GameDto newGame = getGameInfo(gameId);
          //  System.out.println(newGame);
        }

    }

    //Parse all info from page about game with id `gameId`
    public GameDto getGameInfo(int gameId){
        boolean isParsed = false;
        Document doc=null;
        while(!isParsed){
            try {
                doc = Jsoup.parse(new URL(String.format(Constraints.templateForGame,gameId)), 10000);
                isParsed=true;
            }catch (Exception ex){
                ex.getStackTrace();
            }
        }

        String link = String.format(Constraints.templateForGame,gameId);

        Element gameContent = doc.selectFirst(String.format(Constraints.templateForDiv,"game-content"));

      Element metacritic = gameContent.selectFirst(String.format(Constraints.templateForDiv,"metacritic"));

      int metacriticRating = Integer.parseInt(metacritic.selectFirst(String.format(Constraints.templateForSpan,"value")).text());

        Element description = gameContent.selectFirst(String.format(Constraints.templateForDiv,"description"));

        String fullDescription = Jsoup.clean(description.toString(), Whitelist.none()).replaceAll("&nbsp;"," ");



        String originalName = gameContent.selectFirst(String.format(Constraints.templateForH1,"name")).text();

        Element gameData = gameContent.selectFirst(String.format(Constraints.templateForDiv,"game-data"));

//        for(Element divNoClassElem : gameData.select("div:not([class])")){
//           for(Element divTitleElem : divNoClassElem.select(String.format(Constraints.templateForDiv,"title"))){
//
//               if(divTitleElem.text().contains("Платформы:")){
//                   System.out.println(divTitleElem.nextElementSibling());
//               }
//               if(divTitleElem.text().contains("Дата выхода:")){
//                   System.out.println(divTitleElem.nextElementSibling());
//               }
//               if(divTitleElem.text().contains("Разработчики:")){
//                   System.out.println(divTitleElem.nextElementSibling());
//               }
//               if(divTitleElem.text().contains("Серия игр:")){
//                   System.out.println(divTitleElem.nextElementSibling());
//               }
//
//
//
//
//           }
//
//        }

        //System.out.println(originalName);
      //  System.out.println(gameData);


        return new GameDto();

    }


    public List<GameDto> getAllGamesDto() {
        return allGamesDto;
    }

    public static void main(String[] args) throws IOException, ParseException {
        IgromaniaGamesParser gamesParser = new IgromaniaGamesParser(1);
        gamesParser.getGameInfo(471);


    }
}

