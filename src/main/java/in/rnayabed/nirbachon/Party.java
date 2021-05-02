package in.rnayabed.nirbachon;

public class Party
{
    private String name;
    private double percentageShare;
    private int votes;
    private String color = null;

    public Party(String name)
    {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public double getPercentageShare() {
        return percentageShare;
    }

    public int getVotes()
    {
        return votes;
    }

    public String getName()
    {
        return name;
    }

    public void setPercentageShare(double percentageShare) {
        this.percentageShare = percentageShare;
    }

    public void setVotes(int votes)
    {
        this.votes = votes;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
