package com.prueba.tecnica.chakray.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static String getMadagascarTimestamp() {

        ZoneId zone = ZoneId.of("Indian/Antananarivo");

        LocalDateTime now = LocalDateTime.now(zone);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return now.format(formatter);
    }
}
