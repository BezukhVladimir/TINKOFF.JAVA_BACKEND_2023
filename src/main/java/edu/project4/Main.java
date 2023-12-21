package edu.project4;

import edu.project4.models.ImageFormat;
import edu.project4.models.Rect;
import edu.project4.transformations.Disc;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Swirl;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Main {
    private final static int WIDTH = 1920;
    private final static int HEIGHT = 1080;
    private final static Rect WORLD = new Rect(-1.5, -1.5, 3.0, 3.0);
    private final static int SAMPLES = 100_000;
    private final static int ITER_PER_SAMPLE = 200;
    private final static int SYMMETRY = 9;
    private final static double GAMMA = 0.5;
    public static final Path IMAGES_FOLDER = Path.of("src/main/java/edu/project4/images/");

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        long seed = System.currentTimeMillis();

        List<Transformation> variations = new ArrayList<>();
        variations.add(new Linear());
        variations.add(new Sinusoidal());
        variations.add(new Spherical());
        variations.add(new Swirl());
        variations.add(new Disc());

        ImageFormat format = ImageFormat.PNG;
        String fileName = String.format(
            "samples%d_iter%d_symmetry%d_gamma%.2f.%s",
            SAMPLES, ITER_PER_SAMPLE, SYMMETRY, GAMMA, format.name().toLowerCase()
        );
        Path path = IMAGES_FOLDER.resolve(fileName);

        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITER_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            Runtime.getRuntime().availableProcessors(),
            path,
            format
        );
    }
}
