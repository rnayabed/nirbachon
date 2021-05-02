package in.rnayabed.nirbachon;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.HashMap;

public class Details extends ScrollPane
{
    VBox vBox;

    public Details()
    {
        vBox = new VBox();
        vBox.getChildren().add(new Label("Updating ..."));
        vBox.getStyleClass().add("details-pane");
        vBox.setSpacing(10);

        vBox.prefHeightProperty().bind(heightProperty().subtract(10));

        setContent(vBox);
    }

    public void update(HashMap<String, Party> partyHashMap)
    {
        vBox.getChildren().clear();

        for(String partyName : partyHashMap.keySet())
        {
            Party party = partyHashMap.get(partyName);
            Label label = new Label(party.getName()+" - "+party.getVotes() +" - "+party.getPercentageShare()+"%");

            vBox.getChildren().add(label);
        }
    }
}
