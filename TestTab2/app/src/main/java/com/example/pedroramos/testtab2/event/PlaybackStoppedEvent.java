package com.example.pedroramos.testtab2.event;

/**
 * Event will be posted when playback will be stopped.
 */
public class PlaybackStoppedEvent {
    final boolean isMyNoteStopped;

    public PlaybackStoppedEvent(boolean isMyNoteStopped) {
        this.isMyNoteStopped = isMyNoteStopped;
    }

    public boolean isMyNoteStopped() {
        return isMyNoteStopped;
    }
}
