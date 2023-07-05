package com.astrocure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PredictionResponseModel {

    @SerializedName("status")
    @Expose
    public int Status;
    @SerializedName("response")
    @Expose
    public Response response;

    public static class Response {

        @SerializedName("lucky_color")
        @Expose
        public String luckyColor;
        @SerializedName("lucky_color_code")
        @Expose
        public String luckyColorCode;
        @SerializedName("lucky_number")
        @Expose
        public List<Integer> luckyNumber;
        @SerializedName("bot_response")
        @Expose
        public BotResponse botResponse;
        @SerializedName("zodiac")
        @Expose
        public String zodiac;

        public static class BotResponse {
            @SerializedName("physique")
            @Expose
            public Physique physique;
            @SerializedName("status")
            @Expose
            public Status status;
            @SerializedName("finances")
            @Expose
            public Finances finances;
            @SerializedName("relationship")
            @Expose
            public Relationship relationship;
            @SerializedName("career")
            @Expose
            public Career career;
            @SerializedName("travel")
            @Expose
            public Travel travel;
            @SerializedName("family")
            @Expose
            public Family family;
            @SerializedName("friends")
            @Expose
            public Friends friends;
            @SerializedName("health")
            @Expose
            public Health health;
            @SerializedName("total_score")
            @Expose
            public TotalScore totalScore;

            public static class Physique {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Status {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Finances {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Relationship {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Career {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Travel {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Family {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Friends {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class Health {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }

            public static class TotalScore {
                @SerializedName("score")
                @Expose
                public Integer score;
                @SerializedName("split_response")
                @Expose
                public String splitResponse;
            }
        }

    }

}
