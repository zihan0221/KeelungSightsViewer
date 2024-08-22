package com.example.demo.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.entity.Sight;

public class KeelungSightCrawler {
    private static final Map<String, ArrayList<Sight>> sightMap = new HashMap<>();
    public KeelungSightCrawler(){
        try {
            String url = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";
            Document doc = Jsoup.connect(url).timeout(10000).get();
            Elements paragraphs = doc.select("#guide-point h4");
            for (Element h4 : paragraphs) {
                String nowZone=h4.text();
                Element nextUl = h4.nextElementSibling();
                if (nextUl != null && nextUl.tagName().equals("ul")) {
                    Elements liElements = nextUl.select("li");

                    List<String> links = new ArrayList<>();
                    for (Element li : liElements) {
                        
                        Element link = li.selectFirst("a");
                        if (link != null) {
                            links.add(link.attr("href"));
                        }
                    }
                    ArrayList<Sight> list = new ArrayList<>();
                    for (String link : links) {
                        {
                            try {
                                String fullURL="https://www.travelking.com.tw"+link;
                                Document DOC = Jsoup.connect(fullURL).timeout(10000).get();
                                
                                String Name=DOC.select("[itemprop=name]").attr("content");

                                String Image=DOC.select("[itemprop=image]").attr("content");

                                String Description=DOC.select("[itemprop=description]").attr("content");
                                
                                String Address=DOC.select("[itemprop=address]").attr("content");

                                String Category=DOC.select("[property=rdfs:label]").select("strong").text();
                                Sight res=new Sight(Name,nowZone,Category,Image,Description,Address);
                                res.debugSomething();
                                list.add(res);

                            } catch (IOException e) {
                                System.out.println("Something went wrong.");
                            }
                        }
                    }
                    sightMap.put(nowZone, list);

                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }

    public ArrayList<Sight> getSightByZone(String zone){
        
        return sightMap.get(zone);
    }
}
