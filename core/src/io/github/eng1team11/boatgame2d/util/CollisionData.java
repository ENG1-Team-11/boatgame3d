package io.github.eng1team11.boatgame2d.util;

public class CollisionData {
    // Enums for collision location
    public enum VerticalType { Top, Bottom, None };
    public enum HorizontalType { Left, Right, None };

    // Collision
    public boolean collision = false;
    public VerticalType tb = VerticalType.None;
    public HorizontalType lr = HorizontalType.None;

    public CollisionData() {
    }

    public CollisionData(boolean c, HorizontalType h, VerticalType v) {
        collision = c;
        lr = h;
        tb = v;
    }

    public static CollisionData None = new CollisionData();
}