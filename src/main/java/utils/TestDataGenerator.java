package utils;

import java.text.DecimalFormat;
import java.util.Random;

public class TestDataGenerator {

    private final Random R = new Random();

    public String getRandomRegNo() {
        String regNo = "";
        for (int i = 0; i < 2; i++) {
            regNo += (char) (R.nextInt(26) + 'A');
        }
        for (int i = 0; i < 5; i++) {
            regNo += R.nextInt(10);
        }
        return regNo;
    }

    public double getRandomPrice() {
        return Double.parseDouble(new DecimalFormat("#.##").format((R.nextDouble() * 300.0) + 200.0));
    }

    public String getRandomManufactor() {
        String[] manufacturers = {"Audi", "BMW", "Chevrolet", "Citroen", "Fiat", "Ford", "Honda", "Hyundai", "Kia", "Mazda", "Mercedes-Benz", "Mitsubishi", "Nissan", "Peugeot", "Renault", "Suzuki", "Toyota", "Volkswagen", "Volvo"};
        return manufacturers[R.nextInt(manufacturers.length - 1)];
    }

    public String getRandomModel(String manufactor) {
        switch (manufactor) {
            case "Audi":
                String[] models1 = {"A8", "A7", "A6", "A5", "A4"};
                return models1[R.nextInt(models1.length - 1)];
            case "BMW":
                String[] models2 = {"G32 6 Series", "G30 5 Series", "G11 7 Series", "F22 2 Series", "F32 4 Series"};
                return models2[R.nextInt(models2.length - 1)];
            case "Chevrolet":
                String[] models3 = {"Colorado", "Silverado 1500", "Silverado 2500HD", "Silverado 3500HD"};
                return models3[R.nextInt(models3.length - 1)];
            case "Citroen":
                String[] models4 = {"C3", "C4 Cactus", "DS4", "DS5", "C-Zero"};
                return models4[R.nextInt(models4.length - 1)];
            case "Fiat":
                String[] models5 = {"Punto", "Panda", "Bravo", "Sedici"};
                return models5[R.nextInt(models5.length - 1)];
            case "Ford":
                String[] models6 = {"Ecosport", "Kuga", "Edge", "Explorer", "	Expedition"};
                return models6[R.nextInt(models6.length - 1)];
            case "Honda":
                String[] models7 = {"HR-V", "CR-V", "Pilot", "Passport"};
                return models7[R.nextInt(models7.length - 1)];
            case "Hyundai":
                String[] models8 = {"Palisae", "Grand Santa Fe", "Tucson", "Creata", "Kona"};
                return models8[R.nextInt(models8.length - 1)];
            case "Kia":
                String[] models9 = {"Rio", "Forte", "Optima", "Stinger", "Cadenza"};
                return models9[R.nextInt(models9.length - 1)];
            case "Mazda":
                String[] models10 = {"MAZDA3", "MAZDA6"};
                return models10[R.nextInt(models10.length - 1)];
            case "Mercedes-Benz":
                String[] models11 = {"AMG GT", "S-Class", "CLS", "E-Class", "C-Class"};
                return models11[R.nextInt(models11.length - 1)];
            case "Mitsubishi":
                String[] models12 = {"Eclipse Cross", "Pajero Sport", "Outlander", "ASX", "Outlander Phev"};
                return models12[R.nextInt(models12.length - 1)];
            case "Nissan":
                String[] models13 = {"370Z", "370Z Nizmo", "GT-R", "GT-R Nizmo"};
                return models13[R.nextInt(models13.length - 1)];
            case "Peugeot":
                String[] models14 = {"108", "208", "308"};
                return models14[R.nextInt(models14.length - 1)];
            case "Renault":
                String[] models15 = {"Fluence", "Latitude", "Logan", "Megane", "Scala"};
                return models15[R.nextInt(models15.length - 1)];
            case "Suzuki":
                String[] models16 = {"Baleno", "Swift", "Swift Sport", "Celerio"};
                return models16[R.nextInt(models16.length - 1)];
            case "Toyota":
                String[] models17 = {"C-HR", "Rav4", "Highlander", "4Runner", "Sequoia"};
                return models17[R.nextInt(models17.length - 1)];
            case "Volkswagen":
                String[] models18 = {"Jetta", "Jetta GLI", "Passat", "Arteon"};
                return models18[R.nextInt(models18.length - 1)];
            case "Volvo":
                String[] models19 = {"XC90", "XC60", "XC40"};
                return models19[R.nextInt(models19.length - 1)];
            default:
                return "BumbleBee";
        }
    }

    public String getType(String manufactor) {
        switch (manufactor) {
            case "Audi":
                return "Sedan";
            case "BMW":
                return "Sedan";
            case "Volkswagen":
                return "Sedan";
            case "Renault":
                return "Sedan";
            case "Mazda":
                return "Sedan";
            case "Kia":
                return "Sedan";
            case "Chevrolet":
                return "Pick-Up";
            case "Citroen":
                return "Hatchback";
            case "Fiat":
                return "Hatchback";
            case "Peugeot":
                return "Hatchback";
            case "Suzuki":
                return "Hatchback";
            case "Ford":
                return "SUV";
            case "Honda":
                return "SUV";
            case "Hyundai":
                return "SUV";
            case "Mitsubishi":
                return "SUV";
            case "Toyota":
                return "SUV";
            case "Volvo":
                return "SUV";
            case "Nissan":
                return "Coupe";
            case "Mercedes-Benz":
                return "Coupe";
            default:
                return "Transformer";
        }
    }

    public int getRandomYear() {
        return R.nextInt(19) + 2000;
    }

    public int getRandomDist() {
        return R.nextInt(90000) + 10000;
    }

    public String[] getRandomPlace() {
        String[] lng = {"12.6509822", "13.371700000000033", "12.127899999999954"};
        String[] lat = {"55.6091282", "55.5355", "55.587"};
        String[] adr = {"Copenhagen Airport, Tårnby Kommune, Denmark", "Malmö Airport, Skåne län, Sweden", "Københavns Lufthavn, Roskilde, Denmark"};
        int n = R.nextInt(adr.length - 1);
        String[] place = {lng[n], lat[n], adr[n]};
        return place;

    }
}
