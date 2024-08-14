package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.entity.Sight;

public class SightService {
    private static final Map<String, ArrayList<Sight>> sightMap = new HashMap<>();

    public SightService() {
        try {
            String url = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";
            Document doc = Jsoup.connect(url).get();
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
                                Document DOC = Jsoup.connect(fullURL).get();
                                
                                String Name=DOC.select("[itemprop=name]").attr("content");

                                String Image=DOC.select("[itemprop=image]").attr("content");

                                String Description=DOC.select("[itemprop=description]").attr("content");
                                
                                String Address=DOC.select("[itemprop=address]").attr("content");

                                String Category=DOC.select("[property=rdfs:label]").select("strong").text();
                                Sight res=new Sight(Name,nowZone,Category,Image,Description,Address);
                                list.add(res);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    sightMap.put(nowZone, list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Sight>getSights(String zone){
        return sightMap.get(zone);
    }
}
