package com.moviesapp.project.dm_project.util;

import com.moviesapp.project.dm_project.data.ModuleAddressBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConstansUtil {
    public static final String SERVER_IP = "http://:8080/shiro";
    public static final int connectSuccess = 200;
    public static final String POSTER_URL="https://image.tmdb.org/t/p/original";
    public static final String[] CLASSIFIER_NAME={"Action","Adventure","Comedy","Romance"};
    public static String[] stopwords = {"a", "about", "above", "after", "again", "against", "ain", "all", "am", "an", "and", "any", "are", "aren", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can", "couldn", "d", "did", "didn", "do", "does", "doesn", "doing", "don", "down", "during", "each", "few", "for", "from", "further", "had", "hadn", "has", "hasn", "have", "haven", "having", "he", "her", "here", "hers", "herself", "him", "himself", "his", "how", "i", "if", "in", "into", "is", "isn", "it", "its", "itself", "just", "ll", "m", "ma", "me", "mightn", "more", "most", "mustn", "my", "myself", "needn", "no", "nor", "not", "now", "o", "of", "off", "on", "once", "only", "or", "other", "our", "ours", "ourselves", "out", "over", "own", "re", "s", "same", "shan", "she", "should", "shouldn", "so", "some", "such", "t", "than", "that", "the", "their", "theirs", "them", "themselves", "then", "there", "these", "they", "this", "those", "through", "to", "too", "under", "until", "up", "ve", "very", "was", "wasn", "we", "were", "weren", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "won", "wouldn", "y", "you", "your", "yours", "yourself", "yourselves"};

    public static String SQLURL="";//appadingdb.cedzrdkywfqa.us-west-1.rds.amazonaws.com:3306/shiro
    public static String SQLUSER="";
    public static String SQLPASSWORD="";

    public static List<ModuleAddressBean> movie_lists;

    public static String[] genres={"Action","Crime","Animation","Adventure","Fantasy","Romance","Comedy","Drama","Thriller","Documentary","Foreign","War","Mystery"};

    public static Set<String> wordSets = new HashSet<>();
}
