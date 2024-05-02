package Entity;

public class Film {
String thumb_url;
String original_name;
String name;
String modified;
String slug;
public String getThumb_url() {
    return thumb_url;
}
public void setThumb_url(String thumb_url) {
    this.thumb_url = thumb_url;
}
public String getOriginal_name() {
    return original_name;
}
public void setOriginal_name(String original_name) {
    this.original_name = original_name;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public String getModified() {
    return modified;
}
public void setModified(String modified) {
    this.modified = modified;
}
public String getSlug() {
    return slug;
}
public void setSlug(String slug) {
    this.slug = slug;
}
public Film(String thumb_url, String original_name, String name, String modified, String slug) {
    this.thumb_url = thumb_url;
    this.original_name = original_name;
    this.name = name;
    this.modified = modified;
    this.slug = slug;
}
public Film() {
}


}
