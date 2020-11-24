package ru.kinopoisk.server.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constraints {
    //Templates for parsing
    public static final String templateForDiv = "div[class=%s]";
    public static final String templateForHardDiv = "div[class=%s][itemprop=articleBody][data-io-article-url=%s]";
    public static final String templateForSpan = "span[class=%s]";
    public static final String templateForImage = "img[class=%s]";
    public static final String templateForP = "p[class=%s]";
    public static final String templateForA = "a[class=%s]";


    //Format for parsing string date like 16.11.2020
    public static final DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    private static final String templateForGamesLink = "https://www.igromania.ru/games/all/all/all/all/all/0/%d/";
}
