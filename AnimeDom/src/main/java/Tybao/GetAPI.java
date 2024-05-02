package Tybao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;


import Entity.Film;

public class GetAPI {
    private static String link = "https://phim.nguonc.com/api/films/phim-moi-cap-nhat?";// option = 1 (page=[int])
    private static String linktarget = "https://phim.nguonc.com/api/film/";// option = 2 (slug=[String])
    private static String linkcurrent;
    public static void main(String[] args) {
   
    	getHomePage(toJsonFromURL(linktarget, 2, "page=1","slug=thua-hoan-ky"));
    }

    @SuppressWarnings("deprecation")
    public static JSONObject toJsonFromURL(String urlString, int option, Object... data) {
        // option 1 load page \\ "page=?" - https://phim.nguonc.com/api/films/phim-moi-cap-nhat?
        // option 2 load detail film - https://phim.nguonc.com/api/film/thua-hoan-ky - https://phim.nguonc.com/api/film/slug
        JSONObject json = null;
        URL url = null;
        try {
            if(option == 1){
                // page=
                for (Object o : data) {
                    if(o.toString().contains("page")){
                        urlString += o.toString();
                        linkcurrent = urlString;
                        
                        break;
                    }
                }
                //https://phim.nguonc.com/api/films/phim-moi-cap-nhat?page=1
        
            }
            if(option == 2){
                // slug=
                for (Object o : data) {
                    if(o.toString().contains("slug")){
                        urlString += o.toString().replace("slug=", "");
                        linkcurrent = urlString;
                        break;
                    }
                }
            }
            

            // @SuppressWarnings("deprecation")
            // URL url = new URL("https://phim.nguonc.com/api/film/thua-hoan-ky");
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                // dong ket noi

                json = new JSONObject(stringBuilder.toString());

            } else {
                System.out.println("Error get API");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
        
        return json;
    }

    public static void getFilmDetail(JSONObject json) {
        System.out.println("---------------------------------------");
        System.out.println("status: "+(json.getString("status")));
        JSONObject movie = json.getJSONObject("movie");// object film detail
        //System.out.println("movie: "+movie);
        String thumb_url = movie.getString("thumb_url");// anh nho
        //System.out.println("thumb_url: " +thumb_url);
        String created = movie.getString("created");// thoi gian tao
        //System.out.println("created: " +created);
        String director = movie.getString("director");// tac gia
        //System.out.println("director: " +director);
        String poster_url = movie.getString("poster_url");// anh to
        //System.out.println("poster_url: " +poster_url);
        String description = movie.getString("description");// mo ta
        //System.out.println("description:" +description);
        String language = movie.getString("language");// ngon ngu
        // System.out.println("language: " +language);
        String current_episode = movie.getString("current_episode");// tap moi nhat vi du 2/12
       // System.out.println("current_episode: " +current_episode);
        String total_episodes = String.valueOf(movie.getInt("total_episodes"));// tong so tap
        // System.out.println("total_episodes: " +total_episodes);
        String quality = movie.getString("quality");// chat luong
        // System.out.println("quality: " +quality);
        String casts = movie.getString("casts");// dien vien
        // System.out.println("casts: " +casts);
        String original_name = movie.getString("original_name");// ten goc
        // System.out.println("original_name: " +original_name);
        String name = movie.getString("name");// ten tieng viet
        // System.out.println("name: " +name);
        String modified = movie.getString("modified");// ngay sua doi
        // System.out.println("modified: " +modified);
        String id = movie.getString("id");// id
        // System.out.println("id: " +id);
        String time = movie.getString("time");// thoi gian / 1 tap
        // System.out.println("time: " +time);
        JSONObject category = movie.getJSONObject("category");
        JSONArray episodes = movie.getJSONArray("episodes");// list tap
        
        for (int index = 0; index < episodes.length(); index++) {
            JSONObject j = episodes.getJSONObject(index);
            String server_name = j.getString("server_name");
            //System.out.println("server_name: "+server_name);
            JSONArray arr = j.getJSONArray("items");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                //System.out.println(o);
                String nameOfArr = o.getString("name");// tap
                //System.out.println("name: "+nameOfArr);
                String embed = o.getString("embed");// link phim
                //System.out.println("embed: "+embed);
                String m3u8 = o.getString("m3u8");// bi loi
                //System.out.println("m3u8: "+m3u8);
                String slug = o.getString("slug");// tap-1
                //System.out.println("slug: "+slug);

            }
            
            //System.out.println(arr);
        }
        System.out.println("____________");
        //System.out.println(category);
        
        
        for (int i = 1; i <= 4; i++) {
            JSONObject j1 = category.getJSONObject(String.valueOf(i));
            if(j1 == null){
                break;
            }
            JSONArray list = j1.getJSONArray("list");
            JSONObject group = j1.getJSONObject("group");
            System.out.println(group);
            for (Object object : list) {
                System.out.println(object);
            }
            //System.out.println(list);
            
            System.out.println("-----");
            // for (int j = 0; j < array.length(); j++) {
            //     String name_category = array.getString("name");
            // }
            
            
            
        }
        
        
        //System.out.println(list1);
        //System.out.println("____________");
        //System.out.println(episodes);
        // Them vao danh sach
      
        
    }


    public static List<Film> getHomePage(JSONObject json) {
        

        System.out.println(json.getString("status"));
        JSONArray arrHomePage = json.getJSONArray("items");
        List<Film> list_film = new ArrayList<>();
        
        // Them vao danh sach
        for (int i = 0; i < arrHomePage.length(); i++) {
            JSONObject j = arrHomePage.getJSONObject(i);
            String thumb_url = j.getString("thumb_url");
            String original_name = j.getString("original_name");
            String name = j.getString("name");
            String modified = j.getString("modified");
            String slug = j.getString("slug");
            Film e = new Film(thumb_url, original_name, name, modified, slug);
            list_film.add(e);
        }
        
        //
        //writeList(list_film);
        return list_film;
    }

    public static void writeList(List<Film> list_film) {
        try {
            // xuat ra file txt
        int index = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Link: "+linkcurrent+"\n");
        for (Film film : list_film) {
            ++index;
            builder.append("Phim " + index + ":\n");
            builder.append("-Name: " + film.getName() + "\n");
            builder.append("-Modified: " + film.getModified() + "\n");
            builder.append("-Original_name: " + film.getOriginal_name() + "\n");
            builder.append("-Slug: " + film.getSlug() + "\n");
            builder.append("-Thumb_url: " + film.getThumb_url() + "\n");
        }
            String file = System.getProperty("user.dir") + "\\hehe.txt";
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            buffer.write(builder.toString());
            buffer.close();
            System.out.println("Done Write");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public static void writeString(String s) {
        try {
            // xuat ra file txt



            String file = System.getProperty("user.dir") + "\\hehe.txt";
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            buffer.write(s);
            buffer.close();
            System.out.println("Done Write");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

}
