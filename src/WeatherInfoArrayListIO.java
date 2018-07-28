import java.io.*;
import java.util.ArrayList;

public class WeatherInfoArrayListIO {

//    WeatherInfo weatherInfo;

    public static ArrayList<WeatherInfo> citiesFileToArray(String fileName) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bfr = new BufferedReader(fileReader);

        ArrayList<WeatherInfo> weatherInfo = new ArrayList<>();
        String city;

        try {
            do {
                city = bfr.readLine();
                if (city != null) {
                    WeatherApi weatherApi = new WeatherApi();
                    String description = weatherApi.getDescription(city);
                    int temperature = weatherApi.getTemperature(city);
                    WeatherInfo local = new WeatherInfo(city, description, temperature);
                    weatherInfo.add(local);
                }
            } while (city != null);
            bfr.close();
        } catch (IOException e) {
            System.err.println("błąd odczytu danych z serwisu sinoptic.pl");
            e.printStackTrace();
        }
        return weatherInfo;
    }

    public static void ArrayListToCsvFile (String fileName, ArrayList<WeatherInfo> weatherInfoList) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            System.err.println("Bład przy tworzenu pliku");
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String fileLine;
            for (int i = 0; i < weatherInfoList.size(); i++) {
                fileLine = weatherInfoList.get(i).getCity() + ";"
                        + weatherInfoList.get(i).getTemperature() + ";"
                        + weatherInfoList.get(i).getDescription();
                bufferedWriter.write(fileLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("błąd zapisu danych do pliku" + fileName);
            e.printStackTrace();
        }

    }
}
