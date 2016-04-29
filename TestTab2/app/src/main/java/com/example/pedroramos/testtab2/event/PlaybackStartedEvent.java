package com.example.pedroramos.testtab2.event;

/**
 * Event will be posted when some component will start playing new note. All receivers should
 * stop any other playbacks.
 */
public class PlaybackStartedEvent {
    final boolean isMyNotePlayed;
    final Object postingObject;

    public PlaybackStartedEvent(boolean isMyNotePlayed, Object postingObject) {
        this.isMyNotePlayed = isMyNotePlayed;
        this.postingObject = postingObject;
    }

    public boolean isMyNotePlayed() {
        return isMyNotePlayed;
    }

    public Object getPostingObject() {
        return postingObject;
    }
}
