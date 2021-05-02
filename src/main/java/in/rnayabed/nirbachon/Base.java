package in.rnayabed.nirbachon;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;
import java.util.Timer;

public class Base extends VBox
{
    public Base()
    {
        setAlignment(Pos.CENTER);

        getStylesheets().add(Objects.requireNonNull(Main.class.getResource("style.css")).toExternalForm());
        Font.loadFont(Main.class.getResourceAsStream("SF-Pro-Text-Regular.otf"), 13);

        StackPane stackPane = new StackPane();


        Chart chart = new Chart();
        chart.setVisible(true);

        Details details = new Details();
        details.setVisible(false);

        stackPane.getChildren().addAll(chart, details);

        setOnMouseClicked(mouseEvent -> {
            details.setVisible(!details.isVisible());
            chart.setVisible(!chart.isVisible());
        });


        VBox.setVgrow(stackPane, Priority.ALWAYS);


        Label refreshingLabel = new Label("Refreshing ...");

        refreshingLabel.getStyleClass().add("refreshing-label");


        getChildren().addAll(stackPane, refreshingLabel);

        new Timer().scheduleAtFixedRate(new FetcherTask("https://results.eci.gov.in/Result2021/partywiseresult-S25.htm?st=S25", chart, details, refreshingLabel),
                0,
                3*60*1000);


    }
}
