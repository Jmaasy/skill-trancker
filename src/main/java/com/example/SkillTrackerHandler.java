package com.example;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.example.config.SkillTrackerConfig;
import com.example.tracker.CrystalMathLabs;
import com.example.tracker.WiseOldMan;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Player;

@Slf4j
class SkillTrackerHandler {

    private final WiseOldMan wiseOldMan = new WiseOldMan();
    private final CrystalMathLabs crystalMathLabs = new CrystalMathLabs();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Player player;
    private Boolean tracked;

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void resetTracked() {
        this.tracked = false;
    }

    public Future<Boolean> trackPlayerFuture(SkillTrackerConfig config)
    {
        return executor.submit(() -> trackPlayer(config));
    }

    public boolean trackPlayer(SkillTrackerConfig config)
    {
        if(player != null && !tracked) {
            try {
                log.info(String.format("Updating player: %s", player.getName()));
                if(config.wiseOldManCheckbox()) {
                    wiseOldMan.trackPlayer(player);
                }

                if(config.crystalMathLabsCheckbox()) {
                    crystalMathLabs.trackPlayer(player);
                }

                tracked = true;
                return true;
            } catch (IOException e) {
                log.error(e.getMessage());
                return false;
            }
        } else if(tracked) {
            log.info("Player already tracked");
            return true;
        } else {
            log.info("Player not defined");
            return false;
        }
    }
}
