package com.example.tracker;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Player;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class CrystalMathLabs {

    private static final String URL = "https://crystalmathlabs.com/tracker/api.php?type=update?player=%s";

    public void trackPlayer(Player player) throws IOException
    {
        URL url = new URL(String.format(URL, player.getName()));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);

        log.info(String.format("Tracking player CrystalMathLabs response: %s", conn.getResponseCode()));
    }
}
