package com.example.demo.model.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FixedAssetClassification {

    LAND("grunty"),
    BUILDINGS_DWELLING("budynki i lokale oraz spółdzielcze prawo do lokalu użytkowego i spółdzielcze własnościowe prawo do lokalu mieszkalnego"),
    CIVIL_ENGINEER_STRUCTURE("obiekty inżynierii lądowej i wodnej"),
    BOILER_POWER_MACHINERY("kotły i maszyny energetyczne"),
    SPECIALIZED_MACHINERY_EQUIPMENT_GENERAL_USE("maszyny, urządzenia i aparaty ogólnego zastosowania"),
    SPECIALIZED_MACHINERY_EQUIPMENT_APPARATUS("maszyny, urządzenia i aparaty specjalistyczne"),
    TECHNICAL_EQUIPMENT("urządzenia technicznie"),
    TRANSPORT("środki transportu"),
    ANOTHER("narzędzia, przyrządy, ruchomości i wyposażenie, gdzie indziej niesklasyfikowane"),
    LIVESTOCK("inwentarz żywy");


    private String description;
}
