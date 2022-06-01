package com.cotrance.test.scenes;

import com.cotrance.test.display.Window;

public class LevelScene extends Scene
{

    public LevelScene()
    {
        System.out.println("inside level scene");
        Window.get().r = 1;
        Window.get().g = 1;
        Window.get().b = 1;
    }

    @Override
    public void update(float dt)
    {

    }

}
