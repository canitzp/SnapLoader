package de.canitzp.snaploader;

public @interface SnapMod {

    String modid();

    String modName() default "";

    String author() default "";

    String version() default "";

    String[] depends() default {};


}
