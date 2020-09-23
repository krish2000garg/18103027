package sample;
//package com.company;
import java.lang.annotation.Documented;
import java.util.*;
import java.lang.Math;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.*;
public class Controller {

    public static int pageCount=0;
    final static String baseURL = "https://pec.ac.in";
    static int fileNumber = 0;

    public static String fullLinkFinder(String url){
        ArrayList<String> validURL = new ArrayList<String>();
        validURL.add("https://alumni.pec.ac.in");
        validURL.add("https://pec.ac.in");
        validURL.add("http://pec.ac.in");
        validURL.add("https://www.pec.ac.in");
        validURL.add("http://www.pec.ac.in");

        if(url.startsWith(validURL.get(0)) || url.startsWith(validURL.get(1)) || url.startsWith(validURL.get(2)) || url.startsWith(validURL.get(3)) || url.startsWith(validURL.get(4))){
            return url;
        }
        if(url.startsWith("#")){
            return "https://pec.ac.in/";
        }
        if(url.startsWith("/")){
            return baseURL + url;
        }
        return url;

    }
    public static boolean isValid(String url){

        ArrayList<String> validURL = new ArrayList<String>();
        validURL.add("https://alumni.pec.ac.in");
        validURL.add("https://pec.ac.in");
        validURL.add("http://pec.ac.in");
        validURL.add("https://www.pec.ac.in");
        validURL.add("http://www.pec.ac.in");

        if(url.startsWith(validURL.get(0)) || url.startsWith(validURL.get(1)) || url.startsWith(validURL.get(2)) || url.startsWith(validURL.get(3)) || url.startsWith(validURL.get(4))){
            return true;
        }
        return false;
    }

    public static void traverse(int index, ArrayList<String> urlList, ArrayList<String> extraUrl,ArrayList<String> finalResultUrl, ArrayList<String> finalResultPara, Set<String> visitedUrls, int depth){

        if(depth == 5)
            return;

        for(int i=0;i<urlList.size();i++)
        {
            String url = urlList.get(i);

            try{
                final Document document = Jsoup.connect(url).get();
                Elements links = document.getElementsByTag("a");
                Elements paras = document.getElementsByTag("p");

                String paragraph = new String("\n");
                for(Element para: paras){
                    String paraText = para.text();
                    if(paraText.length()!=0){
                        paragraph += paraText;
                        paragraph+="\n";
                    }
                }
                if(url.contains("faculty") || url.contains("Faculty") || url.contains("FACULTY") || paragraph.contains("faculty")|| paragraph.contains("Faculty")|| paragraph.contains("FACULTY")){
                    finalResultUrl.add(url);
                    finalResultPara.add(paragraph);
                    pageCount++;
                    System.out.println("Page number ->" + pageCount + "Scraped!!");
                }

                ArrayList<String> urlListNext = new ArrayList<String>();

                for(Element link:links) {
                    String linkHref = link.attr("href");

                    // Garbage Links
                    if (linkHref.equals("") || linkHref.endsWith("javascript:;") || linkHref.startsWith("https://pec.ac.in/~pecac") || linkHref.endsWith("annexure-III")) {
                        continue;
                    }

                    if (linkHref.equals(".pdf") || linkHref.contains(".PDF") || linkHref.contains(".xlsx") || linkHref.contains(".XLSX") || linkHref.contains(".docx") || linkHref.contains(".DOCX") || linkHref.contains(".doc") || linkHref.contains(".DOC") || linkHref.contains(".jpg") || linkHref.contains(".jpeg") || linkHref.contains(".png") || linkHref.contains(".svg") || linkHref.contains(".JPG") || linkHref.contains(".JPEG") || linkHref.contains(".PNG") || linkHref.contains(".SVG")) {
                        continue;
                    }

                    linkHref = fullLinkFinder(linkHref);

                    // to check whether to traverse this link or not
                    boolean checkLink = isValid(linkHref);

                    if (checkLink == false) {
                        if (visitedUrls.contains(linkHref)) {
                            continue;
                        } else {
                            visitedUrls.add(linkHref);
                            extraUrl.add(linkHref);
                        }

                        continue;
                    }

                    if (visitedUrls.contains(linkHref)) {
                        continue;
                    } else {
                        visitedUrls.add(linkHref);
                        urlListNext.add(linkHref);
                    }

                }
                traverse(index,urlList,extraUrl,finalResultUrl, finalResultPara,visitedUrls, depth+1);

            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args)throws IOException  {
        // write your code here
        // Using string and not StringBuffer because Jsoup.connect requires string input and gives out string as output
        ArrayList<String> urlList = new ArrayList<String>();
        String url = "https://www.pec.ac.in/";
        urlList.add(url);

        ArrayList<String> extraUrl = new ArrayList<String>();
        ArrayList<String> finalResultUrl = new ArrayList<String>();
        ArrayList<String> finalResultPara = new ArrayList<String>();

        Set<String> visitedUrls = new HashSet<String>();

        int index = 0, depth = 0;
        traverse(index,urlList,extraUrl,finalResultUrl, finalResultPara,visitedUrls, depth);
        // Writing to CSV
        File file = new File("C:\\Users\\KRISH GARG\\Desktop\\Assignment 3\\Data.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("Links,Paragraph Content");
        bw.newLine();
        for(int i=0;i<finalResultUrl.size();i++)
        {
            String paragraph = finalResultPara.get(i).replace(",","");
            bw.write(finalResultUrl.get(i)+","+paragraph);
            bw.newLine();
        }
        bw.close();
        fw.close();

        System.out.println("CSV File written!");
    }
}
