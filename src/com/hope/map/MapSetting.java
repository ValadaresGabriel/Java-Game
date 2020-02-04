package com.hope.map;

public class MapSetting {

    public String name;

    public MapSetting(String name) {
        setName(name);
    }

    private void setName(String name) {
        switch (name) {
            case "/Prontera.png" -> this.name = "Prontera";
            case "/Map2.png" -> this.name = "Shit";
        }
    }

}
