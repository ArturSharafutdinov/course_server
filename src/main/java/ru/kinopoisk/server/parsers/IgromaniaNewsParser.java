package ru.kinopoisk.server.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import ru.kinopoisk.server.persistence.dto.ArticleDto;
import ru.kinopoisk.server.persistence.interfaces.Parser;
import ru.kinopoisk.server.utils.Constraints;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//Class for parsing igromania news page
public class IgromaniaNewsParser implements Parser {

    // Name of css class where locate all news
    private static String allNewsClass = "lcol"; //div

    //Name of css class where store all info about article
    private static String blockNewsClass = "aubl_item"; //div

    //List of news
    private List<ArticleDto> allArticleDtos = new LinkedList<>();

    //Function get news page in html format, parse it to fill Article object and then save it in articles list
    public void getInfoFromNews() throws IOException, ParseException {
        Document doc = Jsoup.parse(new URL("https://www.igromania.ru/news/"), 10000);
        Element newsBlock = doc.selectFirst(String.format(Constraints.templateForDiv, allNewsClass));
        for (Element news : newsBlock.select(String.format(Constraints.templateForDiv, blockNewsClass))) {

            Element data = news.selectFirst(String.format(Constraints.templateForDiv, "aubli_data"));
            Element section = data.selectFirst(String.format(Constraints.templateForDiv, "aubli_sect"));
            Element info = data.selectFirst(String.format(Constraints.templateForDiv, "aubli_info"));


            String link = news.select(String.format(Constraints.templateForA, "aubli_img")).first().attr("href");
            String image = news.select("img").first().attr("src");
            String name = data.select(String.format(Constraints.templateForA, "aubli_name")).text();
            String smallDesc = data.select(String.format(Constraints.templateForDiv, "aubli_desc")).text();

            String infoPublic = info.select(String.format(Constraints.templateForDiv, "aubli_date")).text();
            String[] infoPublicArray = infoPublic.split(" ");
            String date = infoPublic.split(" ")[0];
            String author;
            if (infoPublicArray.length == 4) {
                author = infoPublic.split(" ")[2] + " " + infoPublic.split(" ")[3];
            } else {
                author = infoPublic.split(" ")[2];
            }


            String tempSect = section.select(String.format(Constraints.templateForSpan, "hidbl")).text();
            String type = tempSect.substring(0, tempSect.length() - 1);
            String views = section.select(String.format(Constraints.templateForSpan, "sicn_views")).text();

            Date simpleDate = Constraints.format.parse(date);



            Document articlePage = Jsoup.parse(new URL("https://www.igromania.ru/"+link), 3000);

            Element fullDescClass = articlePage.selectFirst(String.format(Constraints.templateForDiv,"lcol"))
                    .selectFirst(String.format(Constraints.templateForDiv,"page_news noselect"))
                    .selectFirst(String.format(Constraints.templateForDiv,"page_news_content haveselect"))
                    .selectFirst(String.format(Constraints.templateForDiv,"universal_content clearfix","https://www.igromania.ru/"+link))
                    ;

          String fd = Jsoup.clean(fullDescClass.toString(), Whitelist.none()).replaceAll("&nbsp;"," ");
            String fullDescription;
          if(fd.contains("Больше на Игромании")) {
              int igro=fd.lastIndexOf("Больше на Игромании");
            fullDescription  = fd.substring(0,igro).replaceAll("[\\s]{2,}", " ");
          }
          else {
              fullDescription=fd.replaceAll("[\\s]{2,}", " ");
          }
            ArticleDto articleDto = new ArticleDto(name, author, simpleDate, smallDesc, fullDescription, Integer.parseInt(views), type, link);

            allArticleDtos.add(articleDto);

        }
    }


    public int getViewsFromArticleByUrl(String link){
        int newViews=-1;
        boolean isDropped=false;
        while(!isDropped) {
            try {
                Document articlePage = Jsoup.parse(new URL("https://www.igromania.ru/" + link), 3000);
                Element infoBlock = articlePage.selectFirst(String.format(Constraints.templateForDiv, "info_block_botrt"));
                newViews = Integer.parseInt(infoBlock.text().split(" ")[1]);
                isDropped = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newViews;
    }

    //Show all articles in console
    public void showAllArticles() {
        for (ArticleDto articleDto : allArticleDtos) {
            System.out.println(articleDto);
            System.out.println();
        }
    }

    //Getter for articles list
    public List<ArticleDto> getAllArticles() {
        return allArticleDtos;
    }

}

