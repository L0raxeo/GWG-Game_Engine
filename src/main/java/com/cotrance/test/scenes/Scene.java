package com.cotrance.test.scenes;

import com.cotrance.test.objects.components.Component;
import com.cotrance.test.objects.GameObject;
import com.cotrance.test.renderer.Camera;
import com.cotrance.test.renderer.Renderer;
import com.cotrance.test.util.ComponentSerializer;
import com.cotrance.test.util.GameObjectSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene
{

    protected Renderer renderer = new Renderer();

    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected GameObject activeGameObject = null;
    protected boolean levelLoaded = false;

    public Scene()
    {

    }

    public void init()
    {

    }

    public void start()
    {
        for (GameObject go : gameObjects)
        {
            go.start();
            this.renderer.add(go);
        }

        isRunning = true;
    }

    public void addGameObjectToScene(GameObject go)
    {
        if (!isRunning) gameObjects.add(go);
        else
        {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);
        }
    }

    public abstract void update(float dt);

    public Camera camera()
    {
        return this.camera;
    }

    public void sceneImgui()
    {
        if (activeGameObject != null) {
            ImGui.begin("Inspector");
            activeGameObject.imgui();
            ImGui.end();
        }

        imgui();
    }

    public void imgui()
    {

    }

    public void saveExit()
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentSerializer())
                .registerTypeAdapter(GameObject.class, new GameObjectSerializer())
                .create();

        try
        {
            FileWriter writer = new FileWriter("level.txt");
            writer.write(gson.toJson(this.gameObjects));
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void load()
    {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentSerializer())
                .registerTypeAdapter(GameObject.class, new GameObjectSerializer())
                .create();

        String inFile = "";

        try
        {
            inFile = new String(Files.readAllBytes(Paths.get("level.txt")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (!inFile.equals(""))
        {
            int maxGoId = -1;
            int maxCompId = -1;
            GameObject[] objects = gson.fromJson(inFile, GameObject[].class);

            for (GameObject object : objects)
            {
                addGameObjectToScene(object);

                for (Component c : object.getAllComponents())
                    if (c.getUid() > maxCompId)
                        maxCompId = c.getUid();

                if (object.getUid() > maxGoId)
                    maxGoId = object.getUid();
            }

            maxGoId++;
            maxCompId++;
            GameObject.init(maxGoId);
            Component.init(maxCompId);
            this.levelLoaded = true;
        }
    }

}
