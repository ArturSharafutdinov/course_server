package ru.kinopoisk.server.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Constraints {

    //Templates for parsing
    public static final String templateForDiv = "div[class=%s]";
    public static final String templateForHardDiv = "div[class=%s][itemprop=articleBody][data-io-article-url=%s]";
    public static final String templateForSpan = "span[class=%s]";
    public static final String templateForImage = "img[class=%s]";
    public static final String templateForP = "p[class=%s]";
    public static final String templateForA = "a[class=%s]";
    public static final String templateForH1 = "h1[class=%s]";
    public static final String templateForGamesLink = "https://www.igromania.ru/games/all/all/all/all/all/0/%d/release_asc/";
    public static final String templateForGame = "https://www.igromania.ru/game/%d/";


    //Format for parsing string date like 16.11.2020
    public static final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    // Array of game genres
    private static String [] genresArray = {"Beat-em-Up",
            "FPS",
            "Hack And Slash",
            "MMO",
            "MOBA",
            "Point & Click",
            "Roguelike",
            "TD",
            "TPS",
            "Аркада",
            "Визуальная новелла",
            "Выживание",
            "Гонки",
            "Игра для взрослых",
            "Интерактивное кино",
            "История",
            "Квест",
            "ККИ",
            "Кооператив",
            "Менеджер",
            "Метроидвания",
            "Музыка",
            "Настольная игра",
            "Обучение",
            "Паззл",
            "Платформер",
            "Пошаговая стратегия",
            "Приключение",
            "Ролевая игра",
            "Симулятор",
            "Симулятор ходьбы",
            "Спорт",
            "Стелс",
            "Стратегия",
            "Стратегия в реальном времени",
            "Тактика",
            "Файтинг",
            "Хоррор",
            "Шутер",
            "Экшен" };

    // List of genres, made to use .contains method
    public static final List<String> genres = new ArrayList<>(Arrays.asList(genresArray));


}