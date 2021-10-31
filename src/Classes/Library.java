package Classes;

public class Library {
    private String id;
    private String name; //branch name
    public Library(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Library(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}

