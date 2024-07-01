package com.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KeelungSightsCrawler {
    
    public KeelungSightsCrawler(){
        
    }
    
    public Sight[] getItems(String str){
        ArrayList resList=new ArrayList<Sight>();
        Sight[] res=new Sight[4];
        Sight[] resSights;
        try {
            // 連接到目標網頁並獲取Document對象
            String url = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/"; // 替換成你要爬取的目標網頁URL
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("#guide-point h4");
            String htmlString = doc.toString() ;
            System.err.println(paragraphs.toString());
            //System.err.println(htmlString);
            String regex = "<div id=\"guide-point\".*?</div>";
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(htmlString);
            if(matcher.find()){
                String result = matcher.group();
                doc=Jsoup.parse(result);
                str=str+"區";
                Elements areaElements=doc.select("h4:contains(" + str + ")");


                if(!areaElements.isEmpty()){
                    Element areaElement = areaElements.first();
                    // 獲取該區域後面的<ul>元素，這包含了景點的鏈接
                    Element ulElement = areaElement.nextElementSibling();

                    // 提取所有鏈接
                    Elements links = ulElement.select("a");
                    for (Element link : links) {
                        String href = link.attr("href");
                        resList.add(querySight(href, str));
                    }
                    Object list[]=resList.toArray();
                    resSights=Arrays.copyOf(list,list.length,Sight[].class);
                    return resSights;
                }else {
                    System.err.println("沒找到");
                }
                
            }else {
                System.err.println("沒有");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    } 

    public Sight querySight(String URL,String Zone){
        Sight res=new Sight(Zone);

        try {
            String fullURL="https://www.travelking.com.tw"+URL;
            //System.err.println(fullURL);
            Document doc = Jsoup.connect(fullURL).get();
            Elements Name=doc.select("[itemprop=name]");
            res.setSightName(Name.attr("content"));

            Elements Image=doc.select("[itemprop=image]");
            res.setPhotoURL(Image.attr("content"));

            Elements Description=doc.select("[itemprop=description]");
            res.setDescription(Description.attr("content"));
            
            Elements Address=doc.select("[itemprop=address]");
            res.setAddress(Address.attr("content"));

            String Category=doc.select("[property=rdfs:label]").select("strong").text();
            res.setCategory(Category);


        } catch (Exception e) {
            e.printStackTrace();
        }



        return res;
    }
}
