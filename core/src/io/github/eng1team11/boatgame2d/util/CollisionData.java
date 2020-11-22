package io.github.eng1team11.boatgame2d.util;

public class CollisionData {
    // Static no-collision CollisionData
    public static CollisionData None = new CollisionData();

    ;
    // Collision
    public boolean collision = false;

    ;
    public VerticalType tb = VerticalType.None;
    public HorizontalType lr = HorizontalType.None;

    /**
     * Default c'tor
     */
    public CollisionData() {
    }

    /**
     * C'tor specifying each parameter
     *
     * @param c Whether or not there was a collision
     * @param h What type of collision there was horizontally
     * @param v What type of collision there was vertically
     */
    public CollisionData(boolean c, HorizontalType h, VerticalType v) {
        collision = c;
        lr = h;
        tb = v;
    }

    // Enums for collision location
    public enum VerticalType {Top, Bottom, None}

    public enum HorizontalType {Left, Right, None}
}