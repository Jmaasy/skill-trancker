package com.example.tracker;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Player;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class WiseOldMan {

    private static final String URL = "https://api.wiseoldman.net/players/track";

    public void trackPlayer(Player player) throws IOException
    {
        URL url = new URL(String.format(URL, player.getName()));
        String playerNameNonNull = Objects.requireNonNull(player.getName());
        String playerName = String.format("username=%s", URLEncoder.encode(playerNameNonNull, StandardCharsets.UTF_8));
        byte[] postDataBytes = playerName.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        log.info(String.format("Tracking player WiseOldMan response: %s", conn.getResponseCode()));
    }
}
