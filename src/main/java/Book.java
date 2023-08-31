import java.time.Instant;
public class Book {
    private final int ID;
    private final String Title;
    private final String Author;
    private int Copies;
    private Boolean IssueStatus;
    private Instant IssueTime;

    public Book(int id, String title, String author, int copies) {
        this.ID = id;
        this.Title = title;
        this.Author = author;
        this.Copies = copies;
        this.IssueStatus = false;
        this.IssueTime = null;
    }

    public int getID(){
        return this.ID;
    }
    public Instant getIssueTime(){
        return this.IssueTime;
    }
    public boolean isIssued(){
        return this.IssueStatus;
    }
    public String getAuthor(){
        return this.Author;
    }
    public String getTitle(){
        return this.Title;
    }
    public int getCopies(){
        return this.Copies;
    }

    public void issueBook(){
        this.IssueStatus=true;
        this.IssueTime=Instant.now();
        this.Copies--;
    }
    public void returnBook(){
        this.IssueStatus=false;
        this.Copies++;
    }
    public void increaseCopies(){
        this.Copies++;
    }
}
