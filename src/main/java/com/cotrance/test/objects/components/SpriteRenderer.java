package com.cotrance.test.objects.components;

import com.cotrance.test.objects.Component;
import com.cotrance.test.objects.Transform;
import com.cotrance.test.renderer.Texture;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component
{

    private Vector4f color = new Vector4f(1, 1, 1, 1);
    private Sprite sprite = new Sprite();

    private transient Transform lastTransform;
    private transient boolean isDirty = true;

//    public SpriteRenderer(Vector4f color)
//    {
//        this.color = color;
//        this.sprite = new Sprite(null);
//        this.setDirty();
//    }
//
//    public SpriteRenderer(Sprite sprite)
//    {
//        this.sprite = sprite;
//        this.color = new Vector4f(1, 1, 1, 1);
//        this.setDirty();
//    }

    @Override
    public void start()
    {
        this.lastTransform = gameObject.transform.copy();
    }

    public void update(float dt)
    {
        if (!this.lastTransform.equals(this.gameObject.transform))
        {
            this.gameObject.transform.copy(this.lastTransform);
            setDirty();
        }
    }

    @Override
    public void imgui()
    {
        float[] imColor = {color.x, color.y, color.z, color.w};
        if (ImGui.colorPicker4("Color Picker: ", imColor))
        {
            this.color.set(imColor[0], imColor[1], imColor[2], imColor[3]);
            this.setDirty();
        }
    }

    public Vector4f getColor()
    {
        return this.color;
    }

    public Texture getTexture()
    {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords()
    {
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
        this.setDirty();
    }

    public void setColor(Vector4f color)
    {
        if (!this.color.equals(color))
        {
            this.setDirty();
            this.color.set(color);
        }
    }

    public boolean isDirty()
    {
        return this.isDirty;
    }

    /**
     * Sets isDirty boolean to true, queuing it to be re-rendered.
     */
    public void setDirty()
    {
        this.isDirty = true;
    }

    /**
     * Sets isDirty boolean to false.
     */
    public void setClean()
    {
        this.isDirty = false;
    }

}
