package pl.coderslab.warsztat3katw03.model;

public class SolutionToDisplay {
    private String title;
    private String authorName;
    private String dateStr;

    public SolutionToDisplay(String title, String authorName, String dateStr) {
        this.title = title;
        this.authorName = authorName;
        this.dateStr = dateStr;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDateStr() {
        return dateStr;
    }
}
