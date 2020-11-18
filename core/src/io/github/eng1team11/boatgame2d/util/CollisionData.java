package io.github.eng1team11.boatgame2d.util;

public class CollisionData {
    // Collision
    public boolean collision = false;
    // Left-Right (T-F)
    public boolean lr = false;
    // Top-Bottom (T-F)
    public boolean tb = false;

    public CollisionData() {
    }

    public CollisionData(boolean c, boolean h, boolean v) {
        collision = c;
        lr = h;
        tb = v;
    }

    public static CollisionData None = new CollisionData();
}