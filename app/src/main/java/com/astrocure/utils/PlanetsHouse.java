package com.astrocure.utils;

import static com.astrocure.utils.AstrologyApiConstants.CAREER_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.HEALTH_HOUSE;
import static com.astrocure.utils.AstrologyApiConstants.LOVE_HOUSE;

import android.content.Context;
import android.util.Log;

import com.astrocure.models.PlanetsResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PlanetsHouse {
    Context context;
    PlanetsResponseModel planetsResponseModel;
    List<String> healthPlanets;
    List<String> careerPlanets;
    List<String> lovePlanets;

    public PlanetsHouse(Context context, PlanetsResponseModel planetsResponseModel) {
        this.context = context;
        this.planetsResponseModel = planetsResponseModel;
        healthPlanets = new ArrayList<>();
        careerPlanets = new ArrayList<>();
        lovePlanets = new ArrayList<>();
    }

    public List<String> getHealthPlanets() {
        return healthPlanets;
    }

    public List<String> getCareerPlanets() {
        return careerPlanets;
    }

    public List<String> getLovePlanets() {
        return lovePlanets;
    }

    private void getPlanetHouse() {
        int ascendant = planetsResponseModel.getOutput().get(1).getAscendant().getCurrentSign();
        List<Integer> location = new ArrayList<>();

        for (int i = 0; i <= 12; i++) {
            if (i == 0) {
                location.add(0);
                Log.i("TAG", "getPlanetHouse: " + i + " " + ascendant);
            } else if (ascendant >= 13) {
                location.add(ascendant - 12);
                Log.i("TAG", "getPlanetHouse: " + i + " " + ascendant);
                ascendant++;
            } else {
                location.add(ascendant);
                Log.i("TAG", "getPlanetHouse: " + i + " " + ascendant);
                ascendant++;
            }
        }
        for (Integer integer : location) {
            Log.i("TAG", "getPlanetHouse: List Data " + integer.toString());
        }
    }

    public int getHouseCurrentSign(int houseNum) {
        int ascendantNum = planetsResponseModel.output.get(1).getAscendant().getCurrentSign();
        List<Integer> location = new ArrayList<>();

        for (int i = 0; i <= 12; i++) {
            if (i == 0) {
                location.add(0);
            } else if (ascendantNum >= 13) {
                location.add(ascendantNum - 12);
                ascendantNum++;
            } else {
                location.add(ascendantNum);
                ascendantNum++;
            }
        }
        int current = location.get(houseNum);
        Log.i("TAG", "getHouseCurrentSign: " + current);

        return current;
    }

    public String getCareerPlanetNum() {
        List<String> numList = new ArrayList<>();
        int currentSign = getHouseCurrentSign(CAREER_HOUSE);
        Log.i("TAG", "getCareerPlanet: " + currentSign);
        if (planetsResponseModel.getOutput().get(1).getSun().getCurrentSign() == currentSign) {
            numList.add("1");
            careerPlanets.add("Sun");
        }
        if (planetsResponseModel.getOutput().get(1).getMoon().getCurrentSign() == currentSign) {
            numList.add("2");
            careerPlanets.add("Moon");
        }
        if (planetsResponseModel.getOutput().get(1).getMars().getCurrentSign() == currentSign) {
            numList.add("3");
            careerPlanets.add("Mars");
        }
        if (planetsResponseModel.getOutput().get(1).getMercury().getCurrentSign() == currentSign) {
            numList.add("4");
            careerPlanets.add("Mercury");
        }
        if (planetsResponseModel.getOutput().get(1).getJupiter().getCurrentSign() == currentSign) {
            numList.add("5");
            careerPlanets.add("Jupiter");
        }
        if (planetsResponseModel.getOutput().get(1).getVenus().getCurrentSign() == currentSign) {
            numList.add("6");
            careerPlanets.add("Venus");
        }
        if (planetsResponseModel.getOutput().get(1).getSaturn().getCurrentSign() == currentSign) {
            numList.add("7");
            careerPlanets.add("Saturn");
        }
        if (planetsResponseModel.getOutput().get(1).getRahu().getCurrentSign() == currentSign) {
            numList.add("8");
            careerPlanets.add("Rahu");
        }
        if (planetsResponseModel.getOutput().get(1).getKetu().getCurrentSign() == currentSign) {
            numList.add("9");
            careerPlanets.add("Ketu");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String num : numList) {
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public String getHealthPlanetNum() {
        int currentSign = getHouseCurrentSign(HEALTH_HOUSE);
        Log.i("TAG", "getHealthPlanet: " + currentSign);
        if (planetsResponseModel.getOutput().get(1).getSun().getCurrentSign() == currentSign) {
            healthPlanets.add("Sun");
        }
        if (planetsResponseModel.getOutput().get(1).getMoon().getCurrentSign() == currentSign) {
            healthPlanets.add("Moon");
        }
        if (planetsResponseModel.getOutput().get(1).getMars().getCurrentSign() == currentSign) {
            healthPlanets.add("Mars");
        }
        if (planetsResponseModel.getOutput().get(1).getMercury().getCurrentSign() == currentSign) {
            healthPlanets.add("Mercury");
        }
        if (planetsResponseModel.getOutput().get(1).getJupiter().getCurrentSign() == currentSign) {
            healthPlanets.add("Jupiter");
        }
        if (planetsResponseModel.getOutput().get(1).getVenus().getCurrentSign() == currentSign) {
            healthPlanets.add("Venus");
        }
        if (planetsResponseModel.getOutput().get(1).getSaturn().getCurrentSign() == currentSign) {
            healthPlanets.add("Saturn");
        }
        if (planetsResponseModel.getOutput().get(1).getRahu().getCurrentSign() == currentSign) {
            healthPlanets.add("Rahu");
        }
        if (planetsResponseModel.getOutput().get(1).getKetu().getCurrentSign() == currentSign) {
            healthPlanets.add("Ketu");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String num : healthPlanets) {
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public String getZodiacSign() {


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("d-MM");
            String zodiac = null;
            Date inputDate = sdf.parse(planetsResponseModel.getInput().getDate() + "-" + planetsResponseModel.getInput().getMonth());
            if (inputDate.after(sdf.parse("21-3")) && inputDate.before(sdf.parse("19-4"))) {
                zodiac = "Aries";

            } else if (inputDate.after(sdf.parse("20-4")) && inputDate.before(sdf.parse("20-4"))) {
                zodiac = "Taurus";

            } else if (inputDate.after(sdf.parse("21-5")) && inputDate.before(sdf.parse("21-6"))) {
                zodiac = "Gemini";

            } else if (inputDate.after(sdf.parse("22-6")) && inputDate.before(sdf.parse("22-7"))) {
                zodiac = "Cancer";

            } else if (inputDate.after(sdf.parse("23-7")) && inputDate.before(sdf.parse("22-8"))) {
                zodiac = "Leo";

            } else if (inputDate.after(sdf.parse("23-8")) && inputDate.before(sdf.parse("22-9"))) {
                zodiac = "Virgo";

            } else if (inputDate.after(sdf.parse("23-9")) && inputDate.before(sdf.parse("23-10"))) {
                zodiac = "Libra";

            } else if (inputDate.after(sdf.parse("24-10")) && inputDate.before(sdf.parse("22-11"))) {
                zodiac = "Scorpio";

            } else if (inputDate.after(sdf.parse("23-11")) && inputDate.before(sdf.parse("21-12"))) {
                zodiac = "Sagittarius";
            } else if ((inputDate.after(sdf.parse("22-12")) && inputDate.before(sdf.parse("31-12"))) || inputDate.after(sdf.parse("0-1")) && inputDate.before(sdf.parse("19-1"))) {
                zodiac = "Capricorn";
            } else if (inputDate.after(sdf.parse("20-1")) && inputDate.before(sdf.parse("18-2"))) {
                zodiac = "Aquarius";
            } else if (inputDate.after(sdf.parse("19-2")) && inputDate.before(sdf.parse("20-3"))) {
                zodiac = "Pisces";
            }
            return zodiac;
        } catch (ParseException e) {
            throw new RuntimeException(e);

        }
    }

    public String getLovePlanetNum() {
        List<String> numList = new ArrayList<>();
        int currentSign = getHouseCurrentSign(LOVE_HOUSE);
        if (planetsResponseModel.getOutput().get(1).getSun().getCurrentSign() == currentSign) {
            lovePlanets.add("Sun");
            numList.add("1");
        }
        if (planetsResponseModel.getOutput().get(1).getMoon().getCurrentSign() == currentSign) {
            lovePlanets.add("Moon");
            numList.add("2");
        }
        if (planetsResponseModel.getOutput().get(1).getMars().getCurrentSign() == currentSign) {
            lovePlanets.add("Mars");
            numList.add("3");
        }
        if (planetsResponseModel.getOutput().get(1).getMercury().getCurrentSign() == currentSign) {
            lovePlanets.add("Mercury");
            numList.add("4");
        }
        if (planetsResponseModel.getOutput().get(1).getJupiter().getCurrentSign() == currentSign) {
            lovePlanets.add("Jupiter");
            numList.add("5");
        }
        if (planetsResponseModel.getOutput().get(1).getVenus().getCurrentSign() == currentSign) {
            lovePlanets.add("Venus");
            numList.add("6");
        }
        if (planetsResponseModel.getOutput().get(1).getSaturn().getCurrentSign() == currentSign) {
            lovePlanets.add("Saturn");
            numList.add("7");
        }
        if (planetsResponseModel.getOutput().get(1).getRahu().getCurrentSign() == currentSign) {
            lovePlanets.add("Rahu");
            numList.add("8");
        }
        if (planetsResponseModel.getOutput().get(1).getKetu().getCurrentSign() == currentSign) {
            lovePlanets.add("Ketu");
            numList.add("9");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String num : numList) {
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public String getPercent(int houseNum) {
        List<String> planets = null;
        String objName = null;
        if (houseNum == LOVE_HOUSE) {
            planets = getLovePlanets();
            objName = "love_planet_effect";
        } else if (houseNum == CAREER_HOUSE) {
            planets = getCareerPlanets();
            objName = "career_planet_effect";
        } else if (houseNum == HEALTH_HOUSE) {
            planets = getHealthPlanets();
            objName = "health_planet_effect";
        }


        double totalScore = 100;
        int max, min, finalScore;
        if (planets.isEmpty()) {
            totalScore = 50;
        } else {
            double singleValue = 100 / planets.size();
            for (String planet : planets) {
                try {
                    JSONObject jsonObject = new JSONObject(AppConstantMethods.loadJSONFromAssets(context, "Predictions.json"));
                    JSONObject jObj = jsonObject.getJSONObject(objName);
                    if (jObj.get(planet).toString().matches("1")) {
                        totalScore = totalScore - singleValue;
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        max = (int) (totalScore + 3);
        min = (int) (totalScore - 3);
        finalScore = new Random().nextInt(max - min + 1) + min;
        return String.valueOf(finalScore);
    }
}
