package resources;

import pojo.googleLocationPayload;
import pojo.location;

import java.util.ArrayList;

public class TestDataBuild {
    public googleLocationPayload addPlacePayload(String name, String language, String address){

        googleLocationPayload gl = new googleLocationPayload();;
        location l = new location();;

        gl.setAccuracy(50);
        gl.setName(name);
        gl.setPhone_number("+91976927");
        gl.setAddress(address);
        gl.setWebsite("www.google.com");
        gl.setLanguage(language);

        l.setLat(-38.383494);
        l.setLng(33.427362);
        gl.setLocation(l);

        ArrayList list = new ArrayList<>();
        list.add("Shoe Shop");
        list.add("Park");
        gl.setTypes(list);

        return gl;
    }
}
