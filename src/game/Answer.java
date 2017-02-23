package game;

public enum Answer {
    // Go in descending priority.
    // For example FINISHED is returned, not KILLED or HIT.
    MISS_AGAIN,
    MISS,
    FINISHED,
    KILLED,
    HIT_AGAIN,
    HIT,
}
