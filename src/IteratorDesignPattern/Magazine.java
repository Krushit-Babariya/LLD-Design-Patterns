package IteratorDesignPattern;

public class Magazine {
    private final String title;
    private final String publisher;
    private final int issueNumber;

    public Magazine(String title, String publisher, int issueNumber) {
        this.title = title;
        this.publisher = publisher;
        this.issueNumber = issueNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String toString() {
        return String.format("Magazine: %s - Issue #%d (Publisher: %s)", title, issueNumber, publisher);
    }
}

