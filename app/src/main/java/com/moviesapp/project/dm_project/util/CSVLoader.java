package com.moviesapp.project.dm_project.util;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.moviesapp.project.dm_project.data.ModuleAddressBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVLoader {

    public static final String TAG="mytest";

    public static List<ModuleAddressBean> readCSV(String path, Activity activity) {
        List<ModuleAddressBean> list = new ArrayList<ModuleAddressBean>();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fiStream;
        Scanner scanner;
        try {
            fiStream = new FileInputStream(file);
//            InputStream in = activity.getResources().openRawResource(R.raw.test);
            scanner = new Scanner(fiStream, "UTF-8");
            scanner.nextLine();//读下一行,把表头越过。不注释的话第一行数据就越过去了
            int a = 0;
            while (scanner.hasNextLine()) {
                String sourceString = scanner.nextLine();
                Log.e("source-->", sourceString);
                Pattern pattern = Pattern.compile("[^,]*,");
                Matcher matcher = pattern.matcher(sourceString);
                String[] lines = new String[8];
                int i = 0;
                while (matcher.find()) {
                    String find = matcher.group().replace(",", "");
                    lines[i] = find.trim();
                    Log.e(TAG, "find=" + find + ",i=" + i + ",lines=" + lines[i]);
                    i++;
                }
                ModuleAddressBean bean = new ModuleAddressBean(lines[9]);
                list.add(bean);
                a++;
                i = 0;
            }
        } catch (NumberFormatException e) {
            //showToast(activity, "NumberFormatException");
            Log.e(TAG,"NumberFormatException");
                    e.printStackTrace();
        } catch (FileNotFoundException e) {
            //showToast(activity, "文件不存在");
            Log.e(TAG,"文件不存在");
            e.printStackTrace();
        }
        return list;
    }

    public static List<ModuleAddressBean> readMonDataCsv() {
        int i = 0;// 用于标记打印的条数
        List<ModuleAddressBean> list = new ArrayList<>();
        try {
            File csv = new File(Environment.getExternalStorageDirectory() + "/Android/test.csv"); // CSV文件路径
            BufferedReader br = new BufferedReader(new FileReader(csv));
            br.readLine();
            String line = "";
            /**
             * 这里读取csv文件中的前10条数据
             * 如果要读取第10条到30条数据,只需定义i初始值为9,wile中i<10改为i>=9&&i<30即可,其他范围依次类推
             */
            while ((line = br.readLine()) != null && i<100) { // 这里读取csv文件中的前10条数据
                i++;
//                System.out.println("第" + i + "行：" + line);// 输出每一行数据
                /**
                 *  csv格式每一列内容以逗号分隔,因此要取出想要的内容,以逗号为分割符分割字符串即可,
                 *  把分割结果存到到数组中,根据数组来取得相应值
                 */
//                String buffer[] = line.split(",");// 以逗号分隔
//                Log.e("mytest","第" + i + "行：" + line+"\n");
                ModuleAddressBean moduleAddressBean = mycsvreader(line);
                list.add(moduleAddressBean);
                // 取第一列数据
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ModuleAddressBean mycsvreader(String str){
        ModuleAddressBean moduleAddressBean;
        List<String> list = new ArrayList<>();
        boolean isincontent = false;
        int maohao=0;
        String contents="";

        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c==','){
                if(maohao==0){
                    list.add(contents);
                    contents="";
                }else{
//                    if(contents.equals("-")){
//                        contents="";
//                    }
                    contents+=c;
                }
            }else if(c=='"'){
                if(maohao==0){
                    maohao++;
                }else{
                    maohao--;
                }
            }else{
                contents+=c;
            }
        }
        list.add(contents);
//        for(int i=0;i<list.size();i++){
//            Log.e("mytest",i+" : "+list.get(i));
//        }
        moduleAddressBean=new ModuleAddressBean(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),
                Long.valueOf(list.get(5)),list.get(6),list.get(7),list.get(8),list.get(9),list.get(10),list.get(11),list.get(12)
                ,list.get(13),list.get(14),list.get(15),list.get(16),list.get(17),list.get(18),list.get(19),list.get(20)
                ,list.get(21),list.get(22),list.get(23));
        return moduleAddressBean;
    }
}
