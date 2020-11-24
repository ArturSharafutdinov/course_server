package ru.kinopoisk.server.parsers;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.kinopoisk.server.persistence.dto.ArticleDto;
import ru.kinopoisk.server.persistence.dto.GameDto;
import ru.kinopoisk.server.utils.Constraints;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class IgromaniaGamesParser {

    //List of games
    private List<GameDto> allGamesDto = new LinkedList<>();


    //Fill allGamesDto list
    public void getAllGames() throws IOException, ParseException {



    }

    //Parse games id's from page with number `pageNumber`
    public List<GameDto> getGamesInfoFromPageWithNumber(int pageNumber){


    }

    //Parse all info from page about game with id `gameId`
    public GameDto getGameInfo(int gameId){



    }


    public List<GameDto> getAllGamesDto() {
        return allGamesDto;
    }
}

