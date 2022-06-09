package com.cotrance.test.scenes;

import com.cotrance.test.objects.GameObject;
import com.cotrance.test.objects.Transform;
import com.cotrance.test.objects.components.Sprite;
import com.cotrance.test.objects.components.SpriteRenderer;
import com.cotrance.test.objects.components.SpriteSheet;
import com.cotrance.test.renderer.Camera;
import com.cotrance.test.util.AssetPool;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class LevelEditorScene extends Scene
{

    private GameObject obj1;
    private SpriteSheet sprites;

    public LevelEditorScene()
    {

    }

    @Override
    public void init()
    {
        loadResources();

        this.camera = new Camera(new Vector2f(-250, 0));

        sprites = AssetPool.getSpriteSheet("assets/images/spritesheet.png");

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100),
                new Vector2f(256, 256)), 0);
        obj1.addComponent(new SpriteRenderer(new Vector4f(1, 0, 0, 1)));
        this.addGameObjectToScene(obj1);
        this.activeGameObject = obj1;

        GameObject obj2 = new GameObject("Object 2",
                new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 0);
        obj2.addComponent(new SpriteRenderer(new Sprite(
                AssetPool.getTexture("assets/images/blendImage2.png")
        )));
        this.addGameObjectToScene(obj2);
    }

    private void loadResources()
    {
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpriteSheet("assets/images/spritesheet.png",
                new SpriteSheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0));
    }

    @Override
    public void update(float dt)
    {
        for (GameObject go : this.gameObjects)
        {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui()
    {
        ImGui.begin("Test Window");
        ImGui.text("Test Text");
        ImGui.end();
    }

}
