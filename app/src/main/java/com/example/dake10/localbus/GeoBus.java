package com.example.dake10.localbus;



import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

@ParseClassName("GeoBus")
public class GeoBus extends ParseObject {
    int registro;
    String rootName;
    ParseGeoPoint geoPoint;
    public GeoBus(String rootName, int registro, ParseGeoPoint geoPoint) {
        this.rootName = rootName;
        this.registro = registro;
        this.geoPoint = geoPoint;
    }

    public GeoBus(){
    }

    public int getRegistro() {
        return getInt("registro");
    }

    public void setRegistro(int registro) {
        put("registro", registro);
    }

    public String getRootName() {
        return getString("rootName");
    }

    public void setRootName(String rootName) {
       put("rootName",rootName);
    }

    public ParseGeoPoint  getParseGeoPoint(){

        return getParseGeoPoint("dondeEsta");
    }

    public void setParseGeoPoint(ParseGeoPoint geoPoint){
        put("dondeEsta",geoPoint );
    }
}
