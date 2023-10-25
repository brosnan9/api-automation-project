package googleLocation;

public class payload {

    public static String addLocationBody(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383498,\n" +
                "    \"lng\": 33.427369\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"GersonsOut house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, top layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"dog HOUSE\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"Hindi-IN\"\n" +
                "}";
    }
}
