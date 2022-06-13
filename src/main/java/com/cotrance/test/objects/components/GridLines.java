package com.cotrance.test.objects.components;

import com.cotrance.test.display.Window;
import com.cotrance.test.renderer.DebugDraw;
import com.cotrance.test.util.Settings;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class GridLines extends Component
{

    @Override
    public void update(float dt)
    {
        Vector2f cameraPos = Window.getScene().camera().position;
        Vector2f projectionSize = Window.getScene().camera().getProjectionSize();

        int firstX = ((int) (cameraPos.x / Settings.GRID_WIDTH) - 1) * Settings.GRID_HEIGHT;
        int firstY = ((int) (cameraPos.y / Settings.GRID_HEIGHT) - 1) * Settings.GRID_HEIGHT;

        // vertical
        int numVtLines = (int) (projectionSize.x / Settings.GRID_WIDTH) + 2;
        // horizontal
        int numHzLines = (int) (projectionSize.y / Settings.GRID_HEIGHT) + 2;

        int width = (int)projectionSize.x + Settings.GRID_WIDTH * 2;
        int height = (int)projectionSize.y + Settings.GRID_HEIGHT * 2;

        int maxLines = Math.max(numVtLines, numHzLines);
        Vector3f color = new Vector3f(0.2f, 0.2f, 0.2f);

        for (int i = 0; i < maxLines; i++)
        {
            int x = firstX + (Settings.GRID_WIDTH * i);
            int y = firstY + (Settings.GRID_HEIGHT * i);

            if (i < numVtLines)
                DebugDraw.addLine2D(new Vector2f(x, firstY), new Vector2f(x, y + height), color);

            if (i < numHzLines)
                DebugDraw.addLine2D(new Vector2f(firstX, y), new Vector2f(firstX + width, y), color);
        }
    }

}
