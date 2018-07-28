
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {


        ArrayList<WeatherInfo> weatherInfoList = WeatherInfoArrayListIO.citiesFileToArray("cities.txt");

        for (int i = 0; i < weatherInfoList.size(); i++) {
            String info = weatherInfoList.get(i).toString();
            System.out.println(info);
        }

        WeatherInfoArrayListIO.ArrayListToCsvFile("weatherInfo.csv", weatherInfoList);

    }
}
