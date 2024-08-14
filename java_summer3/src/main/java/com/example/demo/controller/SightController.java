package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sight;
import com.example.demo.param.SightRequestParameter;

@Controller
@RestController
public class SightController {
    private static final Map<String, ArrayList<Sight>> sightMap = new HashMap<>();

    static {
        try {
            String url = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";
            Document doc = Jsoup.connect(url).get();
            Elements allpara=doc.select("#guide-point");
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

    @GetMapping("/SightAPI")
    public ResponseEntity<List<Sight>> getSights(
            @ModelAttribute SightRequestParameter param
    ) {
        String keyword = param.getZone();
        keyword=keyword+"區";
        ArrayList<Sight>values=sightMap.get(keyword);
        if (values != null) {
            return ResponseEntity.ok(values);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}