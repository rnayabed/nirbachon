package in.rnayabed.nirbachon;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.TimerTask;

public class FetcherTask extends TimerTask
{
    private final String url;
    private final Chart chart;
    private final Label refreshingLabel;
    private final Details details;

    public FetcherTask(String url, Chart chart, Details details, Label refreshingLabel)
    {
        this.url = url;
        this.details = details;
        this.chart= chart;
        this.refreshingLabel = refreshingLabel;
        partyHashMap = new HashMap<>();
    }


    private final HashMap<String, Party> partyHashMap;

    @Override
    public void run()
    {
        try
        {

            Platform.runLater(()->refreshingLabel.setVisible(true));
            Document document = Jsoup.connect(url).get();

            Elements scriptsElement = document.getElementsByTag("script");



            Element voteScript = scriptsElement.get(2);

            String voteHtml = voteScript.html();


            String starterString = "data.addRows([[";
            String endString = "],]);";
            int start = voteHtml.indexOf(starterString);
            int stop = voteHtml.indexOf(endString,start);

            String addRowsPart = voteHtml.substring(start, stop).replace(starterString, "");

            String[] voteSep = addRowsPart.split("],\\[");




            String colourStarterString = "slices:{ ";
            String colourEndString = "'}, }";
            int colourStart = voteHtml.indexOf(colourStarterString);
            int colourStop = voteHtml.indexOf(colourEndString,colourStart);

            String slicesPart = voteHtml.substring(colourStart, colourStop).replace(colourStarterString, "");

            String[] colourSep = slicesPart.split("'},");


            for(int i = 0;i<voteSep.length;i++)
            {
                String eachGroup = voteSep[i];
                String colour = colourSep[i];

                String[] parts = eachGroup.split("%}',");

                int indexOfFirstBrace = parts[0].indexOf("{");

                String partyName = parts[0].substring(1, indexOfFirstBrace);
                double sharePercentage = Double.parseDouble(parts[0].substring(indexOfFirstBrace+1));

                int partyVotes = Integer.parseInt(parts[1]);


                if(!partyHashMap.containsKey(partyName))
                {
                    partyHashMap.put(partyName, new Party(partyName));
                }

                Party party = partyHashMap.get(partyName);
                party.setPercentageShare(sharePercentage);
                party.setVotes(partyVotes);
                party.setColor(colour.substring(colour.indexOf("'")+1));
            }


            Platform.runLater(()->{
                refreshingLabel.setVisible(false);
                details.update(partyHashMap);
            });

            chart.update(partyHashMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
