public class Tile {

    private String type;
    private String image;
    private String item;
    private String character;

    public Tile() {
	type = "grass";
	image = "#";
    }

    public String getType() {
	return type;
    }

    public void setType(String data) {
	type = data;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String data) {
	image = data;
    }

    public String getItem() {
	return item;
    }

    public void setItem(String data) {
	item = data;
    }

    public String getCharacter() {
	return character;
    }

    public void setCharacter(String data) {
	character = data;
    }

}
