package in.rnayabed.nirbachon;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chart extends PieChart
{
    public Chart()
    {
        setLegendVisible(false);

    }

    public void update(HashMap<String, Party> partyHashMap)
    {
        PieChart.Data[] data = new PieChart.Data[partyHashMap.keySet().size()];

        String[] hexColour = new String[partyHashMap.keySet().size()];


        int i = 0;
        for(String partyName : partyHashMap.keySet())
        {
            Party party = partyHashMap.get(partyName);

            data[i] = new PieChart.Data(partyName, party.getVotes());
            hexColour[i] = party.getColor();

            data[i].nameProperty().bind(
                    Bindings.concat(
                            data[i].getName()+" - "+partyHashMap.get(partyName).getPercentageShare()+" %"
                    )
            );

            i++;
        }



        ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(data);

        Platform.runLater(()->{

            setData(observableList);
            applyCustomColorSequence(observableList, hexColour);

        });
    }

    private void applyCustomColorSequence(ObservableList<Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }
}
