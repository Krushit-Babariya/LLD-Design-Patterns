package PrototypeDesignPattern;

public class PrototypePatternDemo {
    public static void main(String[] args) {
        PaletteRegistry registry = new PaletteRegistry();

        ColorSwatch forest = new ColorSwatch("Evergreen", 34, 85, 52);
        forest.addTag("calming");
        forest.addTag("outdoor");

        ColorSwatch sunset = new ColorSwatch("Sunset Glow", 255, 94, 77);
        sunset.addTag("warm");

        registry.register("forest", forest);
        registry.register("sunset", sunset);

        ColorSwatch heroBackground = registry.create("forest");
        heroBackground.rename("Hero Section Background");
        heroBackground.addTag("hero");

        ColorSwatch ctaButton = registry.create("sunset");
        ctaButton.rename("CTA Button");
        ctaButton.addTag("interactive");

        System.out.println(heroBackground);
        System.out.println(ctaButton);
        System.out.println("Original prototype untouched: " + forest);
    }
}

