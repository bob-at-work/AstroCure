package com.astrocure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanetsResponseModel {
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("input")
    @Expose
    public Input input;
    @SerializedName("output")
    @Expose
    public List<Output> output;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public List<Output> getOutput() {
        return output;
    }

    public void setOutput(List<Output> output) {
        this.output = output;
    }

    public static class Input {

        @SerializedName("year")
        @Expose
        public Integer year;
        @SerializedName("month")
        @Expose
        public Integer month;
        @SerializedName("date")
        @Expose
        public Integer date;
        @SerializedName("hours")
        @Expose
        public Integer hours;
        @SerializedName("minutes")
        @Expose
        public Integer minutes;
        @SerializedName("seconds")
        @Expose
        public Integer seconds;
        @SerializedName("latitude")
        @Expose
        public Float latitude;
        @SerializedName("longitude")
        @Expose
        public Float longitude;
        @SerializedName("timezone")
        @Expose
        public Float timezone;
        @SerializedName("settings")
        @Expose
        public Settings settings;

        public static class Settings {

            @SerializedName("observation_point")
            @Expose
            public String observationPoint;
            @SerializedName("ayanamsha")
            @Expose
            public String ayanamsha;

        }

        public Integer getMonth() {
            return month;
        }

        public Integer getDate() {
            return date;
        }

        public Integer getYear() {
            return year;
        }
    }

    public static class Output {
        @SerializedName("Ascendant")
        @Expose
        public Ascendant ascendant;
        @SerializedName("Sun")
        @Expose
        public Sun sun;
        @SerializedName("Moon")
        @Expose
        public Moon moon;
        @SerializedName("Mars")
        @Expose
        public Mars mars;
        @SerializedName("Mercury")
        @Expose
        public Mercury mercury;
        @SerializedName("Jupiter")
        @Expose
        public Jupiter jupiter;
        @SerializedName("Venus")
        @Expose
        public Venus venus;
        @SerializedName("Saturn")
        @Expose
        public Saturn saturn;
        @SerializedName("Rahu")
        @Expose
        public Rahu rahu;
        @SerializedName("Ketu")
        @Expose
        public Ketu ketu;
        @SerializedName("Uranus")
        @Expose
        public Uranus uranus;
        @SerializedName("Neptune")
        @Expose
        public Neptune neptune;
        @SerializedName("Pluto")
        @Expose
        public Pluto pluto;

        public class Ascendant {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Sun {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }

        }

        public class Moon {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }

        }

        public class Mars {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }

        }

        public class Mercury {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Jupiter {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Venus {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Saturn {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Rahu {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Ketu {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Uranus {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Neptune {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public class Pluto {

            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }
        }

        public Ascendant getAscendant() {
            return ascendant;
        }

        public void setAscendant(Ascendant ascendant) {
            this.ascendant = ascendant;
        }

        public Sun getSun() {
            return sun;
        }

        public void setSun(Sun sun) {
            this.sun = sun;
        }

        public Moon getMoon() {
            return moon;
        }

        public void setMoon(Moon moon) {
            this.moon = moon;
        }

        public Mars getMars() {
            return mars;
        }

        public void setMars(Mars mars) {
            this.mars = mars;
        }

        public Mercury getMercury() {
            return mercury;
        }

        public void setMercury(Mercury mercury) {
            this.mercury = mercury;
        }

        public Jupiter getJupiter() {
            return jupiter;
        }

        public void setJupiter(Jupiter jupiter) {
            this.jupiter = jupiter;
        }

        public Venus getVenus() {
            return venus;
        }

        public void setVenus(Venus venus) {
            this.venus = venus;
        }

        public Saturn getSaturn() {
            return saturn;
        }

        public void setSaturn(Saturn saturn) {
            this.saturn = saturn;
        }

        public Rahu getRahu() {
            return rahu;
        }

        public void setRahu(Rahu rahu) {
            this.rahu = rahu;
        }

        public Ketu getKetu() {
            return ketu;
        }

        public void setKetu(Ketu ketu) {
            this.ketu = ketu;
        }

        public Uranus getUranus() {
            return uranus;
        }

        public void setUranus(Uranus uranus) {
            this.uranus = uranus;
        }

        public Neptune getNeptune() {
            return neptune;
        }

        public void setNeptune(Neptune neptune) {
            this.neptune = neptune;
        }

        public Pluto getPluto() {
            return pluto;
        }

        public void setPluto(Pluto pluto) {
            this.pluto = pluto;
        }
        /*List<Planet> planets;

        public List<Planet> getPlanets() {
            return planets;
        }

        public void setPlanets(List<Planet> planets) {
            this.planets = planets;
        }

        public static class Planet{
            @SerializedName("current_sign")
            @Expose
            public Integer currentSign;
            @SerializedName("fullDegree")
            @Expose
            public Float fullDegree;
            @SerializedName("normDegree")
            @Expose
            public Float normDegree;
            @SerializedName("isRetro")
            @Expose
            public String isRetro;

            public Integer getCurrentSign() {
                return currentSign;
            }

            public void setCurrentSign(Integer currentSign) {
                this.currentSign = currentSign;
            }

            public Float getFullDegree() {
                return fullDegree;
            }

            public void setFullDegree(Float fullDegree) {
                this.fullDegree = fullDegree;
            }

            public Float getNormDegree() {
                return normDegree;
            }

            public void setNormDegree(Float normDegree) {
                this.normDegree = normDegree;
            }

            public String getIsRetro() {
                return isRetro;
            }

            public void setIsRetro(String isRetro) {
                this.isRetro = isRetro;
            }
        }*/
    }
}
