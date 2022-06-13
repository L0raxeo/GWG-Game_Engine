package com.cotrance.test.renderer;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Line2D
{

    private Vector2f pointA;
    private Vector2f pointB;
    private Vector3f color;
    private int lifetime;

    public Line2D(Vector2f pointA, Vector2f pointB, Vector3f color, int lifetime) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.color = color;
        this.lifetime = lifetime;
    }

    /**
     * Binding lifetime to framerate
     */
    public int beginFrame()
    {
        this.lifetime--;
        return this.lifetime;
    }

    public Vector2f getPointA()
    {
        return this.pointA;
    }

    public Vector2f getPointB()
    {
        return this.pointB;
    }

    public Vector3f getColor()
    {
        return this.color;
    }

}
