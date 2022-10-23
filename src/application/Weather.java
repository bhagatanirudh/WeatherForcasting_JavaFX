package application;

import java.math.BigDecimal;

public class Weather {
	String dateTime, uv;
    BigDecimal temp, tempFelt, minTemp, maxTemp;
    BigDecimal humidity, visibility, airPressure;
    BigDecimal windSpeed, windGustSpeed;
    
	public String getDate() {
		return dateTime;
	}
	public void setDate(String date) {
		this.dateTime = date;
	}
	public String getUv() {
		return uv;
	}
	public void setUv(String uv) {
		this.uv = uv;
	}
	public BigDecimal getTemp() {
		return temp;
	}
	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}
	public BigDecimal getTempFelt() {
		return tempFelt;
	}
	public void setTempFelt(BigDecimal tempFelt) {
		this.tempFelt = tempFelt;
	}
	public BigDecimal getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(BigDecimal minTemp) {
		this.minTemp = minTemp;
	}
	public BigDecimal getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(BigDecimal maxTemp) {
		this.maxTemp = maxTemp;
	}
	public BigDecimal getHumidity() {
		return humidity;
	}
	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}
	public BigDecimal getVisibility() {
		return visibility;
	}
	public void setVisibility(BigDecimal visibility) {
		this.visibility = visibility;
	}
	public BigDecimal getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(BigDecimal airPressure) {
		this.airPressure = airPressure;
	}
	public BigDecimal getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(BigDecimal windSpeed) {
		this.windSpeed = windSpeed;
	}
	public BigDecimal getWindGustSpeed() {
		return windGustSpeed;
	}
	public void setWindGustSpeed(BigDecimal windGustSpeed) {
		this.windGustSpeed = windGustSpeed;
	}
}
