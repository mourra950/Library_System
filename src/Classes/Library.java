package Classes;

public class Library {
    private String id;
    private String name; //branch name
    private String book_id[];
    static private int BooksLimit =50;
    public Library(String id, String name, book books[]) {
        this.id = id;
        this.name = name;
        for(int i=0;i<BooksLimit;i++){
            book_id[i] = books[i].getId();
        }
    }
    public Library(String id){
        this.id=id;
    }

}

