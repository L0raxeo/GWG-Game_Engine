package com.cotrance.test.scenes;

import com.cotrance.test.renderer.Camera;

public abstract class Scene
{

    protected Camera camera;

    public Scene()
    {

    }

    public void init()
    {

    }

    public abstract void update(float dt);

}
