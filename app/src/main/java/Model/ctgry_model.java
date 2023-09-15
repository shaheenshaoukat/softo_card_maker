package Model;

public class ctgry_model {
    private String name;
    private int img;


    public ctgry_model() {
    }

    public ctgry_model(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
