package com.moviesapp.project.dm_project.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.util.ConstansUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstansUtil.movie_lists = new ArrayList<>();
        new LongOperation().execute();
    }

    private void connectSQL(){
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(ConstansUtil.SQLURL, ConstansUtil.SQLUSER, ConstansUtil.SQLPASSWORD);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT  id,title,overview,adult,belongs_to_collection,budget,genres,homepage,imdb_id,original_language,original_title,popularity,poster_path,production_companies,production_countries,release_date,revenue,runtime,spoken_languages,status,tagline,video,vote_average,vote_count FROM movies_metadata";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                ModuleAddressBean moduleAddressBean = new ModuleAddressBean();
                //Retrieve by column name
                moduleAddressBean.setId(rs.getLong("id"));
                moduleAddressBean.setTitle(rs.getString("title"));
                moduleAddressBean.setOverview(rs.getString("overview"));
                moduleAddressBean.setAdult(rs.getString("adult"));
                moduleAddressBean.setBelongsToCollection(rs.getString("belongs_to_collection"));
                moduleAddressBean.setBudget(rs.getString("budget"));
                moduleAddressBean.setGenres(rs.getString("genres"));
                moduleAddressBean.setHomepage(rs.getString("homepage"));
                moduleAddressBean.setImdbId(rs.getString("imdb_id"));
                moduleAddressBean.setOriginalLanguage(rs.getString("original_language"));
                moduleAddressBean.setOriginalTitle(rs.getString("original_title"));
                moduleAddressBean.setPopularity(rs.getString("popularity"));
//                poster_path,production_companies,production_countries,release_date,revenue,runtime,spoken_languages,status,tagline,video,vote_average,vote_count
                moduleAddressBean.setPosterPath(rs.getString("poster_path"));
                moduleAddressBean.setProductionCompanies(rs.getString("production_companies"));
                moduleAddressBean.setProductionCountries(rs.getString("production_countries"));
                moduleAddressBean.setReleaseDate(rs.getString("release_date"));
                moduleAddressBean.setRevenue(rs.getString("revenue"));
                moduleAddressBean.setRuntime(rs.getString("runtime"));
                moduleAddressBean.setSpokenLanguages(rs.getString("spoken_languages"));
                moduleAddressBean.setStatus(rs.getString("status"));
                moduleAddressBean.setTagline(rs.getString("tagline"));
                moduleAddressBean.setVideo(rs.getString("video"));
                moduleAddressBean.setVoteAverage(rs.getString("vote_average"));
                moduleAddressBean.setVoteCount(rs.getString("vote_count"));
//                moduleAddressBean.setAllrevenue(rs.getFloat("allrevenue"));

                moduleAddressBean.castStringToMap(moduleAddressBean.getTitle()+" "+moduleAddressBean.getOverview());
                ConstansUtil.movie_lists.add(moduleAddressBean);

            }
            rs.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try



        }//end try

    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
//            for (int i = 0; i < 5; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.interrupted();
//                }
//            }
            connectSQL();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("testss",ConstansUtil.movie_lists.size()+"");
            startActivity(new Intent(LoadDataActivity.this, MainActivity.class));
            finish();
//            TextView txt = (TextView) findViewById(R.id.output);
//            txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
