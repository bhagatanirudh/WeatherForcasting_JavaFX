module WeatherForcasting_JavaFX {
	requires javafx.controls;
	requires org.json;
	requires java.net.http;
	
	opens application to javafx.graphics, javafx.fxml;
}
