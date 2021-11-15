package com.laxtiz.game;

import arc.Events;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.gen.Groups;
import mindustry.mod.Plugin;

import static arc.util.Log.*;

@SuppressWarnings("unused")
public class AutoPausePlugin extends Plugin {
    @Override
    public void init() {
        Events.on(PlayerJoin.class, e -> {
            var num = Groups.player.size();
            if (num > 0 && Vars.state.serverPaused) {
                Vars.state.serverPaused = false;
                warn("some player joined, game running.");
            }
        });

        Events.on(PlayerLeave.class, e -> {
            var num = Groups.player.size();
            if (num == 1) {
                Vars.state.serverPaused = true;
                warn("the last player leaving, game will paused.");
            }
        });
    }
}