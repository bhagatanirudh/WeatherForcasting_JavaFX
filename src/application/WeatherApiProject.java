package application;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherApiProject extends Application {

	static Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15;
	static TextField tf1;
	public static ArrayList<Weather> weatherArrayList = new ArrayList<>();
	public static ArrayList<Location> locationArrayList = new ArrayList<>();
	
	public static void setValues(){
		String city = tf1.getText();
		tf1.clear();
		try {
	      	String LocationKey = "";
	  		var cityApi = "http://dataservice.accuweather.com/locations/v1/search?apikey=5oW9IO9aAIxXo8aZGQiBGpxCILb35AE8&q=" + city;
	  		var request = HttpRequest.newBuilder().GET().uri(URI.create(cityApi)).build();
	  		var client = HttpClient.newBuilder().build();
	  		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
	
	  		try {
	  	        JSONArray jsonArray = new JSONArray(response.body());
	          	Location location = new Location();
	          	JSONObject resObj = jsonArray.getJSONObject(0);
	          	LocationKey = resObj.getString("Key");
	          	location.setKey(LocationKey);
	          	
	        	String localName = resObj.getString("LocalizedName");
	        	location.setLocalName(localName);
	        	
	          	JSONObject region = resObj.getJSONObject("Region");
	          	String regionName = region.getString("LocalizedName");
	          	location.setRegionname(regionName);
	          	
	          	JSONObject geoPos = resObj.getJSONObject("GeoPosition");
	          	BigDecimal lat = geoPos.getBigDecimal("Latitude");
	          	BigDecimal lon = geoPos.getBigDecimal("Longitude");
	          	location.setLat(lat);
	          	location.setLon(lon);
	          	
	          	locationArrayList.add(location);
	  		}catch (JSONException e) {
	  			System.out.println(e);
	  		}
  		
	  		Iterator<Location> it = locationArrayList.iterator();
	  		while(it.hasNext()) {
	  			Location LocationIterator = (Location) it.next();
          		l4.setText(LocationIterator.getRegionname() + "/ " + LocationIterator.getLocalName() + "\n" + LocationIterator.getLat() + " Lat/ " + LocationIterator.getLon() + " Lon");
          		LocationKey = LocationIterator.getKey();
	  		}
  	
	  		var weatherApi = "http://dataservice.accuweather.com/currentconditions/v1/"+LocationKey+"?apikey=5oW9IO9aAIxXo8aZGQiBGpxCILb35AE8&details=true";
	  		var req = HttpRequest.newBuilder().GET().uri(URI.create(weatherApi)).build();
	  		var cli = HttpClient.newBuilder().build();
	  		var resp = cli.send(req, HttpResponse.BodyHandlers.ofString());

	  		try {
		  		  JSONArray jsonArr = new JSONArray(resp.body());
		          Weather weather = new Weather();
		          JSONObject resultsObj = jsonArr.getJSONObject(0);
		
		          String date = resultsObj.getString("LocalObservationDateTime");
		          weather.setDate(date);
		          
		          BigDecimal humidity = resultsObj.getBigDecimal("RelativeHumidity");
		          weather.setHumidity(humidity);
		          
		          JSONObject temperatureObj = resultsObj.getJSONObject("Temperature");
		          BigDecimal temp = temperatureObj.getJSONObject("Metric").getBigDecimal("Value");
		          weather.setTemp(temp);
		          
		          JSONObject temperatureFeltObj = resultsObj.getJSONObject("RealFeelTemperature");
	              BigDecimal tempFelt = temperatureFeltObj.getJSONObject("Metric").getBigDecimal("Value");
	              weather.setTempFelt(tempFelt);
	              
	              JSONObject temperatureSummObj = resultsObj.getJSONObject("TemperatureSummary");
	              BigDecimal minTemperature = temperatureSummObj.getJSONObject("Past24HourRange").getJSONObject("Minimum").getJSONObject("Metric").getBigDecimal("Value");
	              weather.setMinTemp(minTemperature);
	              BigDecimal maxTemperature = temperatureSummObj.getJSONObject("Past24HourRange").getJSONObject("Maximum").getJSONObject("Metric").getBigDecimal("Value");
	              weather.setMaxTemp(maxTemperature);
	              
	              JSONObject windObj = resultsObj.getJSONObject("Wind");
	              BigDecimal windSpeed = windObj.getJSONObject("Speed").getJSONObject("Metric").getBigDecimal("Value");
	              weather.setWindSpeed(windSpeed);
	              
	              JSONObject windGustObj = resultsObj.getJSONObject("WindGust");
	              BigDecimal windgust = windGustObj.getJSONObject("Speed").getJSONObject("Metric").getBigDecimal("Value");
	              weather.setWindGustSpeed(windgust);
	              
	              JSONObject visibilityObj = resultsObj.getJSONObject("Visibility");
	              BigDecimal vis = visibilityObj.getJSONObject("Metric").getBigDecimal("Value");
	              weather.setVisibility(vis);
	              
	              JSONObject pressureObj = resultsObj.getJSONObject("Pressure");
	              BigDecimal pressure = pressureObj.getJSONObject("Metric").getBigDecimal("Value");
	              weather.setAirPressure(pressure);
	              
	              weatherArrayList.add(weather);
	          } catch (JSONException e) {
	          	System.out.println(e);
	          }
	  		  String dateTime = "";
	          Iterator<Weather> itr = weatherArrayList.iterator();
	          while(itr.hasNext()) {
	            Weather weatherInIterator = (Weather) itr.next();
	            l7.setText(weatherInIterator.getTemp().toString() + " °C");
	            l8.setText(weatherInIterator.getMinTemp() + " ~ " + weatherInIterator.getMaxTemp() + " °C");
          		l9.setText("Temperature Felt       " + weatherInIterator.getTempFelt() + " °C");
          		l10.setText("Humidity                 " + weatherInIterator.getHumidity() + " %");
          		l11.setText("Visibility                  " + weatherInIterator.getVisibility() + " km");
          		l12.setText("Wind Speed            " + weatherInIterator.getWindSpeed() + " km/h");
          		l13.setText("WindGust Speed     " + weatherInIterator.getWindGustSpeed() + " km/h");
          		l14.setText("Air Pressure            " + weatherInIterator.getAirPressure() + " mb"); //millibar
          		dateTime = weatherInIterator.getDate();
		      }
	          String[] dt = dateTime.split("T");
	          String[] date = dt[0].split("-");
	          String[] time = dt[1].split(":");
	          
	          LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
	          java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
	          java.time.Month month = localDate.getMonth();
	          String day = dayOfWeek.toString().toLowerCase();
	          String mon = month.toString().toLowerCase();
	          
	          l2.setText(Integer.parseInt(time[0])%12 + " : " + time[1]);
	          l3.setText(((Integer.parseInt(time[0]) >= 12) ? "PM" : "AM"));
	          l5.setText(day.substring(0, 1).toUpperCase()+day.substring(1) + ", " );
	          l6.setText(date[2] + " " + mon.substring(0, 1).toUpperCase() + mon.substring(1));
	  	 }
	      catch(Exception e) {
	      	System.out.println(e);
	      }
    }
	@Override
	public void start(Stage primaryStage) throws Exception{
		l1 = new Label("Enter City : ");
		l1.setStyle("-fx-text-fill : black; -fx-font-weight: bold;-fx-font-family:\"Arial Narrow\";");
		l2 = new Label(); // for time
		l2.setId("time");
		l3 = new Label(); // for AM/ PM
		l3.setStyle("-fx-text-fill : black;");
		l4 = new Label(); // for location - latitude/ longitude
		l5 = new Label(); // for day
		l6 = new Label(); // date
		
		l7 = new Label(); // tempature 
		l7.setId("temp");
		l8 = new Label(); // min tempature - max tempature
		l9 = new Label(); // tempature felt
		l10 = new Label(); // humidity
		l11 = new Label(); // visibility
		l12 = new Label(); // wind
		l13 = new Label(); // windgust
		l14 = new Label(); // air pressure
		 
        tf1 = new TextField();   
        Button search = new Button ("Search");  
        
        GridPane root = new GridPane();  
        root.setAlignment(Pos.TOP_LEFT);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        StackPane stpane = new StackPane();
        Rectangle rect = new Rectangle(50, 50, 350, 300);
        rect.setFill(Color.LIGHTSLATEGRAY);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(2);
        rect.setOpacity(0.5);
        stpane.getChildren().add(rect);
        
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(15);
        hbox.getChildren().add(l9);
        vbox.getChildren().add(hbox);
        hbox = new HBox();
        hbox.getChildren().add(l10);
        vbox.getChildren().add(hbox);
        hbox = new HBox();
        hbox.getChildren().add(l11);
        vbox.getChildren().add(hbox);
        hbox = new HBox();
        hbox.getChildren().add(l12);
        vbox.getChildren().add(hbox);
        hbox = new HBox();
        hbox.getChildren().add(l13);
        vbox.getChildren().add(hbox);
        hbox = new HBox();
        hbox.getChildren().add(l14);
        vbox.getChildren().add(hbox);
        
        stpane.getChildren().add(vbox);
        StackPane.setMargin(vbox, new Insets(20,20,20,20));
        
        Scene scene = new Scene(root,900,600);  
        root.add(l1, 0, 0); root.add(tf1, 1, 0, 2, 1); 
        root.add(search, 2, 1);
        root.add(l2, 0, 2); root.add(l3, 1, 2); root.add(l4, 4, 2);  // time - pm/am - location 
        root.add(l5, 0, 3);	root.add(l6, 1, 3); root.add(l7, 4, 3);  // day date -- tempature
        root.add(l8, 4, 4); 
        root.add(stpane, 0, 5, 2, 1);
        
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Weather Forecasting");
        primaryStage.setScene(scene);  
        primaryStage.show(); 
        
        tf1.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            	setValues();
            }
        });
        search.setOnAction(event -> setValues());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

