package ir.s_mohammadi.iranigpractice.Pojo;

/**
 * Created by Sajjad on 8/9/2017.
 */
public class NewsModel {
    private int id;
    private String time;
    private String title;
    private String description;
    private String imageUrl;
    private boolean description_visible = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isDescription_visible() {
        return description_visible;
    }

    public void setDescription_visible(boolean description_visible) {
        this.description_visible = description_visible;
    }
}
