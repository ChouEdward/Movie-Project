package com.moviesapp.project.dm_project.util;

import opennlp.tools.stemmer.PorterStemmer;

public class StringUtil {

    public static PorterStemmer Stemmer = new PorterStemmer();

    public static String[] splitToWords(String s){
        String splitString = s ;
        splitString=splitString.replace("."," ");
        splitString=splitString.replace(":"," ");
        splitString=splitString.replace(","," ");
        splitString=splitString.replace("-"," ");
        splitString=splitString.toLowerCase();
        return splitString.split(" ");
    }
}
