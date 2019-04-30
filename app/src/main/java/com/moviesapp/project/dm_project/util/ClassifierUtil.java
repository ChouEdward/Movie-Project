package com.moviesapp.project.dm_project.util;

import com.moviesapp.project.dm_project.data.ClassifierBase;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.moviesapp.project.dm_project.util.ConstansUtil.wordSets;

public class ClassifierUtil {

    public static List<ClassifierBase> classify(String inputs, String[] genres,List<ModuleAddressBean> movie_lists){
        List<ClassifierBase> lists = new ArrayList<>();

        String[] words = inputs.split(" ");
        double sum = 0.0;
        for (String genre:
                genres) {
            int classcount = 1;
            int allwordsinclass = 1;
            for (ModuleAddressBean moduleAddressBean:
                movie_lists) {
                if(moduleAddressBean.getGenres().contains(genre)){
                    classcount++;
                    allwordsinclass += moduleAddressBean.getMap_word().size();
                }
            }
            sum = classcount*1.0/movie_lists.size();

            for (String word:
                    words) {
                String wordstem = StringUtil.Stemmer.stem(word);
                int happens = 1;
                for (ModuleAddressBean moduleAddressBean:
                        movie_lists) {
                    if(moduleAddressBean.getGenres().contains(genre) && moduleAddressBean.getMap_word().get(wordstem)!=null){
                        happens += moduleAddressBean.getMap_word().get(wordstem);
                    }
                }

                sum *= happens*1.0/(allwordsinclass+wordSets.size());
            }
            ClassifierBase classifierBase = new ClassifierBase(genre,sum);
            lists.add(classifierBase);
            
        }

        Collections.sort(lists, new Comparator<ClassifierBase>() {
            @Override
            public int compare(ClassifierBase o1, ClassifierBase o2) {
                return o1.getScore()>o2.getScore()?-1:1;
            }
        });
        return lists;
    }
}
