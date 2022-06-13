package com.cotrance.test.util;

import com.cotrance.test.objects.components.Component;
import com.cotrance.test.objects.GameObject;
import com.cotrance.test.objects.Transform;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GameObjectSerializer implements JsonDeserializer<GameObject>
{

    @Override
    public GameObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        JsonArray components = jsonObject.getAsJsonArray("components");
        Transform transform = context.deserialize(jsonObject.get("transform"), Transform.class);
        int zIndex = context.deserialize(jsonObject.get("zIndex"), int.class);

        GameObject go = new GameObject(name, transform, zIndex);

        for (JsonElement e : components)
        {
            Component c = context.deserialize(e, Component.class);
            go.addComponent(c);
        }

        return go;
    }

}
