package dev.phonis.horseinfomod.render;

public
class EntityRenderController
{

    private static boolean shouldRenderNameTags = true;

    public static boolean shouldRenderNameTags()
    {
        return EntityRenderController.shouldRenderNameTags;
    }

    public static void withNameTagsOff(Runnable runnable)
    {
        EntityRenderController.shouldRenderNameTags = false;
        runnable.run();
        EntityRenderController.shouldRenderNameTags = true;
    }

}
